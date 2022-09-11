package com.example.mybottnav.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybottnav.R
import com.example.mybottnav.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root
    }


    private fun toggleLoading(loading: Boolean){
        binding?.loading?.visibility  = if(loading) View.VISIBLE else View.GONE
        binding?.home?.visibility  =if(loading) View.GONE else View.VISIBLE

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}