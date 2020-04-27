package com.yasin.licious.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yasin.licious.databinding.ScreenFavouritesBinding
import com.yasin.licious.getAppComponent
import com.yasin.licious.network.ViewState
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Created by Yasin on 26/4/20.
 * Shows favourite items based on server response
 */
class FavouritesScreen :  Fragment() {

    @Inject lateinit var favoritesViewModelFactory: FavoritesViewModelFactory
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding : ScreenFavouritesBinding
    private val favoritesAdapter : FavoritesAdapter by lazy(NONE) {
        FavoritesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().injectFavoritesScreen(this)
        configureViewModel()
        observeViewState()
    }

    private fun configureViewModel() {
        favoritesViewModel = ViewModelProvider(this, favoritesViewModelFactory)[FavoritesViewModel::class.java]
    }

    private fun observeViewState() {
        favoritesViewModel.favorites.observe(this, Observer {
            when(it) {
                is ViewState.Loading -> {
                    Toast.makeText(requireContext(),"Loading..",Toast.LENGTH_SHORT).show()
                }
                is ViewState.Success -> {
                    favoritesAdapter.submitList(it.data)
                }
                is ViewState.Error ->{
                    Toast.makeText(requireContext(),"error!!",Toast.LENGTH_SHORT).show()
                }
            }
        })
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