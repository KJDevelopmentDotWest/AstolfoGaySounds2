package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

open class DefaultPosture {
    companion object{
        var status: Int = 0

        const val SKIRT_OFFSET_TOP = 100f
        const val SKIRT_OFFSET_LEFT = 100f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.default_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.default_astolfo))

        fun draw(){
            ImageFactory.mergeScaleBitmaps(arrayListOf(Item(baseBitmap, 0f, 0f)))
        }
    }
}