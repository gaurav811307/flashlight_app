package com.example.flashlightapp

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashlightapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.button.setOnClickListener(){

            if(binding.button.text == "Turn ON") {
                binding.button.setText("Turn OFF")
                binding.flashImage.setImageResource(R.drawable.flash_on)
                changeLightState(true)
            }
            else
            {
                binding.button.setText("Turn ON")
                binding.flashImage.setImageResource(R.drawable.flash_off)
                changeLightState(false)
            }
        }

    }

    private fun changeLightState(state : Boolean) {
        var cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var cameraId = ""
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, state)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.button.setText("Turn ON")
    }
}