package com.westmangaextensions.utils

import com.alibaba.fastjson.JSON
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeMap
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit


class API {

    private val client:OkHttpClient = OkHttpClient()

    @Throws(IOException::class)
    fun run(url: String): String {
      val request = Request.Builder().url(url).addHeader("referer","com.bk2020.production").build()
      client.newCall(request).execute().use { response ->
          if (!response.isSuccessful) throw IOException("Unexpected code $response")
          return response.body()!!.string()
      }
    }

    @Throws(IOException::class)
    fun getCountryCode(): ReadableMap {
      val map = WritableNativeMap()
      val request = Request.Builder().url("https://api-geolocation.zeit.sh").build()
      val response = client.newCall(request).execute().body()!!.string()
      val parse = JSON.parseObject(response)
      map.putString("country",parse.getString("country").toString())
      map.putString("city", parse.getString("city").toString())
      map.putDouble("lat",parse.getDouble("lat").toDouble())
      map.putDouble("lon",parse.getDouble("lon").toDouble())
      return map
    }
}
