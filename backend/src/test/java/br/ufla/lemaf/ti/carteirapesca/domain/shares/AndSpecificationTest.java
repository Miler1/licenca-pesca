package br.ufla.lemaf.ti.carteirapesca.domain.shares;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.AndSpecification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AndSpecificationTest {

	@Test
	public void testAndIsSatisifedBy() {
		AlwaysTrueSpec trueSpec = new AlwaysTrueSpec();
		AlwaysFalseSpec falseSpec = new AlwaysFalseSpec();

		AndSpecification<Object> andSpecification = new AndSpecification<Object>(trueSpec, trueSpec);
		assertThat(andSpecification.isSatisfiedBy(new Object())).isTrue();

		andSpecification = new AndSpecification<Object>(falseSpec, trueSpec);
		assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

		andSpecification = new AndSpecification<Object>(trueSpec, falseSpec);
		assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

		andSpecification = new AndSpecification<Object>(falseSpec, falseSpec);
		assertThat(andSpecification.isSatisfiedBy(new Object())).isFalse();

	}

}
