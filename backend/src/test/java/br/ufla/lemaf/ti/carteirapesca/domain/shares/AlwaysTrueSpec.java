package br.ufla.lemaf.ti.carteirapesca.domain.shares;


import br.ufla.lemaf.ti.carteirapesca.domain.shared.AbstractSpecification;

public class AlwaysTrueSpec extends AbstractSpecification<Object> {
	public boolean isSatisfiedBy(Object o) {
		return true;
	}
}
