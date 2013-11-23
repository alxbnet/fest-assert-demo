package net.alxb.festassertdemo;

import com.google.common.base.Predicate;

import java.util.Collection;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.ObjectUtils.notEqual;

/**
 * @Author Alex Borisov
 */
public class DefaultEmployeesFilter implements EmployeesFilter {

    @Override
    public Collection<Employee> removeEmployeesWithName(Collection<Employee> employees, String name) {
        return filterByPredicate(employees, employeeNotWithName(name));
    }

    @Override
    public Collection<Employee> getEmployeesOlderThan(Collection<Employee> employees, Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee cannot be null.");
        }
        int age = employee.getAge();
        return filterByPredicate(employees, employeeOlderThan(age));
    }

    @Override
    public Collection<Employee> removeNullElements(Collection<Employee> employees) {
        return filterByPredicate(employees, notNull());
    }

    private Collection<Employee> filterByPredicate(Collection<Employee> employees, Predicate<? super Employee> predicate) {
        Collection<Employee> filtered = filter(employees, predicate);
        return newArrayList(filtered);
    }

    private Predicate<? super Employee> employeeOlderThan(final int age) {
        return new Predicate<Employee>() {
            @Override
            public boolean apply(Employee employee) {
                return employee != null && employee.getAge() > age;
            }
        };
    }

    private Predicate<? super Employee> employeeNotWithName(final String name) {
        return new Predicate<Employee>() {
            @Override
            public boolean apply(Employee employee) {
                return employee != null && notEqual(name, employee.getName());
            }
        };
    }
}
