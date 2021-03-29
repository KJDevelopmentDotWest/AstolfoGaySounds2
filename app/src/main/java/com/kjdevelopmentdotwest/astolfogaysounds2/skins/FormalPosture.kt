package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.Item
import com.kjdevelopmentdotwest.astolfogaysounds2.R

//status 0 - not sold, 1 - sold, 2 - equipped

open class FormalPosture {
    companion object{
        var status: Int = 0

        const val BLAZER_OFFSET_LEFT = 572f*3f
        const val BLAZER_OFFSET_TOP = 1091f*3f
        const val PANTS_OFFSET_LEFT = 477f*3f
        const val PANTS_OFFSET_TOP = 1745f*3f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo))
        var blazerBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_blazer_red)
        var pantsBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_pants_black)
        var resultMap: ArrayList<Item> = arrayListOf(Item(baseBitmap, 0f, 0f),
            Item(blazerBitmap, BLAZER_OFFSET_LEFT, BLAZER_OFFSET_TOP),
            Item(pantsBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP))
    }

    open fun draw() {
        ImageFactory.mergeScaleBitmaps(resultMap)
    }
}