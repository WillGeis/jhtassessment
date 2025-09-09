package com.example.jhtassessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Very Stripped down MainActivity, functionality handled via fragments, Landing page is FirstFragment
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
