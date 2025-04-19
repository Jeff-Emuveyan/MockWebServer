package com.example.joke

import android.app.Application
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(HiltTestApp::class)
interface HiltTestApplication

open class HiltTestApp : Application()