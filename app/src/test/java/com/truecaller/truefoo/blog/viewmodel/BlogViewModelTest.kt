package com.truecaller.truefoo.blog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.truecaller.data.entity.HtmlPageEntity
import com.truecaller.data.repository.blog.BlogRepository
import com.truecaller.truefoo.CoroutineTestRule
import com.truecaller.truefoo.common.BlogContentParser
import com.truecaller.truefoo.common.ViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class BlogViewModelTest {

    companion object {
        private const val BLOG_URL = "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
        private const val BLOG_CONTENT_SAMPLE = "<html><body>Blog return</body></html>"
        private const val ERROR_MESSAGE = "Something went wrong"
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val successResponse = HtmlPageEntity(BLOG_CONTENT_SAMPLE)
    private val errorResponse = HtmlPageEntity(null)
    private val blogRepository = Mockito.mock(BlogRepository::class.java)
    private val blogContentParser = Mockito.mock(BlogContentParser::class.java)
    private val blogViewModel = BlogViewModel(blogRepository, blogContentParser)

    @Before
    fun setup() {
        blogViewModel.tenthCharacterLiveData.observeForever {}
    }

    @Test
    fun `when fetch blog content succeeds view state becomes DataLoadedState`() =
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

        }

    @Test
    fun `when fetch blog fails view state becomes ErrorState`() =
        coroutineTestRule.runBlockingTest {

            // Given
            Mockito.`when`(blogRepository.getBlogPage(BLOG_URL))
                .thenReturn(errorResponse)

            // When
            blogViewModel.getTenthCharacterBlogPost()

            // Then
            Mockito.verify(blogRepository).getBlogPage(BLOG_URL)

            val blogModel = blogViewModel.tenthCharacterLiveData.value
            val viewState = blogModel?.viewState
            Truth.assertThat(viewState)
                .isInstanceOf(ViewState.ErrorState::class.java)
            val errorState = viewState as ViewState.ErrorState
            Truth.assertThat(errorState.errorMessage).isEqualTo(ERROR_MESSAGE)
        }

    @Test
    fun `when there is an exception view state becomes ErrorState`() =
        coroutineTestRule.runBlockingTest {

            // Given
            Mockito.`when`(blogRepository.getBlogPage(BLOG_URL))
                .thenThrow(RuntimeException())

            // When
            blogViewModel.getTenthCharacterBlogPost()

            // Then
            Mockito.verify(blogRepository).getBlogPage(BLOG_URL)

            val blogModel = blogViewModel.tenthCharacterLiveData.value
            val viewState = blogModel?.viewState
            Truth.assertThat(viewState)
                .isInstanceOf(ViewState.ErrorState::class.java)
        }
}
