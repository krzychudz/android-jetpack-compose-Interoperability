package com.example.composeintegration.fragments.compose.people_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.annotation.ExperimentalCoilApi
import com.example.composeintegration.ComposeIntegrationApplication
import com.example.composeintegration.di.ViewModelFactory
import com.example.composeintegration.theme.AppTheme
import javax.inject.Inject

class ComposeFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by activityViewModels<ComposeFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as? ComposeIntegrationApplication)?.appComponent?.inject(this)
    }

    @ExperimentalCoilApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme() {
                    ComposeScreen(viewModel, findNavController())
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetNavigationData()
    }
}