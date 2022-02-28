package com.truecaller.truefoo.blog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truecaller.data.entity.HtmlPageEntity
import com.truecaller.data.repository.blog.BlogRepository
import com.truecaller.truefoo.common.ExceptionHumanizer
import com.truecaller.truefoo.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val blogRepository: BlogRepository
): ViewModel() {

    private val _tenthCharacterLiveData = MutableLiveData<ViewState<String>>()
    private val _everyTenthCharacterLiveData = MutableLiveData<ViewState<String>>()
    private val _wordCounterLiveData = MutableLiveData<ViewState<String>>()
    val tenthCharacterLiveData: LiveData<ViewState<String>> = _tenthCharacterLiveData
    val everyTenthCharacterLiveData: LiveData<ViewState<String>> = _everyTenthCharacterLiveData
    val wordCounterLiveData: LiveData<ViewState<String>> = _wordCounterLiveData

    fun getTenthCharacterBlogPost() {

        _tenthCharacterLiveData.value = ViewState.loading()

        viewModelScope.launch {

            try {
                val response: HtmlPageEntity = blogRepository.getBlogPage(
                    "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                )
                response.content?.let {
                    _tenthCharacterLiveData.value = ViewState.success(it)
                } ?: run {
                    throw Exception("something went wrong!")
                }
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                _tenthCharacterLiveData.value = ViewState.error(errorMessage)
            }

        }
    }

    fun getEveryTenthCharacterBlogPost() {

        _everyTenthCharacterLiveData.value = ViewState.loading()

        viewModelScope.launch {

            try {
                val response: HtmlPageEntity = blogRepository.getBlogPage(
                    "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                )
                response.content?.let {
                    _everyTenthCharacterLiveData.value = ViewState.success(it)
                } ?: run {
                    throw Exception("something went wrong!")
                }
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                _everyTenthCharacterLiveData.value = ViewState.error(errorMessage)
            }

        }
    }

    fun getWordCounterBlogPost() {

        _wordCounterLiveData.value = ViewState.loading()

        viewModelScope.launch {

            try {
                val response: HtmlPageEntity = blogRepository.getBlogPage(
                    "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/"
                )
                response.content?.let {
                    _wordCounterLiveData.value = ViewState.success(it)
                } ?: run {
                    throw Exception("something went wrong!")
                }
            } catch (t: Throwable) {
                val errorMessage = ExceptionHumanizer.getHumanizedErrorMessage(t)
                _wordCounterLiveData.value = ViewState.error(errorMessage)
            }

        }
    }
}
