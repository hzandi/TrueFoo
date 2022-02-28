package com.truecaller.truefoo.blog.model

import com.truecaller.truefoo.common.BlogContentType

data class BlogModel<T>(
    var value: String?,
    var type: BlogContentType?,
    var measuredTime: Long?,
    var viewState: T?
)
