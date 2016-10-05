@postimees
Feature: Videos

  Background: Open postimees page
    Given user opens postimees main page

  Scenario: Check 50 first articles
    When user collects 50 first articles
    Then should have 50 articles with URL's
    When user opens each article and checks for video
    Then all found videos should have valid videos