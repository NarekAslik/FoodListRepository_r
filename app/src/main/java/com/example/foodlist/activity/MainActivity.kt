package com.example.foodlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.example.foodlist.R
import com.example.foodlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorite_item -> {
                    findNavController(R.id.container).navigate(R.id.favoritesFragment)
                }
            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
}