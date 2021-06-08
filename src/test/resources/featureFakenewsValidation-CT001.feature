@fakenewsvalidation @BAT
Feature: Validate Patient Top application and comparing score

  
  Scenario Outline: Validate score for a patient
    Given I launch browser "chrome"
    And   I navigate to "web" application
    When  I click on news related to  "<News Details>"
    Then  I validate user is navigated to full details of the news 
    And   I launch new site called "https://www.google.co.in" to confirm the news
    And   I search "<News Details>" in the google search
    And   I validate the news displayed in guardian is displayed in the search results
    And   I click on the news "1" and validate the content
  
  @fakenewsvalidation  
  Examples: 
      | News Details                             |
      | French Open 2021:Federar pulls out       | 
 
  
   
  
  
  
