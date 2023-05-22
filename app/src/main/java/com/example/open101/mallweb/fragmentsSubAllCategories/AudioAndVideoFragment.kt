package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentAudioAndVideoBinding


class AudioAndVideoFragment : Fragment(R.layout.fragment_audio_and_video) {

    private lateinit var binding: FragmentAudioAndVideoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAudioAndVideoBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}