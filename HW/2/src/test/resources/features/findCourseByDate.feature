Feature: Searching course by date and printing information about the found course

  @date
  Scenario: Search course starting on or after a specified date
    Given Main page "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям" is opened
    When We search course by date 01 февраля 2022
    Then Course is found after a specified date