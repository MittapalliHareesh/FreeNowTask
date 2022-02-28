package com.freenow.task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.freenow.task.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is MainActivity for this class. All fragments are attached with this activity.
 * By using Navigation component will switch from one fragment to other fragment.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}