package com.example.casestudy.ui.serviceDetailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.casestudy.databinding.FragmentServiceDetailPageBinding
import dagger.hilt.android.AndroidEntryPoint

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

        viewModel = ViewModelProvider(this).get(ServiceDetailPageViewModel::class.java)

        return binding.root
    }

}