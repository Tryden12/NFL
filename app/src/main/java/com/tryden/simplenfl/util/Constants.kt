package com.tryden.simplenfl.util

object Constants {

    // NFL base URL
    const val BASE_URL_NFL = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/"

    // NFL scoreboard by dates
    // Example /scoreboard?limit=1000&dates=20220908-20230108
    const val SCOREBOARD_URL =
        "https://site.api.espn.com/apis/site/v2/sports/football/nfl/scoreboard"

    // Article by id base URL
    const val ARTICLE_BY_ID_URL = "http://now.core.api.espn.com/v1/sports/news/"

    // Player by id base URL
    const val PLAYER_BY_ID_URL = "https://sports.core.api.espn.com/v2/sports/football/leagues/nfl/athletes/"
    // Player by id base URL
    const val PLAYER_BY_ID_URL_NEW = "https://site.web.api.espn.com/apis/common/v3/sports/football/nfl/athletes/14876"

    const val DB_NAME = "nfl-db"
    const val HEADLINE_NEWS = "HeadlineNews"
    const val MY_NEWS = "My News"

}