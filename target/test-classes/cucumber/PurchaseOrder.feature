
@tag
Feature: Purchase the order from ecommerce website

Background:
	Given user is on landing Page
 
 @tag2
  Scenario Outline: Positive test of submitting order
  
    Given logged in with username <username> and password <password>
    When I add product <productName> to cart
    And checkout productname <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation Page

    Examples: 
      |username|password|productName| 
      |preethiselaval@gmail.com|Preethi2000|ZARA COAT 3|
      |deepas@gmail.com|Deepa2001|IPHONE 13 PRO|
