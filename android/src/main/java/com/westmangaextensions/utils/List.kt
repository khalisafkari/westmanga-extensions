package com.westmangaextensions.utils

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class List {

  private val url = API()

  @Throws(IOException::class)
  fun getByList():ReadableArray {
    val array = WritableNativeArray()
    val html = url.run("https://westmanga.info/manga-list/?list")
    val document = Jsoup.parse(html)
    for (list in document.select(".bariskelom .jdlbar ul li a")) {
      val map = WritableNativeMap()
      map.putString("id",list.attr("href"))
      map.putString("title",list.text().trim())
      array.pushMap(map)
    }
    return array
  }

  @Throws(IOException::class)
  fun getList(link: String):ReadableMap {
    val array = WritableNativeArray()
    val mp = WritableNativeMap()
    val html = url.run(link)
    val document = Jsoup.parse(html)
    for (element in document.select(".result-search")) {
      val map = WritableNativeMap()
      map.putString("id",element.select(".kanan_search header span a").attr("href"))
      map.putString("title",element.select(".kanan_search header span a").text().trim())
      map.putString("image",element.select(".fletch .img_search img").attr("src").trim())
      map.putString("status",element.select(".kanan_search .tipu_search ul li").eq(2).select("p").text().replace("status(:)".toRegex(RegexOption.MULTILINE),"").trim())
      map.putArray("genre",parseGenre(element.select(".kanan_search .tipu_search ul li").eq(1).select("a")))
      array.pushMap(map)
    }
    mp.putArray("list",array)
    if (document.select("#paginador .paginado").size > 0) {
      if (document.select(".paginado ul li").last().select("a").attr("href").isEmpty()) {
        mp.putInt("total",document.select(".paginado ul li").last().select("a").text().toInt())
      } else {
        mp.putInt("total",document.select(".paginado ul li").last().select("a").attr("href").replace("\\D+".toRegex(RegexOption.MULTILINE),"").toInt())
      }
    } else {
        mp.putInt("total",0)
    }
    return mp
  }

  private fun parseGenre(elements: Elements): ReadableArray {
    val array = WritableNativeArray()
    for (genre in elements) {
      val map = WritableNativeMap();
      map.putString("id",genre.attr("href"))
      map.putString("title",genre.text().trim())
      array.pushMap(map)
    }
    return array
  }

}
