Feature: Tests related to the search functionality

  @search
  Scenario: Search for a valid product
  Given I am on the homepage
  When I want to search for a product "book"
  Then The results displayed should be of the "book" category