package com.example.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ListFragment>(R.id.fragment_container)
            }
        }
    }

    override fun onItemSelected(item: String) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<DetailsFragment>(R.id.fragment_container, null, Bundle().apply {
                putString("selectedItem", item)
            })
            addToBackStack(null)
        }
    }
}