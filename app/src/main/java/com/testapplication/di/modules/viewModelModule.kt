package com.testapplication.di.modules

import androidx.lifecycle.ViewModelProvider
import com.testapplication.utils.ViewModelFactory
import com.testapplication.utils.bindViewModel
import com.testapplication.viewModel.DashBoardViewModel

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val viewModelModule = Kodein.Module(name = "viewModelModule") {
    this.bind<ViewModelProvider.Factory>() with this.singleton { ViewModelFactory(dkodein) }

    this.bindViewModel<DashBoardViewModel>() with this.provider { DashBoardViewModel(this.instance()) }


}
