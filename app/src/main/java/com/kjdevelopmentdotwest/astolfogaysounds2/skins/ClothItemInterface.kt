package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap

interface ClothItemInterface {
    var resBitmap: Bitmap
    var status: Int
    fun addToDrawQueue()
}