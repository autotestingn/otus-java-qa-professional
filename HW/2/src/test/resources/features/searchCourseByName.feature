Feature: Searching course by name and clicking the found course

  @name
  Scenario: Search course by name
    Given We are on page with all courses
    When We search course by name "Специализация С#" and click on it
    Then Course's site is opened