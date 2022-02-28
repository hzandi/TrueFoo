package com.truecaller.truefoo.blog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truecaller.data.entity.HtmlPageEntity
import com.truecaller.data.repository.blog.BlogRepository
import com.truecaller.truefoo.blog.model.BlogModel
import com.truecaller.truefoo.common.BlogContentParser
import com.truecaller.truefoo.common.BlogContentType
import com.truecaller.truefoo.common.ExceptionHumanizer
import com.truecaller.truefoo.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val blogRepository: BlogRepository,
    private val blogContentParser: BlogContentParser
) : ViewModel() {

    private val _tenthCharacterLiveData = MutableLiveData<BlogModel<ViewState<String>>>()
    private val _everyTenthCharacterLiveData = MutableLiveData<BlogModel<ViewState<String>>>()
    private val _wordCounterLiveData = MutableLiveData<BlogModel<ViewState<String>>>()
    val tenthCharacterLiveData: LiveData<BlogModel<ViewState<String>>> = _tenthCharacterLiveData
    val everyTenthCharacterLiveData: LiveData<BlogModel<ViewState<String>>> = _everyTenthCharacterLiveData
    val wordCounterLiveData: LiveData<BlogModel<ViewState<String>>> = _wordCounterLiveData

    fun getTenthCharacterBlogPost() {

        val blogModel = BlogModel<ViewState<String>>(
            null, BlogContentType.TENTH_CHAR, null, ViewState.loading()
        )
        _tenthCharacterLiveData.value = blogModel

        viewModelScope.launch {
            try {
                val measuredTime = measureTimeMillis {
                    val response: HtmlPageEntity = blogRepository.getBlogPage(
                        "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                    )
                    response.content?.let {
                        blogModel.value = blogContentParser.parse(
                            ViewState.success(it).data,
                            BlogContentType.TENTH_CHAR
                        )
                        blogModel.viewState = ViewState.success(it)
                    } ?: run {
                        throw Exception("something went wrong!")
                    }
                }
                blogModel.measuredTime = measuredTime
                _tenthCharacterLiveData.value = blogModel
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                blogModel.viewState = ViewState.error(errorMessage)
                _tenthCharacterLiveData.value = blogModel
            }
        }
    }

    fun getEveryTenthCharacterBlogPost() {

        val blogModel = BlogModel<ViewState<String>>(
            null, BlogContentType.EVERY_TENTH_CHAR, null, ViewState.loading()
        )
        _everyTenthCharacterLiveData.value = blogModel

        viewModelScope.launch {
            try {
                val measuredTime = measureTimeMillis {
                    val response: HtmlPageEntity = blogRepository.getBlogPage(
                        "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                    )
                    response.content?.let {
                        blogModel.value = blogContentParser.parse(
                            ViewState.success(it).data,
                            BlogContentType.EVERY_TENTH_CHAR
                        )
                        blogModel.viewState = ViewState.success(it)
                    } ?: run {
                        throw Exception("something went wrong!")
                    }
                }
                blogModel.measuredTime = measuredTime
                _everyTenthCharacterLiveData.value = blogModel
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                blogModel.viewState = ViewState.error(errorMessage)
                _everyTenthCharacterLiveData.value = blogModel
            }
        }
    }

    fun getWordCounterBlogPost() {

        val blogModel = BlogModel<ViewState<String>>(
            null, BlogContentType.WORD_COUNTER, null, ViewState.loading()
        )
        _wordCounterLiveData.value = blogModel

        viewModelScope.launch {
            try {
                val measuredTime = measureTimeMillis {
                    val response: HtmlPageEntity = blogRepository.getBlogPage(
                        "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                    )
                    response.content?.let {
                        blogModel.value = blogContentParser.parse(
                            ViewState.success(it).data,
                            BlogContentType.WORD_COUNTER
                        )
                        blogModel.viewState = ViewState.success(it)
                    } ?: run {
                        throw Exception("something went wrong!")
                    }
                }
                blogModel.measuredTime = measuredTime
                _wordCounterLiveData.value = blogModel
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                blogModel.viewState = ViewState.error(errorMessage)
                _wordCounterLiveData.value = blogModel
            }
        }
    }
}
