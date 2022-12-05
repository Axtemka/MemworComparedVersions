package com.example.kotlinprojecttest2

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
//<<<<<<< HEAD
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity;
import com.example.kotlinprojecttest2.databinding.ActivityMainBinding
import com.example.kotlinprojecttest2.db.MemworDatabaseManager
//=======
import android.widget.Button
//>>>>>>> refs/remotes/origin/compared
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    public var domainsList: MutableList<String?> = ArrayList()
    var resp: MutableList<MutableList<String>> = ArrayList()
    // This will be connected with fragListTitles || platformName - Vk (test platform)
    val viewer = ResponseViewer("vk")
    private val dbManager = MemworDatabaseManager()
//=======
    var dialog: Dialog? = null
    var btnAboutUs: Button? = null
//>>>>>>> refs/remotes/origin/compared

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

        dataBaseCheck()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnAboutUs = findViewById<Button>(R.id.aboutUs)
//>>>>>>> refs/remotes/origin/compared
        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.ourtablayout, binding.vp2){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        dialog = Dialog(this)
        btnAboutUs!!.setOnClickListener { showDialog() }
//>>>>>>> refs/remotes/origin/compared
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

    fun vkParseOnClick(view: View){
        setContentView(R.layout.activity_main)
        //TODO(Different platform`s parsing from platform names (Reddit, Telegram))
        //viewer.returnUrls()


    }
    fun showDialog() {
        val btnClose: Button
        dialog?.setContentView(R.layout.about_us_pop_up)
        dialog!!.show()
        btnClose = dialog!!.findViewById(R.id.close_)
        btnClose.setOnClickListener { dialog!!.dismiss() }
//>>>>>>> refs/remotes/origin/compared
    }


    fun dataBaseCheck(){
        //dbManager.dataBaseInit()
        //dbManager.addNewCommunity("vk", "test-domain", "test-name", "test-category")
        viewer.returnUrls()

        resp = viewer.getVKList()

        //domainsList = dbManager.getDomains()
    }

}


