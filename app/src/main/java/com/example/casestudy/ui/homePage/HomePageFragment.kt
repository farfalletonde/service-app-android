package com.example.casestudy.ui.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.casestudy.adapter.AllServicesAdapter
import com.example.casestudy.adapter.BlogPostsAdapter
import com.example.casestudy.adapter.PopularServicesAdapter
import com.example.casestudy.databinding.FragmentHomePageBinding
import com.example.casestudy.util.StateResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment(),
    AllServicesAdapter.OnItemClickListener,
    PopularServicesAdapter.OnItemClickListener,
    BlogPostsAdapter.OnItemClickListener{

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
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        homePageContainer.visibility = View.GONE
                        apiErrorMessage.visibility = View.GONE
                    }
                }

                is StateResource.Success -> {

                    val homePageData = stateResource.data

                    binding.apply {

                        servicesRw.apply {
                            adapter = AllServicesAdapter(homePageData.all_services, this@HomePageFragment)
                            setHasFixedSize(true)
                        }
                        popularServicesRw.apply {
                            adapter = PopularServicesAdapter(requireContext(), homePageData.popular, this@HomePageFragment)
                            setHasFixedSize(true)
                        }
                        latestBlogRw.apply {
                            adapter = BlogPostsAdapter(requireContext(), homePageData.posts, this@HomePageFragment)
                            setHasFixedSize(true)
                        }

                        progressBar.visibility = View.GONE
                        homePageContainer.visibility = View.VISIBLE
                        apiErrorMessage.visibility = View.GONE
                    }

                }

                is StateResource.Error -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        homePageContainer.visibility = View.GONE
                        apiErrorMessage.visibility = View.VISIBLE

                        apiErrorMessage.setOnClickListener {
                            apiErrorTryAgain()
                        }
                    }
                }
            }

        })


        return binding.root
    }

    private fun apiErrorTryAgain() {
        requireActivity().recreate()
    }

    override fun onServiceClick(serviceId: Int) {
        requireView().findNavController()
            .navigate(HomePageFragmentDirections.actionHomePageFragmentToServiceDetailPageFragment(serviceId))
    }

    override fun onPopularServiceClick(serviceId: Int) {
        requireView().findNavController()
            .navigate(HomePageFragmentDirections.actionHomePageFragmentToServiceDetailPageFragment(serviceId))
    }

    override fun onBlogPostClick(blogLink: String) {
        requireView().findNavController()
            .navigate(HomePageFragmentDirections.actionHomePageFragmentToBlogPostPageFragment(blogLink))
    }

}