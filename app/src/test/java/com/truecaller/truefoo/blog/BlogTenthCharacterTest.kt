package com.truecaller.truefoo.blog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.truecaller.data.entity.HtmlPageEntity
import com.truecaller.data.repository.blog.BlogRepository
import com.truecaller.truefoo.CoroutineTestRule
import com.truecaller.truefoo.blog.viewmodel.BlogViewModel
import com.truecaller.truefoo.common.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class BlogTenthCharacterTest {

    companion object {
        private const val BLOG_URL = "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
        private const val BLOG_CONTENT_SAMPLE = "<html><body>Blog return</body></html>"
        private const val BLOG_CONTENT_SAMPLE_SHORT = "<html>"
        private const val CHAR = "d"
        private const val EMPTY = ""
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val successResponse = HtmlPageEntity(BLOG_CONTENT_SAMPLE)
    private val successResponseWithShortContent = HtmlPageEntity(BLOG_CONTENT_SAMPLE_SHORT)
    private val errorResponse = HtmlPageEntity(null)
    private val blogRepository = Mockito.mock(BlogRepository::class.java)
    private var blogContentParser = Mockito.mock(BlogContentParser::class.java)
    private var blogViewModel = Mockito.mock(BlogViewModel::class.java)

    @Before
    fun setup() {
        blogContentParser = BlogContentParser(
            TenthCharacterOfContent(),
            EveryTenthCharacterOfContent(),
            WordCounterOfContent()
        )
        blogViewModel = BlogViewModel(blogRepository, blogContentParser)
        blogViewModel.tenthCharacterLiveData.observeForever {}
    }

    @Test
    fun `call tenth character parser succeeds view state becomes DataLoadedState and value`() =
        coroutineTestRule.runBlockingTest {
            // Given
            Mockito.`when`(blogRepository.getBlogPage(BLOG_URL))
                .thenReturn(successResponse)

            // When
            blogViewModel.getTenthCharacterBlogPost()

            // Then
            Mockito.verify(blogRepository).getBlogPage(BLOG_URL)
            val blogModel = blogViewModel.tenthCharacterLiveData.value
            val viewState = blogModel?.viewState
            Truth.assertThat(viewState)
                .isInstanceOf(ViewState.DataLoadedState::class.java)
            val value = blogModel?.value
            assertNotNull(value)
            assertEquals(value?.length, 1)
            assertEquals(value, CHAR)
            Truth.assertThat(value)
                .isInstanceOf(String::class.java)
        }

    @Test
    fun `call tenth character parser returned view state becomes DataLoadedState and empty value`() =
        coroutineTestRule.runBlockingTest {
            // Given
            Mockito.`when`(blogRepository.getBlogPage(BLOG_URL))
                .thenReturn(successResponseWithShortContent)

            // When
            blogViewModel.getTenthCharacterBlogPost()

            // Then
            Mockito.verify(blogRepository).getBlogPage(BLOG_URL)
            val blogModel = blogViewModel.tenthCharacterLiveData.value
            val viewState = blogModel?.viewState
            Truth.assertThat(viewState)
                .isInstanceOf(ViewState.DataLoadedState::class.java)
            val value = blogModel?.value
            assertNotNull(value)
            assertEquals(value?.length, 0)
            assertEquals(value, EMPTY)
            Truth.assertThat(value)
                .isInstanceOf(String::class.java)
        }

}
