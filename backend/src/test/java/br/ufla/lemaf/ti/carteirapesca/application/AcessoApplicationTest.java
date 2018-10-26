package br.ufla.lemaf.ti.carteirapesca.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.caelum.stella.tinytype.CPF;
import br.ufla.lemaf.ti.carteirapesca.application.impl.AcessoApplicationImpl;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Passaporte;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.junit.Before;
import org.junit.Test;

public class AcessoApplicationTest {

	CadastroUnificadoService cadastroUnificadoService;
	AcessoApplicationImpl acessoApplication;

	@Before
	public void setUp() {
		cadastroUnificadoService = mock(CadastroUnificadoService.class);
		acessoApplication = new AcessoApplicationImpl();
	}

	@Test
	public void testIdentificarComCPF() {
		Pessoa expectedPessoa = new Pessoa();
		CPF cpf = new CPF("999.999.999-99");
		AcessoResource acessoResource = new AcessoResource(cpf.getNumero(), null);
		expectedPessoa.cpf = cpf.getNumeroFormatado();

		when(cadastroUnificadoService.existeUsuario(cpf.getNumero())).thenReturn(false);
		when(cadastroUnificadoService.buscarUsuario(cpf.getNumero())).thenReturn(new Pessoa());

		Pessoa pessoa = acessoApplication.identificar(acessoResource);
		assertThat(pessoa).isEqualToComparingFieldByField(expectedPessoa);
	}

	@Test
	public void testIdentificarComPassaporte() {
		Pessoa expectedPessoa = new Pessoa();
		Passaporte passaporte = new Passaporte("123123123");
		AcessoResource acessoResource = new AcessoResource(null, passaporte.getNumero());
		expectedPessoa.passaporte = passaporte.getNumero();

		when(cadastroUnificadoService.existeUsuario(passaporte.getNumero())).thenReturn(false);
		when(cadastroUnificadoService.buscarUsuario(passaporte.getNumero())).thenReturn(new Pessoa());

		Pessoa pessoa = acessoApplication.identificar(acessoResource);
		assertThat(pessoa).isEqualToComparingFieldByField(expectedPessoa);
	}

}
