package org.example.expensewithserver

import android.app.Application
import org.example.expensewithserver.di.initKoin

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}