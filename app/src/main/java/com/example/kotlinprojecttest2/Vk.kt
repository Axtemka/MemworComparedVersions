package com.example.kotlinprojecttest2


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import okhttp3.internal.platform.Platform
import java.util.*


class Vk : Fragment() {
    var recyclerView: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var etQuery: EditText? = null
    var btnSearch: Button? = null
    var dialog: Dialog? = null
    val API_KEY = "cdceae99421e44e1ab1d238e284c07ac"
    var mActivity: Activity? = this.activity
    lateinit var articles: ResponseViewer
    lateinit var adapter: Adapter
    private var vkResList: MutableList<MutableList<String>> = ArrayList()
    lateinit var mContext: Context
    private val platform: String = "vk"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        articles = ResponseViewer(platform)
        mContext= requireContext()
        swipeRefreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        vkResList = articles.getVKList()

/*
        etQuery = view?.findViewById<EditText>(R.id.etQuery)
        btnSearch = view?.findViewById<Button>(R.id.btnSearch)
        dialog = mActivity?.let { Dialog(it) }


 */
        recyclerView?.layoutManager = LinearLayoutManager(mActivity)
        //val country = getCountry()

        //swipeRefreshLayout!!.setOnRefreshListener { retrieveJson("", country, API_KEY) }


        swipeRefreshLayout!!.setOnRefreshListener {
            vkResList = articles.getVKList()
            adapter = Adapter(mContext, vkResList)
            swipeRefreshLayout?.isRefreshing = false
        }



        //retrieveJson("", country, API_KEY)
        adapter = Adapter(mContext, vkResList)
        recyclerView?.adapter = adapter

        btnSearch?.setOnClickListener {

        }
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

    companion object {
        @JvmStatic
        fun newInstance() = Vk()
    }
}