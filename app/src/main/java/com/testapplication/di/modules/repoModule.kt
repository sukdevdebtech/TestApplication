package com.testapplication.di.modules
import com.testapplication.repository.DashBoardRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val repoModule = Kodein.Module(name = "repoModule") {

    this.bind<DashBoardRepository>() with this.provider { DashBoardRepository(this.instance()) }

}
