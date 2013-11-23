package net.alxb.festassertdemo;

import java.util.Collection;

/**
 * @Author Alex Borisov
 */
public interface EmployeesFilter {
    Collection<Employee> removeEmployeesWithName(Collection<Employee> employees, String name);

    Collection<Employee> getEmployeesOlderThan(Collection<Employee> employees, Employee employee);

    Collection<Employee> removeNullElements(Collection<Employee> employeesWithNulls);
}
