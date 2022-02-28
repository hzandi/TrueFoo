package com.truecaller.truefoo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.truecaller.truefoo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun attachBaseContext(baseContext: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(baseContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
