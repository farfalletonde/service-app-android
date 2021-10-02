package com.example.casestudy.ui.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.casestudy.adapter.AllServicesAdapter
import com.example.casestudy.adapter.BlogPostsAdapter
import com.example.casestudy.adapter.PopularServicesAdapter
import com.example.casestudy.databinding.FragmentHomePageBinding
import com.example.casestudy.util.StateResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)

        viewModel.getData().observe(viewLifecycleOwner, { stateResource ->

            when (stateResource) {
                is StateResource.Loading -> {
                    //progress bar
                }
                is StateResource.Success -> {

                    val homePageData = stateResource.data

                    binding.servicesRw.apply {
                        adapter = AllServicesAdapter(homePageData.all_services)
                        setHasFixedSize(true)
                    }
                    binding.popularServicesRw.apply {
                        adapter = PopularServicesAdapter(requireContext(), homePageData.popular)
                        setHasFixedSize(true)
                    }
                    binding.latestBlogRw.apply {
                        adapter = BlogPostsAdapter(requireContext(), homePageData.posts)
                        setHasFixedSize(true)
                    }
                }
                is StateResource.Error -> {

                }
            }

        })


        return binding.root
    }

}