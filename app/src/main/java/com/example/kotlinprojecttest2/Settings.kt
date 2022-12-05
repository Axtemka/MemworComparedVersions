package com.example.kotlinprojecttest2

import android.annotation.SuppressLint
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

class Settings : AppCompatActivity() {
    var dialog: Dialog? = null
    var btnSettings: Button? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        btnSettings = findViewById<Button>(R.id.close_)
        dialog = Dialog(this)
        btnSettings!!.setOnClickListener { showDialog() }
    }
    fun showDialog() {
        val btnClose: Button
        dialog?.setContentView(R.layout.about_us_pop_up)
        dialog!!.show()
        btnClose = dialog!!.findViewById(R.id.close_)
        btnClose.setOnClickListener { dialog!!.dismiss() }
    }
}