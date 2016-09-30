@examples
Feature: Examples videos provided by Postimees

  Scenario: Check LeadMedia video
    When user opens article with url "http://sport.postimees.ee/3611563/jalgpalli-otsepilt-kaotusseisust-vaelja-tulnud-infonet-voitis-nomme-kaljut"
    Then video should be visible
    And video should be correct

  Scenario: Check in article video
    When user opens article with url "http://video.postimees.ee/3824985/postimehe-augustikuu-veebivideod"
    Then video should be visible
    And video should be correct

  Scenario: Check YouTube video
    When user opens article with url "http://sport.postimees.ee/3611787/video-lillardi-41-punkti-peatasid-portlandi-kaotuseseeria"
    Then video should be visible
    And video should be correct

  Scenario: Check Instagram video
    When user opens article with url "http://elu24.postimees.ee/3818671/video-steve-o-murdis-peldikutrikki-tehes-molemad-jalad"
    Then video should be visible
    And video should be correct

  Scenario: Check Facebook video
    When user opens article with url "http://sport.postimees.ee/3608777/video-kelly-sildaru-harjutab-lumelauasoitu"
    Then video should be visible
    And video should be correct