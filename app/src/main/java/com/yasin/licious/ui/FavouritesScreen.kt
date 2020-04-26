package com.yasin.licious.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yasin.licious.databinding.ScreenFavouritesBinding

/**
 * Created by Yasin on 26/4/20.
 * Shows favourite items based on server response
 */
class FavouritesScreen :  Fragment() {

    private lateinit var binding : ScreenFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenFavouritesBinding.inflate(inflater,container,false)
        return binding.root
    }
}