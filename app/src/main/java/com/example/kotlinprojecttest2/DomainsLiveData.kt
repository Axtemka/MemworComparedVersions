package com.example.kotlinprojecttest2

import androidx.lifecycle.LiveData

class DomainsLiveData: LiveData<MutableList<Domain>>() {

    fun setValueToVkDomains(list:MutableList<Domain>){
        value = list
    }

    fun isEmpty(): Boolean {
        if (value.isNullOrEmpty()) return true
        return false
    }
}