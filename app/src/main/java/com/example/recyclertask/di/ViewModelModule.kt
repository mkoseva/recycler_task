package com.example.recyclertask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclertask.ui.image.ImageViewModel

import com.example.recyclertask.viewmodel.TaskApiViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    abstract fun bindImageViewModel(imageViewModel: ImageViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: TaskApiViewModelFactory): ViewModelProvider.Factory
}