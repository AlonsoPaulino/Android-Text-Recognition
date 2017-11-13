package com.belatrix.kotlintextrecognition.processor

/**
 * Created by Luis Alonso on 13/11/2017
 */

interface TextProcessingListener {

    fun onTextRecognized(text: String?)
}