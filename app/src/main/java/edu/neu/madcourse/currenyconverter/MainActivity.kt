package edu.neu.madcourse.currenyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.neu.madcourse.currenyconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}