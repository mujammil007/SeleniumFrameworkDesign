
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: LoginErrorValidation
    Given I landed on Ecommerce page
 		When Logged in with email <email> and password <password>
    Then "Incorrect email or password." message is displayed

  	Examples: 
      | email  						| password  				|
      | sahil@test.com 		| Sahil@1			  		| 

