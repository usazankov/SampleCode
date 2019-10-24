package ru.sample.presentation.view.activity

import android.os.Bundle
import ru.sample.presentation.R
import ru.sample.presentation.internal.di.components.DaggerSoftPosComponent
import ru.sample.presentation.internal.di.components.SoftPosComponent

/**
 * Main application screen. This is the app entry point.
 */
class MainActivity: BaseActivity(){

    private lateinit var softPosComponent: SoftPosComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        initializeInjector()
        applicationComponent.inject(this)
    }

    private fun initializeInjector() {
        this.softPosComponent = DaggerSoftPosComponent.builder()
            .applicationComponent(applicationComponent)
            .activityModule(activityModule)
            .build()
    }
}