package com.yasin.licious.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yasin.licious.databinding.ScreenFavouritesBinding
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Created by Yasin on 26/4/20.
 * Shows favourite items based on server response
 */
class FavouritesScreen :  Fragment() {

    private lateinit var binding : ScreenFavouritesBinding
    private val favoritesAdapter : FavoritesAdapter by lazy(NONE) {
        FavoritesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenFavouritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insetWindow()
        binding.rvFavorites.adapter = favoritesAdapter
    }

    private fun insetWindow() {
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->

            binding.rvFavorites.setPadding(0, 0, 0, windowInsets.systemWindowInsetBottom)

            return@setOnApplyWindowInsetsListener windowInsets
        }

    }
}