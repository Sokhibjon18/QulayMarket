package uz.triples.qulaymarket.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_my_profile_details.view.*
import uz.triples.qulaymarket.R


class MyProfileDetails : Fragment() {

    private val pickImage = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.fragment_my_profile_details,
            container,
            false
        )

        view.name_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeNameFragment)
        }

        view.birthday_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeBirthdayFragment)
        }

        view.email_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeEmailFragment)
        }

        view.phone_number_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePhoneNumberFragment)
        }

        view.password_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePasswordFragment)
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_profileFragment)
        }

        view.upload_image_button.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(
                Intent.createChooser(intent, "Rasm tanlang"),
                pickImage
            )
        }

        return view
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        if(requestCode == pickImage){

            val selectedImage = data?.data

            //val path = getPath(selectedImage)


            view?.user_image?.setImageURI(null)
            view?.user_image?.setImageURI(selectedImage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

//    fun getPath(uri: Uri?): String? {
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = requireActivity().managedQuery(uri, projection, null, null, null)
//            ?: return null
//        val column_index: Int =
//            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        val s: String = cursor.getString(column_index)
//        cursor.close()
//        return s
//    }
}