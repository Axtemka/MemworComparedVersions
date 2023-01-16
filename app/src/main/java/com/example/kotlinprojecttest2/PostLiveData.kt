package com.example.kotlinprojecttest2

import androidx.lifecycle.LiveData

class PostLiveData: LiveData<MutableList<Post>>() {

    fun setValueToVkPosts(list:MutableList<Post>){
        postValue(list)
    }

    fun isEmpty(): Boolean {
        if (value.isNullOrEmpty()) return true
        return false
    }
}