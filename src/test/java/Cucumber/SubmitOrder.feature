
Feature: Purchase the order from Ecommerce website

Background: 
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with email <email> and password <password>
    When I add product <productName> to cart
    And Chechout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | email  						| password  				| productName  		|
      | sahil@test.com 		| Sahil@1998	  		| ZARA COAT 3     |
