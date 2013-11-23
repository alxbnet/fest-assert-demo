package net.alxb.festassertdemo;

/**
 * Employee with name, last name and age
 *
 * @Author Alex Borisov
 */
public class Employee {

    private final String name;
    private final String lastName;
    private final int age;

    private Employee(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public static class EmployeeBuilder {

        private String name;
        private String lastName;
        private int age;

        public EmployeeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder ofAge(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Employee build() {
            return new Employee(name, lastName, age);
        }
    }
}
