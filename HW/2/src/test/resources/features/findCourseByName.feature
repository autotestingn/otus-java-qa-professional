Feature: Searching course by name and clicking the found course

  @name
  Scenario: Search course by name
    Given Main page "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям" is opened

    When We search course by name "Специализация С#" and click on it
    Then Course's page with title "Специализация С#" is opened