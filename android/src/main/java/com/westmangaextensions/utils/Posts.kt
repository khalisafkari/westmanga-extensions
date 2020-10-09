package com.westmangaextensions.utils

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class Posts {
  private val url = API()

  @Throws(IOException::class)
  fun getPosts(uri: String): ReadableMap {
    val map = WritableNativeMap()
    val mangainfo = WritableNativeMap()
    val html = url.run(uri);
    val document = Jsoup.parse(html);
    map.putString("id",uri)
    map.putString("title",document.select(".postbody .mangainfo h1").text().trim())
    map.putString("image",document.select(".postbody .mangainfo .topinfo img").attr("src"))

    //mangainfo
    mangainfo.putString("native_title",document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(0).text().trim())
    mangainfo.putString("japanese_title",document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(1).text().trim())
    mangainfo.putString("total_chapter",document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(2).text().trim())
    mangainfo.putString("status",document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(3).text().trim())
    mangainfo.putString("author",document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(4).text().trim())
    mangainfo.putArray("genre",parseMangainfoGenre(document.select(".postbody .mangainfo .topinfo .l table tbody tr td").eq(5)))
    map.putMap("mangainfo",mangainfo)
    map.putString("sinopsis",document.select(".postbody .mangainfo .sin p").text().trim())
    map.putArray("list",parseList(document.select(".postbody .mangainfo .cl ul li")))
    return map;
  }

  private fun parseMangainfoGenre(element: Elements): ReadableArray {
     val array = WritableNativeArray()
     for (genre in element.select("a")) {
       val map = WritableNativeMap()
       map.putString("id",genre.attr("href").trim())
       map.putString("title",genre.text().trim())
       array.pushMap(map)
     }
     return array;
  }

  private fun parseList(elements: Elements): ReadableArray {
     val array = WritableNativeArray()
     for (list in elements) {
       val map = WritableNativeMap();
       map.putString("id",list.select(".leftoff a").attr("href"))
       map.putString("title",list.select(".leftoff a").text().trim())
       map.putString("download",list.select(".rightoffdl a").attr("href"))
       map.putString("time",list.select(".rightoff").text())
       array.pushMap(map)
     }
     return array
  }

}
