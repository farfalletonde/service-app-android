package com.example.casestudy.ui.serviceDetailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.casestudy.R
import com.example.casestudy.adapter.AllServicesAdapter
import com.example.casestudy.adapter.BlogPostsAdapter
import com.example.casestudy.adapter.PopularServicesAdapter
import com.example.casestudy.databinding.FragmentServiceDetailPageBinding
import com.example.casestudy.util.StateResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class ServiceDetailPageFragment : Fragment() {

    private var _binding: FragmentServiceDetailPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ServiceDetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentServiceDetailPageBinding.inflate(inflater, container, false)

        //get the service id that came with the safeargs bundle
        val serviceId = ServiceDetailPageFragmentArgs
            .fromBundle(requireArguments()).serviceId

        viewModel = ViewModelProvider(this).get(ServiceDetailPageViewModel::class.java)

        viewModel.getData(serviceId).observe(viewLifecycleOwner, { stateResource ->
            when (stateResource) {

                is StateResource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.detailPageContainer.visibility = View.GONE
                }

                is StateResource.Success -> {

                    val detailPageData = stateResource.data

                    binding.apply {
                        Glide.with(requireContext()).load(detailPageData.image_url).into(serviceImage)
                        serviceNameTw.text = detailPageData.long_name
                        numberOfProsTw.text = detailPageData.pro_count.toString()
                        serviceRatingTw.text = detailPageData.average_rating.toString()
                        completedJobTw.text = detailPageData.completed_jobs_on_last_month.toString()
                    }

                    binding.progressBar.visibility = View.GONE
                    binding.detailPageContainer.visibility = View.VISIBLE

                }

                is StateResource.Error -> {
                    binding.detailPageContainer.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

}