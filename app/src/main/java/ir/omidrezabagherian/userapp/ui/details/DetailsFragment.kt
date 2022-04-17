package ir.omidrezabagherian.userapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ir.omidrezabagherian.userapp.R
import ir.omidrezabagherian.userapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsBinding = FragmentDetailsBinding.bind(view)

        viewModel.name = args.name
        viewModel.family = args.family
        viewModel.nationalCode = args.nationalCode

        detailsBinding.textFirstName.text = viewModel.name
        detailsBinding.textLastName.text = viewModel.family
        detailsBinding.textNationalCode.text = viewModel.nationalCode

    }

}