@tag
Feature: Purchase the order from ecommerce website

 
 @tag2
  Scenario Outline: Negative test of submitting order
  
    Given user is on landing Page
    Given logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed on landingPage
    Examples: 
      | username  | password |
      | arkatkowsalya1721@gmail.com  |Preethi2000 |
     
