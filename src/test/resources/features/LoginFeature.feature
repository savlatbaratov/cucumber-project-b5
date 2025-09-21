Feature: Docuport Login Logout Feature

  Background: this is for navigating Docuport login page
    Given user is on Docuport login page

  @smoke
  Scenario: Login as a client
    #Given user is on Docuport login page
    When user enters usermane for client
    And user enters password for client
    And user clicks login button
    Then user should be able to see the home page for client

  @smoke
  Scenario: Login as a employee
    #Given user is on Docuport login page
    When user enters usermane for employee
    And user enters password for employee
    And user clicks login button
    Then user should be able to see the home page for employee

  @smoke
  Scenario: Login as a advisor
   # Given user is on Docuport login page
    When user enters usermane for advisor
    And user enters password for advisor
    And user clicks login button
    Then user should be able to see the home page for advisor

  @smoke
  Scenario: Login as a supervisor
   # Given user is on Docuport login page
    When user enters usermane for supervisor
    And user enters password for supervisor
    And user clicks login button
    Then user should be able to see the home page for supervisor


  @dataTableLogin @smoke
  Scenario: Login as a client map practice
    When user enters credentials
      | username | b1g1_client@gmail.com |
      | password | Group1                |
    Then user should be able to see the home page for client
