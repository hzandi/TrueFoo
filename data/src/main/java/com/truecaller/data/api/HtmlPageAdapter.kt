package com.truecaller.data.api

import com.truecaller.data.entity.HtmlPageEntity
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class HtmlPageAdapter : Converter<ResponseBody, HtmlPageEntity> {

    companion object {
        val FACTORY: Converter.Factory = object : Converter.Factory() {
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ): Converter<ResponseBody, *>? {
                return if (type == HtmlPageEntity::class.java) HtmlPageAdapter() else null
            }
        }
    }

    override fun convert(responseBody: ResponseBody): HtmlPageEntity {
        val document: Document = Jsoup.parse(responseBody.byteStream(), "UTF-8", "")
        val value: Element = document.body()
        val content: String = value.html()
        return HtmlPageEntity(content)
    }

}

