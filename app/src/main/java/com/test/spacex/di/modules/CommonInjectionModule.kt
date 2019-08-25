package com.test.spacex.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class CommonInjectionModule {

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}