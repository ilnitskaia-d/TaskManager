package com.example.taskmenager.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.taskmenager.data.local.Pref
import com.example.taskmenager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etNameProfile.setText(pref.getName())
        binding.etNameProfile.addTextChangedListener {
            pref.saveName(binding.etNameProfile.text.toString())
        }

        if(pref.getImg() != null)
            binding.imgProfile.setImageURI(pref.getImg()?.toUri())

        binding.imgProfile.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        //val b = s?.toByteArray()
//        binding.imgProfile.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b?.size ?: 1))
  //      pref.safeImg(binding.imgProfile.drawToBitmap().toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == pickImage) {
            binding.imgProfile.setImageURI(data?.data)
            pref.safeImg(data?.data)
        }
    }

    companion object {
        const val pickImage = 100
    }
}