package com.example.casestudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.casestudy.databinding.FragmentBlogPostPageBinding

class BlogPostPageFragment : Fragment() {

    private var _binding: FragmentBlogPostPageBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBlogPostPageBinding.inflate(inflater, container, false)

        val blogUrlLink = BlogPostPageFragmentArgs
            .fromBundle(requireArguments())
            .blogPostUrl

        binding.blogWebView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(blogUrlLink)
        }
        return binding.root
    }
}