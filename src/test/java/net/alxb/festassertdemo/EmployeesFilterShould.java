package net.alxb.festassertdemo;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;

import static com.google.common.collect.Lists.newArrayList;
import static net.alxb.festassertdemo.Employee.builder;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

/**
 * @Author Alex Borisov
 */
public class EmployeesFilterShould {

    private EmployeesFilter filter = new DefaultEmployeesFilter();

    private Employee alex = builder().withName("Alex").ofAge(25).build();
    private Employee alexDoe = builder().withName("Alex").withLastName("Doe").ofAge(43).build();
    private Employee john = builder().withName("John").ofAge(40).build();
    private Employee maria = builder().withName("Maria").ofAge(31).build();

    private Collection<Employee> employees = newArrayList(alex, alexDoe, john, maria);

    @Test
    public void filterOutEmployeesWithGivenName() {

        Collection<Employee> filtered = filter.removeEmployeesWithName(employees, "Alex");

        assertThat(filtered)
                .isNotEmpty()
                .hasSize(2);

        assertThat(extractProperty("name")
                .from(filtered))
                .doesNotContain("Alex")
                .containsOnly("John", "Maria");
    }

    @Test
    public void returnEmployeesOlderThanGivenEmployee() {

        Collection<Employee> filtered = filter.getEmployeesOlderThan(employees, john);

        Employee alexWithAge43 = builder().withName("Alex").ofAge(43).build();

        assertThat(filtered)
                .isNotEmpty()
                .usingElementComparator(employeeByNameAndAgeComparator())
                .containsExactly(alexWithAge43);
    }

    @Test
    public void removeNullElements() {
        Collection<Employee> employeesWithNulls = newArrayList(alex, null, null, alexDoe, john, maria, null, null);

        Collection<Employee> filtered = filter.removeNullElements(employeesWithNulls);

        assertThat(filtered)
                .doesNotContainNull()
                .isEqualTo(employees);
    }

    private Comparator<? super Employee> employeeByNameAndAgeComparator() {
        return new Comparator<Employee>() {
            @Override
            public int compare(Employee first, Employee second) {

                int compareNamesResult = ObjectUtils.compare(first.getName(), second.getName());
                if (compareNamesResult != 0) {
                    return compareNamesResult;
                }

                return ObjectUtils.compare(first.getAge(), second.getAge());
            }
        };
    }


}
