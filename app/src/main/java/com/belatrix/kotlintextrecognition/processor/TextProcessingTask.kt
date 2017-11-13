package com.belatrix.kotlintextrecognition.processor

import android.os.AsyncTask
import android.util.SparseArray
import com.google.android.gms.vision.text.TextBlock

/**
 * Created by Luis Alonso on 13/11/2017
 */

class TextProcessingTask: AsyncTask<SparseArray<TextBlock>, Void, String> {

    private var mListener: TextProcessingListener?

    constructor(listener: TextProcessingListener?) {
        mListener = listener
    }

    override fun doInBackground(vararg p0: SparseArray<TextBlock>?): String {
        val items = p0[0]
        var result = ""

        items?.let {
            for (i in 0..(items.size() - 1)) {
                val item = items.valueAt(i)
                result += item.value
                result += "\n"
            }
        }

        return result
    }

    override fun onPostExecute(result: String?) {
        mListener?.onTextRecognized(result)
    }
}