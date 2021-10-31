package com.example.nasaimages.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasaimages.R
import com.example.nasaimages.di.components.DaggerMainActivityComponent
import com.example.nasaimages.di.components.MainActivityComponent
import com.example.nasaimages.presentation.view.MainFragment
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    lateinit var mainActivityComponent: MainActivityComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .activity(this)
                .build()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}