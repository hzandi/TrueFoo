package com.truecaller.data.repository.blog


import com.truecaller.data.entity.HtmlPageEntity

interface BlogRepository {

    suspend fun getBlogPage(url: String): HtmlPageEntity
}
