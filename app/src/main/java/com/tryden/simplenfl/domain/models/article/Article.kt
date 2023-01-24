package com.tryden.simplenfl.domain.models.article


data class Article(
    val headlines: List<Headline> = listOf(),
    val resultsCount: Int = 0,
    val resultsLimit: Int = 0,
    val resultsOffset: Int = 0,
    val status: String = "",
    val timestamp: String = ""
) {

    data class Api(
        val news: News = News()
    )

    data class ApiRelated(
        val news: NewsRelated = NewsRelated()
    )

    data class ApiTeam(
        val teams: LinksTeams = LinksTeams()
    )

    data class App(
        val sportscenter: Sportscenter = Sportscenter()
    )

    data class Category(
        val description: String = "",
        val id: Int = 0,
        val league: League? = League(),
        val leagueId: Int? = 0,
        val sportId: Int = 0,
        val team: Team? = Team(),
        val teamId: Int? = 0,
        val topicId: Int? = 0,
        val type: String = "",
        val uid: String? = ""
    )

    data class Headline(
        val allowAMP: Boolean = false,
        val allowAds: Boolean = false,
        val allowComments: Boolean = false,
        val allowCommerce: Boolean = false,
        val allowSearch: Boolean = false,
        val byline: String = "",
        val categories: List<Category> = listOf(),
        val dataSourceIdentifier: String = "",
        val description: String = "",
        val headline: String = "",
        val id: Int = 0,
        val images: List<Image> = listOf(),
        val keywords: List<Any> = listOf(),
        val lastModified: String = "",
        val linkText: String = "",
        val links: Links = Links(),
        val metrics: List<Metric> = listOf(),
        val nowId: String = "",
        val originallyPosted: String = "",
        val premium: Boolean = false,
        val published: String = "",
        val related: List<Related> = listOf(),
        val source: String = "",
        val story: String = "",
        val title: String = "",
        val type: String = "",
        val video: List<Any> = listOf()
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

    data class ImageRelated(
        val alt: String? = "",
        val caption: String? = "",
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
    )

    data class Leagues(
        val href: String = ""
    )

    data class Links(
        val api: Api = Api(),
        val app: App = App(),
        val mobile: Mobile = Mobile(),
        val web: Web = Web()
    )

    data class LinksRelated(
        val api: ApiRelated = ApiRelated(),
        val mobile: MobileRelated = MobileRelated(),
        val web: WebRelated = WebRelated()
    )

    data class LinksTeam(
        val api: ApiTeam = ApiTeam(),
        val mobile: MobileTeam = MobileTeam(),
        val web: WebTeam = WebTeam()
    )

    data class LinksTeams(
        val href: String = ""
    )

    data class Metric(
        val count: Int = 0,
        val type: String = ""
    )

    data class Mobile(
        val href: String = ""
    )

    data class MobileRelated(
        val href: String = ""
    )

    data class MobileTeam(
        val teams: LinksTeams = LinksTeams()
    )

    data class News(
        val href: String = ""
    )

    data class NewsRelated(
        val href: String = ""
    )

    data class Related(
        val byline: String = "",
        val dataSourceIdentifier: String? = "",
        val description: String = "",
        val headline: String = "",
        val id: Int = 0,
        val images: List<ImageRelated> = listOf(),
        val lastModified: String = "",
        val linkText: String = "",
        val links: LinksRelated = LinksRelated(),
        val premium: Boolean = false,
        val published: String = "",
        val title: String = "",
        val type: String = ""
    )

    data class Short(
        val href: String = ""
    )

    data class Sportscenter(
        val href: String = ""
    )

    data class Team(
        val description: String = "",
        val id: Int = 0,
        val links: LinksTeam = LinksTeam()
    )

    data class Web(
        val href: String = "",
        val short: Short? = Short()
    )

    data class WebRelated(
        val href: String = ""
    )

    data class WebTeam(
        val teams: LinksTeams = LinksTeams()
    )

}