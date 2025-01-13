# Expeditors - Java Academy

## Week 07 Homework

The starter provides a working application for managing courses for registration. Using the `Mockito` example provided 
at https://github.com/KernelGamut32/java-academy-public/tree/main/week07/examples/Mockito/end/mocking-with-junit,
add a set of JUnit tests for the `CourseService` class but **DO NOT INCLUDE ADDING DATA TO THE DAO IN YOUR TESTS!**.

Instead, use mocking for the DAO to isolate your testing to the units of code defined in the service (true UNIT testing). In
fact, to confirm that your tests are correctly utilizing mocking, you should be able to delete the
`RegistrationApp` class and the `InMemoryCourseDAO` class, and your tests should still run and pass.
