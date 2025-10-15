package com.example.testeandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testeandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }

    fun navigateTo(
        fragment: androidx.fragment.app.Fragment,
        addToBackStack: Boolean = true,
        clearBackStack: Boolean = false
    ) {
        if (clearBackStack) {
            supportFragmentManager.popBackStack(
                null,
                androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        val t = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack) t.addToBackStack(null)
        t.commit()
    }



}
