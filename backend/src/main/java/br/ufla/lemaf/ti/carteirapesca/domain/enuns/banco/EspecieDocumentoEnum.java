package br.ufla.lemaf.ti.carteirapesca.domain.enuns.banco;

import lombok.Getter;

@Getter
public enum EspecieDocumentoEnum {

	DUPLICATA_MERCANTIL(1, "DM", "Duplicata Mercantil"),
	NOTA_PROMISSORIA(2, "NP", "Nota Promissória"),
	NOTA_SEGURO(3, "NS", "Nota de Seguro"),
	COBRANCA_SERIADA(4, "CS", "Cobrança Seriada"),
	RECIBO(5, "REC", "Recibo"),
	LETRAS_CAMBIO(6, "LC", "Letras de Câmbio"),
	NOTA_DEBITO(7, "ND", "Nota de Débito"),
	DUPLICATA_SERVICOS(8, "NS", "Duplicata de Serviços"),
	CARTAO_CREDITO(9, "CC", "Cartão de crédito"),
	OUTROS(10, "Outros", "Outros");

	private Integer id;
	private String codigo;
	private String descricao;

	EspecieDocumentoEnum(Integer id, String codigo, String descricao) {

		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;

	}

}
