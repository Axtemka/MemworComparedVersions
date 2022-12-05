package com.example.kotlinprojecttest2

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlinprojecttest2.data.remote.quest.GetVKJsonResponse
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi
import com.example.kotlinprojecttest2.utils.Constants

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ResponseViewer (private val platformName:String): ViewModel(){
    //val RetrofitClient = RetrofitModule()
    //TODO(IMPORT DOMAINS LIST FROM ACTIVITY)

    //var domainsList: MutableList<String?> = ArrayList()
    val constants = Constants()
    val vkResList: MutableList<MutableList<String>> = ArrayList()
    lateinit var questApi: QuestApi

    fun returnUrls() {
        vkConfigureRetrofit()
    }

    private fun vkConfigureRetrofit() {

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
        //questApi.getVkJson(domain = "memzavod1523l", access_token = "9fb466189fb466189fb46618449ca5442599fb49fb46618fce51db6b049cb80918bb78e", ver = "5.131")
        questApi = retrofit.create(QuestApi::class.java)

//        questApi = RetrofitClient.vkRetrofit().create(QuestApi::class.java)
        //TODO("ADD DOMAINS LIST")
        //domainsList.forEach { it ->
                questApi.getVkJson(domain = "memzavod1523l", access_token = constants.ACCESS_TOKEN, count = 100, ver = constants.API_VERSION)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        vkFindUrls(it)
                    }, {
                        //println(it.stackTrace.toString())
                    })
        //}
//        questApi.getVkJson(domain = "dank_memes_ayylmao", access_token = constants.ACCESS_TOKEN, count = 100, ver = constants.API_VERSION)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                vkFindUrls(it)
//            }, {
//                //println(it.stackTrace.toString())
//            })
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

    private fun vkFindUrls(res: GetVKJsonResponse) {
        //TODO(Add return statement)

        res.vkResponse?.items?.forEach {
            if (it.marked_as_ads == 0) {
                val inter_list: MutableList<String> = ArrayList()
                // Checking for empty text
                inter_list.add(it.text)
                // Adding existing urls to intermediate list
                it.attachments?.forEach {
                    //                    if (it.type == "photo"){
                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
                    //                    }
                }
                // checking for empty intermediate list
                if (!inter_list.isNullOrEmpty()) {
                    vkResList.add(inter_list)
                }
            }
        }
        // debug output (comment if not necessary)

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

    fun getVKList(): MutableList<MutableList<String>> {
        val vkResList_: MutableList<MutableList<String>> = ArrayList()
        val numbers: IntArray = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1)
        for(i in 0..10) {
            var flag: Boolean = true
            var rnds = (0..this.vkResList.size).random()
            while (flag){
                for(j in 0..9) {
                    if (rnds == numbers[j]){
                        rnds = (0..this.vkResList.size).random()
                        break
                    }
                }
                flag = false
            }
            vkResList_.add(vkResList[rnds])
        }
        return vkResList_
    }
}
