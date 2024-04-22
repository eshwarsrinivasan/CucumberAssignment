This project is implementation of the test scenarios for Open Exchange Api. 

All the acceptance criteria mentioned in the PDF are covered as scenarios in the feature file. (src/test/resources/OpenExchangeTest.feature) 

Some Key Features:
 1. Created a Scenario Context class that would hole the values of attributes within a scenario so that they can be shared accross steps.This is done using dependecy injection using pico container. 
 2. Use of hooks to achieve the point 1.

Running the feature file in IDE:
 1. Install maven > 3.6.
 2. Run the feature file OpenExchangeTest. feature

Running Test using CMD CLI:
 1. Install maven > 3.6
 2. Run command "mvn test". This will identify the TestRunner created within the project where you can define the location of feature file and step definition.

Future enhancements:
 1. Implementation of log4j for standard logging.
 2. Use of cucumber report using plugins in Test Runner file.
