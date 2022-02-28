package com.truecaller.data.api

import com.truecaller.data.entity.HtmlPageEntity
import retrofit2.http.GET
import retrofit2.http.Url

interface BlogWebservice {

    @GET
    suspend fun getBlogPage(@Url url: String): HtmlPageEntity

}
