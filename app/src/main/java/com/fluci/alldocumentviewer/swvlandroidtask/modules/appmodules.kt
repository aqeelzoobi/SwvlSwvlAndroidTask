package com.fluci.alldocumentviewer.swvlandroidtask.modules

import com.fluci.alldocumentviewer.swvlandroidtask.datarepositories.MoviesRepositories
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.MainActivityViewModal
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.MovieDetailViewModal
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.SearchActivityViewModel
import org.koin.dsl.module

val appmodules= module {
    /*
    * first we init Repositories
    * */
    single { MoviesRepositories(get()) }

    /*
    create instance of viewsmodals
    */
    single { MainActivityViewModal(get(),get()) }
    single { SearchActivityViewModel(get()) }
    single { MovieDetailViewModal(get()) }
}