package com.yasin.licious.ui.favorites

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import com.yasin.licious.R
import com.yasin.licious.data.utils.FILTER_ALL
import com.yasin.licious.data.utils.FILTER_EXPRESS
import com.yasin.licious.databinding.ScreenFavouritesBinding
import com.yasin.licious.getAppComponent
import com.yasin.licious.ui.utils.themeColor
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Created by Yasin on 26/4/20.
 * Shows favourite items based on server response
 */
class FavouritesScreen : Fragment() {

    @Inject lateinit var picasso: Picasso
    @Inject lateinit var favoritesViewModelFactory: FavoritesViewModelFactory
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: ScreenFavouritesBinding
    private val favoritesAdapter: FavoritesAdapter by lazy(NONE) {
        FavoritesAdapter(picasso)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().injectFavoritesScreen(this)
        configureViewModel()
        observeViewState()
    }

    private fun configureViewModel() {
        favoritesViewModel =
            ViewModelProvider(this, favoritesViewModelFactory)[FavoritesViewModel::class.java]
    }

    private fun observeViewState() {
        observeFavorites()
    }

    private fun observeFavorites() {
        favoritesViewModel.favoritesViewState.observe(this, Observer {
            when (it) {
                is FavoriteViewState.Loading -> {
                    //show loading state
                }
                is FavoriteViewState.Success -> {
                    renderScreen(it)
                }
                is FavoriteViewState.Error -> {
                    //show error state
                }
            }
        })
    }

    private fun renderScreen(viewState: FavoriteViewState.Success) {
        binding.tvInfoMessage.text = viewState.infoMessage
        binding.tvInfoBadge.text = String.format("(%s)", viewState.badge)
        favoritesAdapter.submitList(viewState.favorites)
        //set screen title via navController
        findNavController().currentDestination?.label = viewState.screenTitle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insetWindow()
        init()
    }

    private fun init() {
        binding.rvFavorites.adapter = favoritesAdapter
        binding.buttonAllSlots.setOnClickListener {
            selectButton(binding.buttonAllSlots)
            unSelectButton(binding.buttonExpress)
            favoritesViewModel.filterFavorites(FILTER_ALL)
        }
        binding.buttonExpress.setOnClickListener {
            selectButton(binding.buttonExpress)
            unSelectButton(binding.buttonAllSlots)
            favoritesViewModel.filterFavorites(FILTER_EXPRESS)
        }
    }

    private fun unSelectButton(materialButton: MaterialButton) {
        materialButton.backgroundTintList =
            ColorStateList.valueOf(requireContext().themeColor(R.attr.colorPrimary))
        materialButton.setTextColor(ColorStateList.valueOf(requireContext().themeColor(R.attr.colorOnPrimary)))
        materialButton.strokeColor =
            ColorStateList.valueOf(requireContext().themeColor(R.attr.colorOnPrimary))
    }

    private fun selectButton(materialButton: MaterialButton) {
        materialButton.backgroundTintList =
            ColorStateList.valueOf(requireContext().themeColor(R.attr.colorSecondary))
        materialButton.setTextColor(ColorStateList.valueOf(requireContext().themeColor(R.attr.colorOnSecondary)))
    }

    private fun insetWindow() {
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->

            binding.rvFavorites.setPadding(windowInsets.systemWindowInsetLeft, 0, windowInsets.systemWindowInsetRight, windowInsets.systemWindowInsetBottom)
            binding.appBar.setPadding(windowInsets.systemWindowInsetLeft,0,windowInsets.systemWindowInsetRight,0)

            return@setOnApplyWindowInsetsListener windowInsets
        }

    }
}