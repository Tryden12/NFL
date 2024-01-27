package com.tryden.simplenfl.usecase

import com.tryden.simplenfl.data.repository.DataRepositoryImpl
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.domain.newmapper.HeadlinesMapper
import com.tryden.simplenfl.domain.usecase.news.allNews.AllNewsUseCase
import com.tryden.simplenfl.utils.CoroutineTestRule
import io.kotest.inspectors.runTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AllNewsUseCaseTest {

    lateinit var allNewsUseCase: AllNewsUseCase
    lateinit var mapper: HeadlinesMapper

    private val dataRepository = mockk<DataRepositoryImpl>()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        mapper = HeadlinesMapper()
        allNewsUseCase = AllNewsUseCase(dataRepository, mapper)
    }

    @Test
    fun fetch_headlineNews() = runTest {
        // given
        val mockHeadlines = mockk<List<Headline>>(relaxed = true)

        every {
//            dataRepository.getNews("", "")
        }
    }

}