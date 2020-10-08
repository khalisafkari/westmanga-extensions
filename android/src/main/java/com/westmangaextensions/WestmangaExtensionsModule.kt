package com.westmangaextensions

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.westmangaextensions.utils.Home
import java.io.IOException

class WestmangaExtensionsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "WestmangaExtensions"
    }

    @ReactMethod
    fun multiply(promise: Promise) {
      try {
        val data = Home().getTotal()
        promise.resolve(data)
      } catch (e:IOException) {
        promise.reject(e)
      }
    }

    @ReactMethod
    fun getHome(promise: Promise) {
      try {
        val data = Home().getHome()
        promise.resolve(data)
      } catch (e:IOException) {
        promise.resolve(e)
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

}
