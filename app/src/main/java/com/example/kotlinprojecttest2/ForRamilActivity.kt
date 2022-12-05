package com.example.kotlinprojecttest2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.kotlinprojecttest2.db.MemworDatabaseManager

class ForRamilActivity : AppCompatActivity() {

    private val dbManager = MemworDatabaseManager()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_ramil)
        dbManager.dataBaseInit()
        val addButton: Button = findViewById(R.id.addBtn)
        addButton.setOnClickListener {
            addDBCommunity()
        }

    }

    fun addDBCommunity(){
        val platformText: EditText = findViewById(R.id.editPlatformText)
        val domainText: EditText = findViewById(R.id.editDomainText)
        val nameText: EditText = findViewById(R.id.editNameText)
        val categoryText: EditText = findViewById(R.id.editCategoryText)

        dbManager.addNewCommunity(platformText.text.toString(), domainText.text.toString(), nameText.text.toString(), categoryText.text.toString())

    }
}