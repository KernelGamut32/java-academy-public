# Expeditors - Java Academy

## Week 05 Homework

* In this program, you will refactor your previous project (from week04 homework):
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
    * Create an array that holds Employee objects
        * Load 2 `SalariedEmployee` objects and 2 `HourlyEmployee` objects into the array
        * Loop through your array and display each employee by calling the `getName`, `getJobTitle`, and `calculateWeeklyPay` methods
        * As an added challenge, call and display the results of `calculateYearlyPay` for only the `HourlyEmployee` objects
