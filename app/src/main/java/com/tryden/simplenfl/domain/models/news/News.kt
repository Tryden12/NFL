package com.tryden.simplenfl.domain.models.news

import com.tryden.simplenfl.domain.models.article.ArticleById


data class News(
    val articles: List<Article> = listOf(),
    val header: String = "",
    val link: Link = Link()
) {

    data class Api(
        val news: NewsX = NewsX(),
        val self: Self = Self()
    )

    data class ApiAthlete(
        val athletes: Athletes = Athletes()
    )

    data class Article(
        val byline: String = "",
        val categories: List<Category> = listOf(),
        val description: String = "",
        val headline: String = "",
        val images: List<Image> = listOf(),
        val lastModified: String = "",
        val links: Links = Links(),
        val premium: Boolean = false,
        val published: String = "",
        val type: String = ""
    )

    data class Athlete(
        val description: String = "",
        val id: Int = 0,
        val links: LinksAthlete = LinksAthlete()
    )

    data class Athletes(
        val href: String = ""
    )

    data class Category(
        val athlete: Athlete? = Athlete(),
        val athleteId: Int? = 0,
        val createDate: String = "",
        val description: String? = "",
        val guid: String? = "",
        val id: Int? = 0,
        val league: League? = League(),
        val leagueId: Int? = 0,
        val sportId: Int? = 0,
        val team: Team? = Team(),
        val teamId: Int? = 0,
        val topicId: Int? = 0,
        val type: String = "",
        val uid: String? = ""
    )

    data class Image(
        val credit: String = "",
        val height: Int = 0,
        val id: Int = 0,
        val name: String = "",
        val type: String = "",
        val url: String = "",
        val width: Int = 0
    )

    data class League(
        val description: String = "",
        val id: Int = 0,
//    val links: LinksX = LinksX()
    )

    data class Leagues(
        val href: String = ""
    )

    data class Link(
        val link: LinkX = LinkX()
    )

    data class Links(
        val api: Api = Api(),
        val mobile: Mobile = Mobile(),
        val web: Web = Web()
    )

    data class LinksAthlete(
        val api: ApiAthlete = ApiAthlete(),
        val mobile: MobileAthlete = MobileAthlete(),
        val web: WebAthlete = WebAthlete()
    )

    data class LinkX(
        val href: String = "",
        val isExternal: Boolean = false,
        val isPremium: Boolean = false,
        val language: String = "",
        val rel: List<String> = listOf(),
        val shortText: String = "",
        val text: String = ""
    )

    data class Mobile(
        val href: String = ""
    )

    data class MobileAthlete(
        val athletes: Athletes = Athletes()
    )

//    data class NewsX(
//        val href: String = ""
//    )
    data class NewsX(
        val href: ArticleById = ArticleById()
    )

    data class Self(
        val href: String = ""
    )

    data class Short(
        val href: String = ""
    )

    data class Team(
        val description: String = "",
        val id: Int = 0,
//    val links: LinksXX = LinksXX()
    )

    data class Teams(
        val href: String = ""
    )

    data class Web(
        val href: String = "",
        val short: Short = Short()
    )

    data class WebAthlete(
        val athletes: Athletes = Athletes()
    )


}