package br.ufla.lemaf.ti.carteirapesca.domain.shares;


import br.ufla.lemaf.ti.carteirapesca.domain.shared.AbstractSpecification;

public class AlwaysFalseSpec extends AbstractSpecification<Object> {
	public boolean isSatisfiedBy(Object o) {
		return false;
	}
}
