/*
 * Copyright (c) 2019 Naman Dwivedi.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
@file:Suppress("unused")

package com.yonas.emusic

import android.app.Application
import com.yonas.emusic.BuildConfig.DEBUG
import com.yonas.emusic.db.roomModule
import com.yonas.emusic.logging.FabricTree
import com.yonas.emusic.network.lastFmModule
import com.yonas.emusic.network.lyricsModule
import com.yonas.emusic.network.networkModule
import com.yonas.emusic.notifications.notificationModule
import com.yonas.emusic.permissions.permissionsModule
import com.yonas.emusic.playback.mediaModule
import com.yonas.emusic.repository.repositoriesModule
import com.yonas.emusic.ui.viewmodels.viewModelsModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class EMusicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(FabricTree())
        }

        val modules = listOf(
            mainModule,
            permissionsModule,
            mediaModule,
            prefsModule,
            networkModule,
            roomModule,
            notificationModule,
            repositoriesModule,
            viewModelsModule,
            lyricsModule,
            lastFmModule
        )
        startKoin(
            androidContext = this,
            modules = modules
        )
    }
}
