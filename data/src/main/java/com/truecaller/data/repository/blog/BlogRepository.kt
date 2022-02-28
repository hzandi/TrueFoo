package com.truecaller.data.repository.blog


import com.truecaller.data.entity.HtmlPageEntity
import com.truecaller.data.entity.Response

interface BlogRepository {

    suspend fun getBlogPage(url: String): HtmlPageEntity

}
