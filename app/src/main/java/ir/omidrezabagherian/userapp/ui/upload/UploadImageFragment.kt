package ir.omidrezabagherian.userapp.ui.upload

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ir.omidrezabagherian.userapp.R
import ir.omidrezabagherian.userapp.databinding.FragmentUploadImageBinding

class UploadImageFragment : Fragment(R.layout.fragment_upload_image) {

    private lateinit var uploadImageBinding: FragmentUploadImageBinding
    private lateinit var uriImage: Uri
    private val args: UploadImageFragmentArgs by navArgs()
    private val viewModel: UploadImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadImageBinding = FragmentUploadImageBinding.bind(view)

        val getImageFromGallery = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { image ->
                uriImage = image
                uploadImageBinding.imageviewUser.setImageURI(image)
            })

        uploadImageBinding.buttonOpenFile.setOnClickListener {
            getImageFromGallery.launch("image/*")
        }

        uploadImageBinding.buttonSendImage.setOnClickListener {
            val change = context?.contentResolver?.openInputStream(uriImage)?.readBytes()
            viewModel.uploadImage(args.userID, change!!)
            viewModel.imageResponse.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
            viewModel.imageThrowable.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }

    }

}