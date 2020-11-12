package com.westmangaextensions.utils

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException
import java.lang.Exception


class Home {

  private val url = API()

  @Throws(IOException::class)
  fun getHome(page: Int? = 1): ReadableArray? {
    try {
      val array = WritableNativeArray()
      val html = url.run("https://westmanga.info/page/$page/");
      val document = Jsoup.parse(html)
      for (element in document.select(".item_1.items .item")) {  array.pushMap(parse(element)) }
      return array;
    } catch (e: IOException) {
      throw e;
    }
//      try {
//        val array = WritableNativeArray()
//        val html = url.run("https://westmanga.info/page/$page/");
//        val document = Jsoup.parse(html)
//        for (element in document.select(".item_1.items .item")) {
//          array.pushMap(parse(element))
//        }
//        return array
//      } catch (e: Exception) {
//        e.printStackTrace()
//      }
//      return null;
  }

  @Throws(IOException::class)
  fun getTotal(): Int {
    val html = url.run("https://westmanga.info/");
    val document = Jsoup.parse(html)
    val total:Int = document.select(".paginado").eq(1).select("ul li").last().select("a").attr("href").replace("\\D+".toRegex(),"").toInt()
    return total;
  }

  private fun parse(element: Element): ReadableMap {
    val root = WritableNativeMap()
    val boxinfo = WritableNativeMap()
    val fixyear = WritableNativeMap()

    //root
    root.putString("id",element.select("a").attr("href"))
    root.putString("image",element.select("a .image img").attr("src"))
    root.putBoolean("hot",element.select("a .image .hot").isEmpty())

    //boxinfo
    boxinfo.putString("tt",element.select(".boxinfo a .tt").text());
    boxinfo.putString("ttx",element.select(".boxinfo a .ttx p").text().trim())
    boxinfo.putString("imdbs",element.select(".boxinfo .cocs.imdb_r .a span").text())
    boxinfo.putString("progress",element.select(".boxinfo .cocs.imdb_r .b .bar span").attr("style").replace("\\D+".toRegex(),""))
    boxinfo.putString("score",element.select(".boxinfo .cocs.imdb_r .b .dato b").eq(0).text())
    boxinfo.putString("status",element.select(".boxinfo .cocs.imdb_r .b .dato b").eq(1).text())
    root.putString("type",element.select(".boxinfo .typepost").text())
    root.putMap("boxinfo",boxinfo)

    //fixyear
    fixyear.putString("title",element.select(".fixyear h2").text().trim())
    fixyear.putString("time",element.select(".fixyear .imdb").text().trim())
    fixyear.putString("last_id",element.select(".fixyear a ").attr("href"))
    fixyear.putString("last_title",element.select(".fixyear a .year").text().trim())
    root.putMap("fixyear",fixyear)

    return  root;
  }
}
