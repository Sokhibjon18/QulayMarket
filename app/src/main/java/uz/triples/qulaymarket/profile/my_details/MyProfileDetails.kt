package uz.triples.qulaymarket.profile.my_details

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_my_profile_details.view.*
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.databinding.FragmentMyProfileDetailsBinding
import uz.triples.qulaymarket.utils.Status
import uz.triples.qulaymarket.utils.gone
import uz.triples.qulaymarket.utils.visible


class MyProfileDetails : Fragment() {

    private lateinit var binding: FragmentMyProfileDetailsBinding
    private val viewModel: MyDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(MyDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMyProfileDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        binding.nameContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeNameFragment)
        }

        binding.birthdayContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeBirthdayFragment, bundleOf("birthdate" to binding.birthday.text))
        }

        binding.emailContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeEmailFragment)
        }

        binding.phoneNumberContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePhoneNumberFragment)
        }

        binding.passwordContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePasswordFragment)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_profileFragment)
        }

        binding.uploadImageButton.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(
                Intent.createChooser(intent, "Rasm tanlang"),
                111
            )
        }

//        binding.uploadImageButton.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
//        }

        viewModel.uploadStatus.observe(viewLifecycleOwner){
            when (it) {
                Status.LOADING -> {
                    binding.refreshSpinner.apply {
                        visible()
                        start()
                    }
                }
                Status.DONE -> {
                    binding.refreshSpinner.apply {
                        gone()
                        stop()
                    }
                }
                else -> {
                    binding.refreshSpinner.apply {
                        gone()
                        stop()
                        Toast.makeText(
                            requireContext(),
                            "Xatolik yuz berdi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        return binding.root
    }



    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 111){
            val fileUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver,
                    fileUri
                )


            binding.userImage.setImageBitmap(bitmap)
            viewModel.uploadImage(bitmap)
        }

//        if (resultCode == Activity.RESULT_OK) {
//            //Image Uri will not be null for RESULT_OK
//            val fileUri = data?.data
//
//            try {
//                val bitmap = MediaStore.Images.Media.getBitmap(
//                    requireActivity().contentResolver,
//                    fileUri
//                )
//                viewModel.uploadImage(bitmap)
//                binding.userImage.setImageBitmap(bitmap)
//            } catch (e:Exception){
//                Toast.makeText(requireContext(), "Xatolik yuz berdi", Toast.LENGTH_SHORT).show()
//            }
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(
//                requireContext(),
//                ImagePicker.getError(data),
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            Toast.makeText(
//                requireContext(),
//                "Task Cancelled",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }
}