# Expeditors - Java Academy

## Week 11 Homework

* In this assignment, you will refactor your previous implementation (from week 05 homework) to create a CRUD API for employees
* For reference, here are the instructions from the **Week 05 Homework**
    * Create an abstract base class named `Employee` with:
        * Private `name` and `jobTitle` fields
        * A constructor that initializes the fields
        * Getter methods for each field
        * An abstract method named `calculateWeeklyPay`
    * Create a subclass named `HourlyEmployee` - to this class add:
        * Two private fields (`hoursWorked` and `hourlyPayRate`)
        * A constructor that initializes the fields
        * Getter methods for the new fields
        * An appropriate `calculateWeeklyPay` method (`hoursWorked * hourlyPayRate`)
        * A `calculateYearlyPay` method (`hoursWorked * hourlyPayRate * 52`)
    * Create a subclass named `SalariedEmployee` - to this class add:
        * One private field (`yearlySalary`)
        * A constructor that initializes the field
        * A getter method for the new field
        * An appropriate `calculateWeeklyPay` method (`yearlySalary / 52`)
* Use [start.spring.io](https://start.spring.io/) to create a new Spring Boot application that will provide a REST API to support CRUD operations against hourly employees and salaried employees
* Use partitioning in your application (dao/inmemory, domain, services, controllers) and use annotation-based Spring configuration to "wire up" your API
* Provide both unit and integration tests (to whatever degree you feel comfortable)
* Use https://github.com/KernelGamut32/java-academy-public/tree/main/week11/examples/registration as an example to guide you
