package com.example.kotlinprojecttest2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//class Vk : Fragment() {
//=======

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log

import android.widget.Button
import android.widget.EditText

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.collections.ArrayList


class Vk : Fragment() {
    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var etQuery: EditText? = null
    var btnSearch: Button? = null
    var dialog: Dialog? = null
    val API_KEY = "cdceae99421e44e1ab1d238e284c07ac"
    var mActivity: Activity? = this.activity
    private var articles = ResponseViewer()

    var vkPosts: MutableList<Post> = ArrayList()
    var vkDomains: MutableList<Domain> = ArrayList()

    lateinit var adapter: Adapter

    lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //return inflater.inflate(R.layout.fragment_vk, container, false)
        MemworViewModel.vkPostsLiveData.observe(viewLifecycleOwner) {
            vkPosts = it

//            debug output
//            vkPosts.forEach {
//                Log.e("FROM VK FRAGMENT", it.text + " " + it.author + " " + it.category + " " + it.images.toString())
//            }

        }
        MemworViewModel.vkDomainsLiveData.observe(viewLifecycleOwner) {
            vkDomains = it
            articles.vkConfigureRetrofit()
        }

        mContext= requireContext()
        swipeRefreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)


        articles.getVkInfo()
        //articles.vkConfigureRetrofit()

        etQuery = view?.findViewById<EditText>(R.id.etQuery)
        btnSearch = view?.findViewById<Button>(R.id.btnSearch)
        dialog = mActivity?.let { Dialog(it) }


            recyclerView?.layoutManager = LinearLayoutManager(mActivity)
            //val country = getCountry()

            //swipeRefreshLayout!!.setOnRefreshListener { retrieveJson("", country, API_KEY) }

        //TODO (Нужно переписать адаптер под класс Post)

//        if (vkPosts.isNotEmpty() && !MemworViewModel.vkPostsLiveData.isEmpty()){
//            swipeRefreshLayout?.setOnRefreshListener {
//                Log.e("SIZE", vkPosts.toString())
//                Log.e("SIZE", vkPosts.size.toString())
//                adapter = Adapter(mContext, vkPosts)
//                swipeRefreshLayout?.isRefreshing = false
//            }
//
//            //retrieveJson("", country, API_KEY)
//            adapter = Adapter(mContext, vkPosts)
//            recyclerView?.adapter = adapter
//            return inflater.inflate(R.layout.fragment_vk, container, false)
//        }
        //Какая-нибудь заглушка
        return inflater.inflate(R.layout.fragment_vk, container, false)

    }
/*
    fun retrieveJson(query: String?, country: String?, apiKey: String?) {
        swipeRefreshLayout!!.isRefreshing = true
        val call: Call<Headlines?>? = if (etQuery!!.text.toString() != "") {
            ApiClient.instance?.api?.getSpecificData(query, apiKey)
        } else {
            ApiClient.instance?.api?.getHeadlines(country, apiKey)
        }
        call?.enqueue(object : Callback<Headlines?> {
            override fun onResponse(call: Call<Headlines?>, response: Response<Headlines?>) {
                if (response.isSuccessful && response.body()!!.getArticles() != null) {
                    swipeRefreshLayout!!.isRefreshing = false
                    adapter = mActivity?.let { articles?.let { it1 -> Adapter(it, it1.getVKList()) } }
                    recyclerView!!.adapter = adapter
                }
            }
            override fun onFailure(call: Call<Headlines?>, t: Throwable) {
                swipeRefreshLayout!!.isRefreshing = false
                Toast.makeText(mActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun getCountry(): String {
        val locale = Locale.getDefault()
        val country = locale.country
        return country.lowercase(Locale.getDefault())
    }


 */
//>>>>>>> refs/remotes/origin/compared

    companion object {
        @JvmStatic
        fun newInstance() = Vk()
    }
}