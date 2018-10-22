package br.ufla.lemaf.ti.carteirapesca.domain.shares;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.OrSpecification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrSpecificationTest {

	@Test
	public void testAndIsSatisifedBy() {
		AlwaysTrueSpec trueSpec = new AlwaysTrueSpec();
		AlwaysFalseSpec falseSpec = new AlwaysFalseSpec();

		OrSpecification<Object> orSpecification = new OrSpecification<Object>(trueSpec, trueSpec);
		assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

		orSpecification = new OrSpecification<Object>(falseSpec, trueSpec);
		assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

		orSpecification = new OrSpecification<Object>(trueSpec, falseSpec);
		assertThat(orSpecification.isSatisfiedBy(new Object())).isTrue();

		orSpecification = new OrSpecification<Object>(falseSpec, falseSpec);
		assertThat(orSpecification.isSatisfiedBy(new Object())).isFalse();

	}
}
