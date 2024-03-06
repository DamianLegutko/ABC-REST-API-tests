# ABC of REST API tests
This project is an example of REST API testing framework using Java 17 & Retrofit2. It showcases various test cases and methodologies for testing RESTful CRUD service.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine.

## Prerequisites

What things you need to install the software and how to install them:

- Windows system (for bat scripts)
- Chrome browser
- JDK 17 Azul zulu 17.0.9 or later
- IntelliJ IDEA 2023.1.3 or later
  - Annotation Processing is enabled
- Git 2.44.0 or later

## Installing

A step by step series of examples that tell you how to get a development environment running:

### 1. Clone the repository:
```bash
git clone https://github.com/DamianLegutko/ABC-REST-API-tests.git
```

### 2. Navigate to the project directory:
```bash
cd ABC-REST-API-tests
```

### 3. Enable Annotation Processing 
To enable annotation processing in IntelliJ IDEA 2023.1.3 or later, follow these steps to ensure your project correctly recognizes and processes annotations, such as those provided by the Lombok library. This guide assumes you already have IntelliJ IDEA 2023.1.3 installed and a project opened.

#### Step 1: Open Annotation Processors Settings

1. Navigate to `File` > `Settings` on Windows/Linux or `IntelliJ IDEA` > `Preferences` on macOS.
2. In the Settings/Preferences window, go to `Build, Execution, Deployment` > `Compiler` > `Annotation Processors`.

#### Step 2: Enable Annotation Processing

1. In the Annotation Processors section, check the box next to `Enable annotation processing`. This activates the annotation processing feature for your project.
2. Optionally, you can adjust the specific settings related to annotation processing, such as the output directory and the processor path. However, for most projects, the default settings should suffice.

#### Step 3: Apply and Save

1. Click `Apply` to save your changes.
2. Click `OK` to close the Settings/Preferences window.

#### Step 4: Rebuild Your Project

After enabling annotation processing, it's a good practice to rebuild your project to ensure all annotations are processed correctly:

1. Go to `Build` > `Rebuild Project`.
2. Wait for the rebuild process to complete.

#### Troubleshooting

- If your annotations still do not seem to be processed, ensure that you have the necessary libraries (e.g., Lombok) added to your project's dependencies.
- Check that the version of the library supports annotation processing in your specific IDE version.
- If problems persist, consider invalidating the cache and restarting IntelliJ IDEA via `File` > `Invalidate Caches / Restart` > `Invalidate and Restart`.

By following these steps, you should successfully enable annotation processing in IntelliJ IDEA 2023.1.3, allowing your project to utilize libraries like Lombok more effectively.

### 4. Build the project with Maven:
```bash
mvn clean install
```

# Test Running

## Test run script (Windows only)

```
.\runWithReport.bat
```

## Run test with maven wrapper
```
.\mvnw.cmd clean test
```

## Run test with maven global installation
```
mvn clean test
```

## Report generation with maven wrapper
```
.\mvnw.cmd allure:report
```

## Report generation with maven
```
mvn allure:report
```

## Open report in Chrome browser (Windows only)
```
.\openAllureReportExampleInChrome.bat
```

# Open bug list report in a browser (Windows only)
```
.\openListReportExampleInBrowser.bat
```

This command runs all the tests in the project and outputs the results to the console.
# Assumptions

## Book Specification Assumptions
This document outlines the assumptions for the Book specification. The Book's text values should support Latin, Polish and special characters,
which is essential for correctly handling `title`s, author `name`s, and other text fields containing specific Polish diacritical marks.

### id
- The `id` field represents a unique number assigned by the server at the time of creation. It ensures that each book can be uniquely identified within the system.

### name
- The `name` field is mandatory. It holds the title of the book and is essential for identifying and cataloging the book in the system.

### author
- The `author` field is mandatory. It specifies the author(s) of the book, allowing for proper attribution and searchability by authorship.

### publication
- The `publication` field is mandatory. It records the year of publication, providing essential information for bibliographic records and helping to categorize books chronologically.

### category
- The `category` field is mandatory. It classifies the book into predefined categories, enhancing search and organization within the system. The available categories are:
  - Programming
  - New Age

### pages
- The `pages` field specifies the number of pages in the book. It accepts a value range from 1 to 10,000, accommodating a wide variety of book lengths and formats.

### price
- The `price` field denotes the cost of the book. It is set within a range from 0.01 to 10,000.00, with a minimum increment of 0.01. This precision allows for accurate pricing and financial transactions within the system.

## Used Libraries

### Lombok
- [Link to Maven repository](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- [Link to the project](https://projectlombok.org/)
- [Link to a tutorial](https://www.baeldung.com/lombok-ide)

Lombok is a Java library that automates repetitive coding tasks such as generating getter and setter methods. By using this library, developers can focus on more important aspects of the code, avoiding errors from manually writing boilerplate code. Lombok significantly improves code readability and reduces its volume.

#### Lombok annotation
Enable lombok annotation is required.

### JUnit Jupiter
- [Link to Maven repository](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
- [Link to the project](https://junit.org/junit5/)
- [Link to a tutorial](https://www.baeldung.com/junit-5)

JUnit Jupiter is part of JUnit 5, providing an API for writing unit and integration tests in Java. It is a powerful tool that allows for more flexible and expressive tests thanks to new assertions and annotations. It facilitates integration with IDE environments and build tools such as Maven and Gradle, making it the standard for testing Java applications.

### Retrofit2
- [Link to Maven repository](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit)
- [Link to the project](https://square.github.io/retrofit/)
- [Link to a tutorial](https://futurestud.io/tutorials/retrofit-getting-started-and-android-client)

Retrofit2 is a commonly used library in Java and Android applications for HTTP API communication. It allows for easy mapping of Java interfaces to REST calls. Thanks to annotations, Retrofit2 makes network connection management and data processing much simpler and more intuitive, significantly speeding up application development.

### Jackson
- [Link to Maven repository](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [Link to the project](https://github.com/FasterXML/jackson)
- [Link to a tutorial](https://www.baeldung.com/jackson)

Jackson is a versatile library for processing JSON in Java. It allows for the serialization of Java objects to JSON and deserialization of JSON to Java objects. It is a key tool in web and mobile application development, supporting integration with RESTful APIs. Jackson offers high performance and flexibility in data mapping, making it a popular choice among developers.

### Guice
- [Link to Maven repository](https://mvnrepository.com/artifact/com.google.inject/guice)
- [Link to the project](https://github.com/google/guice)
- [Link to a tutorial](https://www.baeldung.com/guice)

Guice is a lightweight tool for dependency injection, designed to simplify configuration and dependency management in Java applications. It is frequently used by Android developers and in many libraries due to its modularity and ease of integration. Guice promotes writing testable, maintainable, and flexible code.

### AssertJ
- [Link to Maven repository](https://mvnrepository.com/artifact/org.assertj/assertj-core)
- [Link to the project](https://assertj.github.io/doc/)
- [Link to a tutorial](https://www.baeldung.com/introduction-to-assertj)

AssertJ provides a rich set of assertions that greatly simplify writing tests in Java. It enables creating more readable and concise tests through a fluent interface and the ability to easily create custom assertions. This tool improves the quality of test code and facilitates understanding test cases.

### Faker
- [Link to Maven repository](https://mvnrepository.com/artifact/com.github.javafaker/javafaker)
- [Link to the project](https://github.com/DiUS/java-faker)
- [Link to a tutorial](https://www.baeldung.com/java-faker)

Faker is a library for generating fake data for various purposes, such as testing, database population, or prototyping. It provides a wide range of data, from names and addresses to quotes and colors. It enables easy creation of realistic test data, which is invaluable in the development and testing process.

# Approaches Used

In this document, I describe the methodologies and tools utilized in my CRUD API demo project, focusing on data generation, 
data serialization, and assertions. These approaches have significantly streamlined the testing process, enhancing both efficiency and reliability.

## Test Data Generation

For generating payloads in tests, we employ factories that create Java objects with random default values. 
This method allows for the dynamic generation of data, which is crucial for testing the versatility and robustness of the CRUD API. By utilizing factories, we can simulate a wide range of scenarios and data conditions without manually crafting each test case. This not only saves time but also introduces an element of unpredictability in the tests, mimicking real-world data variability. The use of Java objects ensures type safety and clarity, making the tests easier to understand and maintain.

### Utilizing Faker for Realistic Data Generation

The Faker library is an invaluable tool for software developers and testers, designed to facilitate the generation of random data that closely mimics real-world information. This capability is crucial for developing robust and comprehensive tests, simulations, and prototypes. Faker's versatility and ease of use make it an essential resource in the toolkit of modern software development.

#### Overview of Faker

Faker is capable of producing a wide variety of data types, including but not limited to names, addresses, phone numbers, emails. Its design allows for the creation of data that is not only random but also possesses the characteristics and formats of genuine data, enhancing the realism of test environments and data sets.

#### Benefits of Using Faker

1. **Enhanced Test Coverage**: By generating diverse and realistic datasets, Faker helps ensure that applications are tested under conditions that closely resemble actual usage scenarios.
2. **Efficiency**: Manually creating test data that reflects a wide range of scenarios can be time-consuming and error-prone. Faker automates this process, saving developers and testers significant amounts of time.
3. **Customizability**: While Faker provides a vast array of predefined data types, it also offers the flexibility to customize data generation according to specific requirements. This means that almost any kind of data needed for testing or development can be generated using Faker.
4. **Localization Support**: Faker supports multiple locales, allowing it to generate data that is appropriate for different geographical and cultural contexts. This feature is particularly useful for applications intended for a global audience.

#### Conclusion

Faker stands out as a powerful library for generating fake but realistic data, aiding in the development and testing of software applications. Its ability to produce a broad spectrum of randomized data types, combined with its ease of use and customization options, makes it an indispensable tool for improving the quality and effectiveness of software tests.


## Data Serialization

The project leverages Retrofit2, which offers out-of-the-box integration for converting between Java objects and JSON text, 
and vice versa. 
This seamless serialization and deserialization facilitate communication with the API by abstracting 
the complexities involved in data format conversion. Additionally, the framework includes a utility, 
identified by `pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer` [Serializer Util class](src/main/java/pl/damianlegutko/demo/api/test/crud/framework/serializer/Serializer.java), 
which provides methods for converting between Java objects and JSON/YML formats in both directions. 
This utility enhances our testing framework's flexibility, 
allowing for easy manipulation and verification of data in different formats.

## Assertions

Our tests heavily rely on assertions from AssertJ, specifically utilizing recursive assertions as the foundation. 
The advantage of using recursive assertions lies in their ability to deeply compare object graphs. 
This means that by using a DTO/POJO in our assertion, we can ensure that the assertion remains up-to-date with the object structure. Recursive assertions automatically navigate through the fields of the objects being compared, thus providing a thorough and accurate assessment of the test outcomes. This method significantly reduces the need for manually updating test assertions to align with changes in object models, thereby maintaining the relevance and accuracy of tests over time.

## REST Resource Representation in Tests

In the context of REST API testing, especially when dealing with resources like books, it's essential for the resource representations to implement the `Resourceable` interface. This interface is designed to facilitate the interaction with REST resources, ensuring they can be easily serialized, cloned, and managed within the test framework.

For instance, the representation of RESTful objects, such as a [Book](src/main/java/pl/damianlegutko/demo/api/test/crud/domain/books/api/v1/books/Book.java)`pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books`
, should implement the [Resourceable](src/main/java/pl/damianlegutko/demo/api/test/crud/framework/data/Resourceable.java) `src/main/java/pl/damianlegutko/demo/api/test/crud/framework/data/Resourceable.java`. This approach not only standardizes the resource management in tests but also leverages the capabilities of the [Serializer Util class](src/main/java/pl/damianlegutko/demo/api/test/crud/framework/serializer/Serializer.java), which is instrumental in handling serialization and deserialization processes efficiently.

By adhering to this practice, testers can ensure a consistent and scalable framework for interacting with various REST resources across different API tests.

A significant advantage of this approach is that REST Resource classes can incorporate a wealth of reusable business logic, which is both cost-effective and easy to maintain.

### Framework support
## Tests configuration
`src/main/resources/config.yml` [ Project config file ](src/main/resources/config.yml)

must represent class `pl.damianlegutko.demo.api.test.crud.framework.config.Config` [Config class](src/main/java/pl/damianlegutko/demo/api/test/crud/framework/config/Config.java).

# Reporting
## Allure report integration
### @Severity
@Severity is used to define the severity level of a test case, helping to prioritize tests based on their importance or impact.

### @Epic & @Feature & @Story
@Epic is a high-level description grouping multiple features, @Feature represents a specific aspect of the software being tested, and @Story describes a single functionality or behavior within a feature, allowing for detailed test organization.

### @TmsLink
@TmsLink is used to link a test with an ID in a Test Management System, providing a direct reference to the test case's specification or details.

### @Link
@Link allows attaching an arbitrary link to the test case, which can point to documentation, issue trackers, or any relevant external information.

### @Owner
@Owner is used to specify the owner or the main responsible person for a given test case, facilitating accountability and direct contact for test-related queries.

# Why BDD Was Not Used

In the context of our API automation testing project, the decision not to use Behavior-Driven Development (BDD) methodologies such as Cucumber or JBehave was made after careful consideration of the specific needs and constraints of our testing environment. Below are the key reasons why BDD was not deemed suitable for this particular project:

## No Requirement for BA-Prepared Test Cases

- The project did not necessitate the preparation of test cases by Business Analysts (BAs). This meant that one of the primary values of BDD as a communication tool—bridging the understanding between technical and non-technical team members through the use of natural language scenarios—was not applicable. The testing requirements were straightforward and could be directly translated into automated tests by the QA team without the need for an intermediary layer.

## Limitations in Data Passing

- BDD tools like Cucumber and JBehave have limitations in passing data through return values. This constraint poses a significant challenge in API testing, where the ability to pass data between steps or scenarios dynamically is crucial for testing complex interactions and data flows.

## Nature of UI vs. API Test Cases

- While UI test cases tend to be more procedural, allowing for a more straightforward adaptation to the step-wise structure promoted by BDD frameworks, API test cases are inherently more functional. They often require a more intricate setup and validation logic that doesn't fit neatly into the BDD scenario format.

## Workarounds and Their Drawbacks

- To circumvent the data passing limitations of BDD tools, common workarounds involve the use of shared memory spaces for storing and retrieving test data between steps. However, this approach introduces the risk of tests inadvertently affecting each other, leading to flaky tests and unreliable outcomes. Such workarounds necessitate constructing business steps in an artificial and unintuitive manner, detracting from the readability and maintainability of the test code.

## Conclusion
In conclusion, while BDD provides valuable benefits for enhancing communication between technical and non-technical stakeholders and structuring test scenarios in a user-centric manner, the specific requirements and technical considerations of our API testing project made it less suited for our needs. The decision to forego BDD was based on the desire to maintain clarity, efficiency, and reliability in our test automation practices without the compromises and workarounds necessitated by the current limitations of BDD tools in the context of API testing.

# Authors

* [DamianLegutko](https://github.com/DamianLegutko)

# License

This project is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. This license allows others to download this work and share it with others as long as they credit the author, but they can't change the work in any way or use it commercially. For more details and the full license text, please visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
