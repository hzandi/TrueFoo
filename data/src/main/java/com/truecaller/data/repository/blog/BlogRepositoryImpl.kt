package com.truecaller.data.repository.blog

import com.truecaller.data.api.BlogWebservice
import com.truecaller.data.entity.HtmlPageEntity
import javax.inject.Inject

class BlogRepositoryImpl
@Inject constructor(
    private val blogWebservice: BlogWebservice
) : BlogRepository {

    override suspend fun getBlogPage(url: String): HtmlPageEntity {
        return blogWebservice.getBlogPage(url)
    }

}

