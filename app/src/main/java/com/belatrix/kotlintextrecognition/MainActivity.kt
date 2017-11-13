package com.belatrix.kotlintextrecognition

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceView
import android.widget.TextView
import com.belatrix.kotlintextrecognition.camera.SurfaceHolderCallback
import com.belatrix.kotlintextrecognition.processor.TextBlockDetector
import com.belatrix.kotlintextrecognition.processor.TextProcessingListener
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.text.TextRecognizer

/**
 * Created by Luis Alonso on 13/11/2017
 */

class MainActivity : AppCompatActivity(), TextProcessingListener {

    private lateinit var recognizer: TextRecognizer
    private var cameraSource: CameraSource? = null

    private lateinit var cameraView: SurfaceView
    private lateinit var readTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraView = findViewById(R.id.camera_view)
        readTextView = findViewById(R.id.read_text_view)

        this.loadCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource?.release()
    }

    private fun loadCamera() {
        recognizer = TextRecognizer.Builder(applicationContext).build()
        recognizer.setProcessor(TextBlockDetector(this))

        if (recognizer.isOperational) {
            cameraSource = CameraSource.Builder(applicationContext, recognizer)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(4.0f)
                    .build()

            cameraView.holder.addCallback(SurfaceHolderCallback(cameraSource))
        } else {
            Log.d("MainActivity", "not available")
        }
    }

    override fun onTextRecognized(text: String?) {
        if (text.isNullOrEmpty()) {
            readTextView.setText(R.string.no_text)
        } else {
            readTextView.text = text
        }
    }
}