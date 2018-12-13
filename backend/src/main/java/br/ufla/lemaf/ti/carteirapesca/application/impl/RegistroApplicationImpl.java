package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.*;
import br.ufla.lemaf.ti.carteirapesca.domain.services.ProtocoloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.boletos.GeradorBoleto;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.DateUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.QRCodeUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.web.RegistroResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.validators.Validate;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Implementação do serviço de registro da camada de aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class RegistroApplicationImpl implements RegistroApplication {

	private static final Integer ESPORTIVA = Modalidade.ESPORTIVA.ordinal();
	private static final Integer RECREATIVA = Modalidade.RECREATIVA.ordinal();

	private ProtocoloBuilder protocoloBuilder;

	private SolicitanteRopository solicitanteRopository;

	/**
	 * Injetando dependências.
	 *
	 * @param protocoloBuilder O Builder de protocolo
	 * @param solicitanteRopository O repositório do solicitante
	 */
	@Autowired
	public RegistroApplicationImpl(final ProtocoloBuilder protocoloBuilder,
	                               final SolicitanteRopository solicitanteRopository) {
		this.protocoloBuilder = protocoloBuilder;
		this.solicitanteRopository = solicitanteRopository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Protocolo registrar(final RegistroResource resource) {

		var solicitante = getSolicitante(resource);

		Protocolo protocolo;

		String identificador = resource.getPessoa().getCpf() != null ? resource.getPessoa().getCpf() : resource.getPessoa().getPassaporte();

		Pessoa pessoa = WebServiceUtils.webService().buscarPessoaFisicaPeloCpf(identificador);
		if(pessoa == null){
			throw new RuntimeException("Pessoa não foi encontrada no Entrada Única com o cpf informado.");
		}

		if (!solicitante.pussuiLicencaAtiva()) {

			var licenca = criarLicenca(resource);

			// TODO - Armazenar o caminho do boleto para pagamento
			// Gera o boleto para pagamento da carteira
			GeradorBoleto geradorBoleto = new GeradorBoleto(pessoa, licenca.protocolo().toString());
			String caminhoBoleto = geradorBoleto.gerarBoleto();

			licenca.setCaminhoBoleto(caminhoBoleto);

			// TODO - Armazenar o caminho da carteira de pesca
			String caminhoCarteiraDePesca = gerarCarteiraDePesca(pessoa, licenca.protocolo().toString(), licenca.modalidade().toString());

			licenca.setCaminhoCarteira(caminhoCarteiraDePesca);

			protocolo = solicitante.adicionarLicenca(licenca);

		} else {

			throw new SolicitanteException("solicitante.licenca.ativa");
		}

		solicitanteRopository.save(solicitante);

		return protocolo;
	}

	/**
	 * Cria uma licença de pesca.
	 *
	 * @param resource Os dados de registro.
	 * @return A Licenca
	 */
	private Licenca criarLicenca(final RegistroResource resource) {

		var modalidade = gerarModalidade(resource.getInformacaoComplementar().getModalidade());
		var protocolo = new Protocolo(protocoloBuilder.gerarProtocolo(modalidade));

		return new Licenca(protocolo, modalidade);
	}

	/**
	 * Cadastra o usuário no Entrada Única.
	 *
	 * @param pessoa A pessoa
	 */
	private void cadastrarPessoa(PessoaDTO pessoa) {

		WebServiceUtils.validarWebService();

		Pessoa pessoaEU = WebServiceUtils.webService().buscarPessoaFisicaPeloCpf(pessoa.getCpf());

		if(pessoaEU == null) {

			WebServiceUtils.webService().cadastrarPessoa(pessoa);
		}
	}

	/**
	 * Constroi uma modalidade a partir da modalidade.
	 *
	 * @param tipo O tipo da modalidade
	 * @return A Modalidade
	 */
	private static Modalidade gerarModalidade(Integer tipo) {

		if (tipo.equals(ESPORTIVA)) {

			return Modalidade.ESPORTIVA;

		} else if (tipo.equals(RECREATIVA)) {

			return Modalidade.RECREATIVA;

		} else {

			return Modalidade.UNKNOWN;

		}

	}

	/**
	 * Busca o solicitante se o mesmo já estiver registrado ou
	 * cria um novo caso não esteja registrado.
	 *
	 * @param resource Os dados de registro
	 * @return O Solicitante
	 */
	private Solicitante getSolicitante(final RegistroResource resource) {

		var cpf = !Validate.isNull(resource.getPessoa().getCpf())
			? new CPF(resource.getPessoa().getCpf())
			: null;
		var passaporte = !Validate.isNull(resource.getPessoa().getPassaporte())
			? new Passaporte(resource.getPessoa().getPassaporte())
			: null;

		Solicitante solicitante = null;

		if (cpf != null) {
			solicitante = solicitanteRopository.findByIdentityCpfNumero(cpf.getNumero());
		} else if (passaporte != null) {
			solicitante = solicitanteRopository.findByIdentity_Passaporte_Numero(passaporte.getNumero());
		}

		if (solicitante == null) {
			solicitante = new Solicitante(cpf, passaporte);
			cadastrarPessoa(resource.getPessoa());
		}

		return solicitante;
	}

	private String gerarCarteiraDePesca(Pessoa pessoa, String protocolo, String modalidade) {

		try {

			String protocoloSimples = protocolo.replace("-", "").replace("/", "");

			ClassLoader classLoader = getClass().getClassLoader();

			BufferedImage bufferedImage = ImageIO.read(classLoader.getResource("templates/template_carteira_pesca.png").openStream());

			Graphics g = bufferedImage.getGraphics();
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.PLAIN, 12));

			int col1, col2, row;
			col1 = 52;
			col2 = 320;
			row = 92;

			// Nome
			g.drawString(pessoa.nome, col1, row);

			// CPF / PASSAPORTE
			if(pessoa.passaporte != null) {
				g.drawString( CPFUtils.format(pessoa.cpf) + " / " + pessoa.passaporte.toString(), col2, row);
			}
			else {
				g.drawString(CPFUtils.format(pessoa.cpf), col2, row);
			}

			row += 50;

			// Número da licença
			g.drawString(protocolo, col1, row);

			// Modalidade
			g.drawString(modalidade, col2, row);

			row += 48;

			// Endereço
			Endereco endereco = null;

			for(Endereco e : pessoa.enderecos) {
				if(!StringUtils.isBlank(e.logradouro) && !StringUtils.isBlank(e.cep)) {
					endereco = e;
				}
			}

			String strEndereco =  endereco.logradouro + ", Nº " + endereco.numero + " " + (endereco.complemento != null ? endereco.complemento : "") + ", " + endereco.bairro;
			g.drawString(strEndereco, col1, row);

			row += 48;

			// Município / UF
			g.drawString(endereco.municipio.nome + " / " + endereco.municipio.estado.sigla, col1, row);

			// CEP
			g.drawString(endereco.cep != null ? endereco.cep : "-", col2, row);

			row += 48;

			// País
			g.drawString("BRASIL", col1, row);

			// Limite de captura
			g.drawString("0 (ZERO)", col2, row);

			row += 48;

			// Valor
			// TODO - Definir como será obtido o valor da carteira.
			g.drawString("R$41,20", col1, row);

			// Emissao
			g.drawString(DateUtils.formatDate(new Date(), "dd/MM/yyyy"), col2, row);

			// QRCode
			BufferedImage qrCode = QRCodeUtils.createQRCodeImage(protocolo);
			g.drawImage(qrCode, 1000, 80, null);

			g.dispose();

			Path caminhoCarteiraPesca = Paths.get(Properties.pathCarteiraPesca() + protocoloSimples + "/" + protocoloSimples + ".png");
			File carteiraPesca = caminhoCarteiraPesca.toFile();

			if(!carteiraPesca.exists()) {
				Files.createDirectories(caminhoCarteiraPesca.getParent());
			}

			ImageIO.write(bufferedImage, "png", carteiraPesca);

			return caminhoCarteiraPesca.toString();

		}
		catch (Exception e) {

			e.printStackTrace();

			throw new SolicitanteException("solicitante.erro.carteira.pesca");
		}

	}
}
