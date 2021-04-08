package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

//status 0 - not sold, 1 - sold, 2 - equipped

class Background(var resBitmap: Bitmap, var status: Int) {
    companion object{
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.background_black))
    }

    fun draw(){
        ImageFactory.resultBackground = resBitmap
    }
}