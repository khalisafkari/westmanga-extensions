package com.westmangaextensions

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.westmangaextensions.utils.*
import com.westmangaextensions.utils.List
import java.io.IOException

class WestmangaExtensionsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "WestmangaExtensions"
    }

    @ReactMethod
    fun multiply(page: Int,promise: Promise) {
      try {
        val data = API().getCountryCode()
        promise.resolve(data)
      } catch (e:IOException) {
        promise.reject(e)
      }
    }

    @ReactMethod
    fun getHome(page: Int,promise: Promise) {
      try {
        val data = Home().getHome(page)
        promise.resolve(data)
      } catch (e: IOException) {
        promise.reject(e)
      }
    }

    @ReactMethod
    fun TotalPageHome(promise: Promise) {
      try {
          val data = Home().getTotal()
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }

    @ReactMethod
    fun getPosts(url: String, promise: Promise) {
      try {
          val data = Posts().getPosts(url)
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }

    @ReactMethod
    fun getPostsView(url: String,promise: Promise) {
      try {
          val data = PostsView().getPostsViewJSON(url)
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }


    @ReactMethod
    fun getByList(promise: Promise) {
      try {
          val data = List().getByList()
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }

    @ReactMethod
    fun getList(url: String,promise: Promise) {
      try {
          val data = List().getList(url)
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }

    @ReactMethod
    fun getCountry(promise: Promise) {
      try {
          val data = API().getCountryCode()
          promise.resolve(data)
      } catch (e:IOException) {
          promise.reject(e)
      }
    }

}
