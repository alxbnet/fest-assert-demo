package net.alxb.festassertdemo;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.util.Lists.newArrayList;

/**
 * Tests {@link DeepCloneable} class implementation to demonstrate
 * {@link org.fest.assertions.api.Assertions}
 * 
 * @author Alex Borisov
 * 
 */
public class DeepCloneableShould {

	private int intValue = 100;
	private Date date = new Date();
	private List<Date> list = newArrayList(date, date);

	@Test
	public void createNewButEqualObject() throws CloneNotSupportedException {

        DeepCloneable cloneable = new DeepCloneable(intValue, date, list);
        Object cloned = cloneable.clone();

		assertThat(cloned)
                .isNotNull()
                .hasSameClassAs(cloneable)
                .isExactlyInstanceOf(DeepCloneable.class)
                .isNotSameAs(cloneable)
				.isEqualTo(cloneable);
	}

}