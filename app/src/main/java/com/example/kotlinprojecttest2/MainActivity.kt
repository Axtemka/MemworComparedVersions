package com.example.kotlinprojecttest2

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import com.example.kotlinprojecttest2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    var dialog: Dialog? = null
    var btnAboutUs: Button? = null
    // This will be connected with fragListTitles || platformName - Vk (test platform)
    private val viewer = ResponseViewer("vk")

    private val fragList = listOf(
        Vk.newInstance(),
        Telegram.newInstance(),
        Reddit.newInstance(),
        TikTok.newInstance()
    )
    private val fragListTitles = listOf(
        "Vk",
        "Telegram",
        "Reddit",
        "TikTok"
    )
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnAboutUs = findViewById<Button>(R.id.aboutUs)
        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.ourtablayout, binding.vp2){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        dialog = Dialog(this)
        btnAboutUs!!.setOnClickListener { showDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.vk -> {
                Toast.makeText(this, "Vk", Toast.LENGTH_SHORT).show()
            }
            R.id.telegram -> {
                Toast.makeText(this, "Telegram", Toast.LENGTH_SHORT).show()
            }
            R.id.reddit -> {
                Toast.makeText(this, "Reddit", Toast.LENGTH_SHORT).show()
            }
            R.id.tiktok -> {
                Toast.makeText(this, "TikTok", Toast.LENGTH_SHORT).show()
            }
            R.id.instagram -> {
                Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun showDialog() {
        val btnClose: Button
        dialog?.setContentView(R.layout.about_us_pop_up)
        dialog!!.show()
        btnClose = dialog!!.findViewById(R.id.close)
        btnClose.setOnClickListener { dialog!!.dismiss() }
    }
}

