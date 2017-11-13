package com.belatrix.kotlintextrecognition.camera

import android.util.Log
import android.view.SurfaceHolder
import com.google.android.gms.vision.CameraSource
import java.io.IOException

/**
 * Created by Luis Alonso on 13/11/2017
 */
class SurfaceHolderCallback: SurfaceHolder.Callback {

    var mSource: CameraSource? = null

    constructor(source: CameraSource?) {
        mSource = source
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        try {
            mSource?.start(p0)
        } catch (ioException: IOException) {
            Log.d("SurfaceHolderCallback", ioException.localizedMessage)
        } catch (securityException: SecurityException) {
            Log.d("SurfaceHolderCallback", securityException.localizedMessage)
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        Log.d("SurceHolderCallback", "surfaceChanged")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        mSource?.stop()
    }
}