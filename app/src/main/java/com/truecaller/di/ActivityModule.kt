package com.truecaller.di

import com.truecaller.data.api.BlogWebservice
import com.truecaller.data.api.WebserviceFactory
import com.truecaller.data.repository.blog.BlogRepository
import com.truecaller.data.repository.blog.BlogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ActivityModule {

    @Provides
    fun provideBlogWebService(webserviceFactory: WebserviceFactory): BlogWebservice {
        return webserviceFactory.createService(BlogWebservice::class.java)
    }

    @Provides
    fun provideBlogRepository(userRepositoryImpl: BlogRepositoryImpl): BlogRepository {
        return userRepositoryImpl
    }

}
