
Feature: check fb page
  I want to use this template for my feature file

	@smoke @regression
  Scenario: enter nothing
    Given initiate web driver and launch fb home page
    When feilds are blank
    And sign in clicked
    Then validate the error message and close the window
    
    
    @smoke 
  Scenario: enter nothing
    Given initiate web driver and launch fb home page
    When feilds are blank
    And sign in not clicked
    Then do nothing