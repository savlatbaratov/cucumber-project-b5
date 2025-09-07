Feature: passing multiple parameters to the same step

  @google_search_data_table
  Scenario: Searching multiple items
    Given user is on Google search page
    Then user searches the following items
    |Loop Academy|
    |Java|
    |Selenium|
    |Taras|
    |Suidim|
    |Halina|
    |Savlat|
    |Polina|
    And we love Loop Academy