package com.example.kotlinprojecttest2

import androidx.lifecycle.ViewModel
import com.example.kotlinprojecttest2.data.remote.quest.GetVKJsonResponse
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi
import com.example.kotlinprojecttest2.db.MemworDatabaseManager
import com.example.kotlinprojecttest2.utils.Constants

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.collections.ArrayList

class ResponseViewer (): ViewModel(){

    //val RetrofitClient = RetrofitModule()
    //TODO(IMPORT DOMAINS LIST FROM ACTIVITY)
    private val dbManager = MemworDatabaseManager()
    var vkResList: MutableList<Post> = ArrayList()
    //var tgDomainsList: MutableList<MutableList<String>> = ArrayList()
    //var redditDomainsList: MutableList<MutableList<String>> = ArrayList()

    private val constants = Constants()

    private var questApi: QuestApi

    init{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.vk.com/method/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        questApi = retrofit.create(QuestApi::class.java)

    }

    fun getVkInfo() {
        dbManager.getVkDomains()
    }

    fun vkConfigureRetrofit(){
        val vkCoroutineScope = CoroutineScope(Dispatchers.IO)
        vkCoroutineScope.launch {
            MemworViewModel.vkDomainsLiveData.value?.map {
                delay(1000)
                vkCoroutineScope.async {
                    //delay(Random().nextInt(3000).toLong())
                    questApi.getVkJson(
                        domain = it.domain,
                        access_token = constants.ACCESS_TOKEN,
                        count = 100,
                        ver = constants.API_VERSION
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ it1 ->
                            vkFindUrls(it1, it)
                        }, {
                            println(it.stackTrace.toString())
                        })
                }
            }?.awaitAll()
            MemworViewModel.vkPostsLiveData.setValueToVkPosts(vkResList)
        }
    }

    private fun redditConfigureRetrofit() {

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        //TODO(Add https for reddit api methods)
//        val retrofit = Retrofit.Builder()
//            .baseUrl("")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        questApi = retrofit.create(QuestApi::class.java)
//
//        compositeDisposable.add(
//            questApi.getRedditJson()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    redditFindUrls(it)
//                }, {
//                    //println(it.stackTrace.toString())
//                })
//        )
    }

    private fun telegramConfigureRetrofit() {
//
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        //TODO(Add https for telegram api methods)
//        val retrofit = Retrofit.Builder()
//            .baseUrl("")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        questApi = retrofit.create(QuestApi::class.java)
//
//        compositeDisposable.add(
//            questApi.getTelegramJson()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    telegramFindUrls(it)
//                }, {
//                    //println(it.stackTrace.toString())
//                })
//        )
    }

    private fun vkFindUrls(res: GetVKJsonResponse, domain: Domain) {
        //TODO(Add return statement)

        //val _vkResList: MutableList<Post> = ArrayList()
        res.vkResponse?.items?.forEach {
            if (it.marked_as_ads == 0) {

                val post = Post()
                post.category = domain.category
                post.author = domain.name
                post.text = it.text

                val inter_list: MutableList<String> = ArrayList()
                it.attachments?.forEach {
                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
                }

                post.images = inter_list
                if(!post.images.isNullOrEmpty()){
                    vkResList.add(post)
//                    vkResList.forEach {
//                        Log.e("FROM RP VIEWER", it.text + " " + it.author + " " + it.category + " " + it.images.toString())
//                    }
                }
            }
        }
        //MainActivity.vkPostsLiveData.addValueToVkPost(post)
    }

    //TODO(Replace string with reddit data class response)
    fun redditFindUrls(res: String) {

        //TODO(Add to QuestApi and data.remote.quest reddit methods)
        //TODO(Add return statement)



        //        res.redditResponse?.items?.forEach {
        //            if (it.marked_as_ads == 0) {
        //                val inter_list: MutableList<String> = ArrayList()
        //
        //                // Checking for empty text
        //
        //                inter_list.add(it.text)
        //
        //
        //                // Adding existing urls to intermediate list
        //                it.attachments?.forEach {
        //                      if (it.type == "photo"){
        //                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
        //                      }
        //
        //                }
        //
        //                // checking for empty intermediate list
        //                if (!inter_list.isNullOrEmpty()) {
        //                    res_list.add(inter_list)
        //                }
        //            }
        //        }

        // debug output (comment if not necessary)
        //Log.e("TAAAAG", res_list.toString())
    }

    //TODO(Replace string with telegram data class response)
     fun telegramFindUrls(res: String) {

        //TODO(Add to QuestApi and data.remote.quest telegram methods)
        //TODO(Add return statement)



        //        res.redditResponse?.items?.forEach {
        //            if (it.marked_as_ads == 0) {
        //                val inter_list: MutableList<String> = ArrayList()
        //
        //                // Checking for empty text
        //
        //                inter_list.add(it.text)
        //
        //
        //                // Adding existing urls to intermediate list
        //                it.attachments?.forEach {
        //                      if (it.type == "photo"){
        //                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
        //                      }
        //
        //                }
        //
        //                // checking for empty intermediate list
        //                if (!inter_list.isNullOrEmpty()) {
        //                    res_list.add(inter_list)
        //                }
        //            }
        //        }
        //        debug output (comment if not necessary)
        //        Log.e("TAAAAG", res_list.toString())
    }
}