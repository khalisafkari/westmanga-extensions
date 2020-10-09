package com.westmangaextensions.utils

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class PostsView {

  private val url = API()

  @Throws(IOException::class)
  fun getPostsViewJSON(uri: String): ReadableMap {
      val map = WritableNativeMap()
      val html = url.run(uri)
      val document = Jsoup.parse(html)
      map.putArray("image",parseImageJSON(document.select("div[id=readerarea] p img")))
      map.putString("prev", document.select(".nextprev a[rel=prev]").eq(0).attr("href"))
      map.putString("next", document.select(".nextprev a[rel=next]").eq(0).attr("href"))
      return map
  }


  private fun parseImageJSON(elements: Elements):ReadableArray {
    val array = WritableNativeArray()
    for (image in elements) {
      array.pushString(image.attr("src"))
    }
    return array
  }
}
