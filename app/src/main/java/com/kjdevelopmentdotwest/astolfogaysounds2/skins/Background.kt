package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

class Background {
    companion object{
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.background_black))
    }
}