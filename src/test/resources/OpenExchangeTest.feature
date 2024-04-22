@openexchangetest
Feature: OpenXchangeRate api testing

  Background:
    Given I hit the api url "https://open.er-api.com/v6/latest/USD" successfully


  Scenario: Check if open exchange rate api is up and we get 200 response
    Given I hit the api url "https://open.er-api.com/v6/latest/USD" successfully


  Scenario: Check if json schema matches the expected schema
    Then I verify if json schema matches the expected json

  Scenario: Check for success response from the API
    Then I verify if response received is "success"

  Scenario: Check for number of currencies received from API is 162
    Then I verify number of distinct currencies received is 162

  Scenario: Check if the USD price against AED is within expected range
    Then I verify if USD price against AED is within range
