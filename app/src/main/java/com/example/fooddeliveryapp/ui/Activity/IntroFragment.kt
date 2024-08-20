package com.example.fooddeliveryapp.ui.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepository
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.FragmentIntroBinding



class IntroFragment : Fragment(R.layout.fragment_intro) {

    private lateinit var binding: FragmentIntroBinding

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroBinding.bind(view)

        binding.getStartedBtn.setOnClickListener {


            view.findNavController().navigate(R.id.action_mainFragment_to_menuFragment)

        }
    }
}