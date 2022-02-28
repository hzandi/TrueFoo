package com.truecaller.truefoo.blog.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.truecaller.truefoo.R
import com.truecaller.truefoo.blog.model.BlogModel
import com.truecaller.truefoo.blog.viewmodel.BlogViewModel
import com.truecaller.truefoo.common.BlogContentType.*
import com.truecaller.truefoo.common.ViewState
import com.truecaller.truefoo.databinding.FragmentBlogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogFragment: Fragment() {

    private var _binding: FragmentBlogBinding? = null
    private val binding get() = _binding!!
    private val blogViewModel: BlogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.fetchCardview.setOnClickListener {
            blogViewModel.getTenthCharacterBlogPost()
            blogViewModel.getEveryTenthCharacterBlogPost()
            blogViewModel.getWordCounterBlogPost()
        }

        binding.tenthCharTitleTextView.setOnClickListener {
            toggleTenthCharContent()
        }

        binding.everyTenthCharTitleTextView.setOnClickListener {
            toggleEveryTenthCharContent()
        }

        binding.wordCounterTitleTextView.setOnClickListener {
            toggleWordCounterContent()
        }
    }

    private fun observeViewModel() {
        blogViewModel.tenthCharacterLiveData
            .observe(viewLifecycleOwner) { blogModel: BlogModel<ViewState<String>> ->
                when (blogModel.viewState) {
                    is ViewState.LoadingState -> showLoading(blogModel)
                    is ViewState.DataLoadedState -> handleDataLoadedState(blogModel)
                    is ViewState.ErrorState -> handleErrorState(blogModel)
                }
            }

        blogViewModel.everyTenthCharacterLiveData
            .observe(viewLifecycleOwner) { blogModel: BlogModel<ViewState<String>> ->
                when (blogModel.viewState) {
                    is ViewState.LoadingState -> showLoading(blogModel)
                    is ViewState.DataLoadedState -> handleDataLoadedState(blogModel)
                    is ViewState.ErrorState -> handleErrorState(blogModel)
                }
            }

        blogViewModel.wordCounterLiveData
            .observe(viewLifecycleOwner) { blogModel: BlogModel<ViewState<String>> ->
                when (blogModel.viewState) {
                    is ViewState.LoadingState -> showLoading(blogModel)
                    is ViewState.DataLoadedState -> handleDataLoadedState(blogModel)
                    is ViewState.ErrorState -> handleErrorState(blogModel)
                }
            }
    }

    private fun showLoading(blogModel: BlogModel<ViewState<String>>){
        when (blogModel.type) {
            TENTH_CHAR -> {
                binding.tenthCharProgressBar.isVisible = true
                binding.tenthCharSuccessImageView.isGone = true
                binding.tenthCharMeasuredTimeTextView.isGone = true
            }
            EVERY_TENTH_CHAR -> {
                binding.everyTenthCharProgressBar.isVisible = true
                binding.everyTenthCharSuccessImageView.isGone = true
                binding.everyTenthCharMeasuredTimeTextView.isGone = true
            }
            WORD_COUNTER -> {
                binding.wordCounterProgressBar.isVisible = true
                binding.wordCounterSuccessImageView.isGone = true
                binding.wordCounterMeasuredTimeTextView.isGone = true
            }
        }
    }

    private fun handleDataLoadedState(blogModel: BlogModel<ViewState<String>>){
        when (blogModel.type) {
            TENTH_CHAR -> {
                binding.tenthCharProgressBar.isGone = true
                binding.tenthCharSuccessImageView.isVisible = true
                binding.tenthCharArrowDownImageView.isVisible = true
                binding.tenthCharArrowDownImageView.scaleY = 1f
                binding.tenthCharContentTextView.text = blogModel.value
                binding.tenthCharMeasuredTimeTextView.isVisible = true
                val measuredTime = blogModel.measuredTime.toString() + " ms"
                binding.tenthCharMeasuredTimeTextView.text = measuredTime
            }
            EVERY_TENTH_CHAR -> {
                binding.everyTenthCharProgressBar.isGone = true
                binding.everyTenthCharSuccessImageView.isVisible = true
                binding.everyTenthCharArrowDownImageView.isVisible = true
                binding.everyTenthCharArrowDownImageView.scaleY = 1f
                binding.everyTenthCharContentTextView.text = blogModel.value
                binding.everyTenthCharMeasuredTimeTextView.isVisible = true
                val measuredTime = blogModel.measuredTime.toString() + " ms"
                binding.everyTenthCharMeasuredTimeTextView.text = measuredTime
            }
            WORD_COUNTER -> {
                binding.wordCounterProgressBar.isGone = true
                binding.wordCounterSuccessImageView.isVisible = true
                binding.wordCounterArrowDownImageView.isVisible = true
                binding.wordCounterArrowDownImageView.scaleY = 1f
                binding.wordCounterContentTextView.text = blogModel.value
                binding.wordCounterMeasuredTimeTextView.isVisible = true
                val measuredTime = blogModel.measuredTime.toString() + " ms"
                binding.wordCounterMeasuredTimeTextView.text = measuredTime
            }
        }
    }

    private fun handleErrorState(blogModel: BlogModel<ViewState<String>>) {
        when (blogModel.type) {
            TENTH_CHAR -> {
                binding.tenthCharProgressBar.isGone = true
                binding.tenthCharSuccessImageView.isGone = true
                binding.tenthCharArrowDownImageView.isGone = true
                binding.tenthCharMeasuredTimeTextView.isGone = true
                blogModel.viewState?.let {
                    showSnackbar((it as ViewState.ErrorState<String>).errorMessage)
                }
            }
            EVERY_TENTH_CHAR -> {
                binding.everyTenthCharProgressBar.isGone = true
                binding.everyTenthCharSuccessImageView.isGone = true
                binding.everyTenthCharArrowDownImageView.isGone = true
                binding.everyTenthCharMeasuredTimeTextView.isGone = true
                blogModel.viewState?.let {
                    showSnackbar((it as ViewState.ErrorState<String>).errorMessage)
                }
            }
            WORD_COUNTER -> {
                binding.wordCounterProgressBar.isGone = true
                binding.wordCounterSuccessImageView.isGone = true
                binding.wordCounterArrowDownImageView.isGone = true
                binding.wordCounterMeasuredTimeTextView.isGone = true
                blogModel.viewState?.let {
                    showSnackbar((it as ViewState.ErrorState<String>).errorMessage)
                }
            }
        }
    }

    private fun toggleTenthCharContent(){
        if(binding.tenthCharContentTextView.isVisible){
            binding.tenthCharContentTextView.isGone = true
            binding.tenthCharArrowDownImageView.scaleY = 1f
        }else{
            binding.tenthCharContentTextView.isVisible = true
            binding.tenthCharArrowDownImageView.scaleY = -1f
        }
    }

    private fun toggleEveryTenthCharContent(){
        if(binding.everyTenthCharContentTextView.isVisible){
            binding.everyTenthCharContentTextView.isGone = true
            binding.everyTenthCharArrowDownImageView.scaleY = 1f
        }else{
            binding.everyTenthCharContentTextView.isVisible = true
            binding.everyTenthCharArrowDownImageView.scaleY = -1f
        }
    }

    private fun toggleWordCounterContent(){
        if(binding.wordCounterContentTextView.isVisible){
            binding.wordCounterContentTextView.isGone = true
            binding.wordCounterArrowDownImageView.scaleY = 1f
        }else{
            binding.wordCounterContentTextView.isVisible = true
            binding.wordCounterArrowDownImageView.scaleY = -1f
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) {
                blogViewModel.getTenthCharacterBlogPost()
                blogViewModel.getEveryTenthCharacterBlogPost()
                blogViewModel.getWordCounterBlogPost()
            }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
