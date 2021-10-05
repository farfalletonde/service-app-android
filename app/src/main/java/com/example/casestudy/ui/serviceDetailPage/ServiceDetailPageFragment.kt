package com.example.casestudy.ui.serviceDetailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.casestudy.R
import com.example.casestudy.databinding.FragmentServiceDetailPageBinding
import com.example.casestudy.util.StateResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import java.net.UnknownHostException

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

        lifecycleScope.launchWhenStarted {

            viewModel.getData(serviceId).collect {

                when (it) {

                    is StateResource.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            detailPageContainer.visibility = View.GONE
                            apiErrorMessage.visibility = View.GONE
                        }
                    }

                    is StateResource.Success -> {

                        val detailPageData = it.data

                        binding.apply {
                            Glide.with(requireContext()).load(detailPageData.image_url)
                                .thumbnail(0.1f)
                                .into(serviceImage)
                            serviceNameTw.text = detailPageData.long_name
                            numberOfProsTw.text = detailPageData.pro_count.toString()
                            serviceRatingTw.text = detailPageData.average_rating.toString()
                            completedJobTw.text =
                                detailPageData.completed_jobs_on_last_month.toString()

                            progressBar.visibility = View.GONE
                            detailPageContainer.visibility = View.VISIBLE
                            apiErrorMessage.visibility = View.GONE
                        }


                    }

                    is StateResource.Error -> {

                        binding.apply {
                            progressBar.visibility = View.GONE
                            detailPageContainer.visibility = View.GONE
                            apiErrorMessage.visibility = View.VISIBLE
                        }

                        //Error handling
                        when (it.e) {

                            //If data is not available
                            is HttpException -> binding.apiErrorMessage.text =
                                resources.getText(R.string.not_found)

                            //If there is no internet
                            is UnknownHostException -> {
                                binding.apiErrorMessage.setOnClickListener {
                                    apiErrorTryAgain()
                                }
                            }

                            else -> {
                                binding.apiErrorMessage.apply {
                                    text = resources.getText(R.string.unknown_error)
                                    setOnClickListener {
                                        apiErrorTryAgain()
                                    }
                                }
                            }

                        }

                    }
                }
            }
        }

        return binding.root
    }

    private fun apiErrorTryAgain() {
        requireActivity().recreate()
    }

}