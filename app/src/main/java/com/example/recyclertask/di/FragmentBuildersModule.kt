package com.example.recyclertask.di

import com.example.recyclertask.ui.detail.ImageDetailFragment
import com.example.recyclertask.ui.image.ImageListFragment
import com.example.recyclertask.ui.splash.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    abstract fun contributeImageListFragment(): ImageListFragment

    @ContributesAndroidInjector
    abstract fun contributeImageDetailFragment(): ImageDetailFragment
}