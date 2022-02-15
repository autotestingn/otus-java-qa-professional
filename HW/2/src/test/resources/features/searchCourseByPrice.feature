Feature: Searching course by price and printing information about the found course

  @price
  Scenario: Search course by minimum price on the page "Training courses"
    Given We are on page with all courses
    When We open menu "Курсы" and choose submenu "Подготовительные курсы"
    Then Page with training courses is opened
    When We search course by minimum price
    Then Courses are found by minimum price

  @price
  Scenario: Search course by maximum price on the page "Training courses"
    Given We are on page with all courses
    When We open menu "Курсы" and choose submenu "Подготовительные курсы"
    Then Page with training courses is opened
    When We search course by maximum price
    Then Courses are found by maximum price
