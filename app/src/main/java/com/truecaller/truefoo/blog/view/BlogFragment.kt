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
import com.truecaller.truefoo.blog.viewmodel.BlogViewModel
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
        }
    }

    private fun observeViewModel() {
        blogViewModel.tenthCharacterLiveData
            .observe(viewLifecycleOwner) { viewState: ViewState<String> ->
                when (viewState) {
                    is ViewState.LoadingState -> showLoading()
                    is ViewState.DataLoadedState -> handleDataLoadedState(viewState)
                    is ViewState.ErrorState -> handleErrorState(viewState)
                }
            }

        blogViewModel.everyTenthCharacterLiveData
            .observe(viewLifecycleOwner) { viewState: ViewState<String> ->
                when (viewState) {
                    is ViewState.LoadingState -> showLoading()
                    is ViewState.DataLoadedState -> handleDataLoadedState(viewState)
                    is ViewState.ErrorState -> handleErrorState(viewState)
                }
            }

        blogViewModel.wordCounterLiveData
            .observe(viewLifecycleOwner) { viewState: ViewState<String> ->
                when (viewState) {
                    is ViewState.LoadingState -> showLoading()
                    is ViewState.DataLoadedState -> handleDataLoadedState(viewState)
                    is ViewState.ErrorState -> handleErrorState(viewState)
                }
            }
    }

    private fun showLoading(){
        binding.progressBar.isVisible = true
    }

    private fun handleDataLoadedState(viewState: ViewState.DataLoadedState<String>){
        binding.progressBar.isGone = true
        val blogContent = viewState.data
        binding.contentTextView.text = blogContent
    }

    private fun handleErrorState(viewState: ViewState.ErrorState<String>) {
        binding.progressBar.isGone = true
        showSnackbar(viewState.errorMessage)
    }

    @SuppressLint("ShowToast")
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
