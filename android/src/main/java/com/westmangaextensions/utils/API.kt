package com.westmangaextensions.utils

import okhttp3.OkHttpClient
import okhttp3.Request

class API {

    private val client:OkHttpClient = OkHttpClient()

    fun run(url:String):String {
      val request = Request.Builder().url(url).addHeader("referer","com.bk2020.production").build()
      val response = client.newCall(request).execute()
      return response.body()!!.string();
    }
}
