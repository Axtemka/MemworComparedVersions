package com.example.kotlinprojecttest2.db

import android.util.Log
import com.google.firebase.database.*

class MemworDatabaseManager {
    private lateinit var db: DatabaseReference
    private val listData: MutableList<String?> = ArrayList()

    val COMMUNITY_KEY: String = "Community"

    fun dataBaseInit(){
        db = FirebaseDatabase.getInstance().getReference(COMMUNITY_KEY)
    }

    fun addNewCommunity(platform: String, domain: String, name: String, category: String){
        val id = db.key.toString()
        val newCommunity = Community(id, platform, domain, name, category)
        db.push().setValue(newCommunity)
    }

    fun getDomains(): MutableList<String?> {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

                if (listData.size > 0) listData.clear()

                for (ds: DataSnapshot in dataSnapshot.children){
                    val community = ds?.getValue(Community::class.java)

                    if (community != null) {
                        listData?.add(community.domain)
                    }
                }

                Log.e("SUCCESS DATABASE TAG", listData.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("DATABASE ERROR TAG", "Failed to read value.", error.toException())
            }
        })
        return listData
    }
}
