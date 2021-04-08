package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item
import com.kjdevelopmentdotwest.astolfogaysounds2.R

//status 0 - not sold, 1 - sold, 2 - equipped

open class FormalPosture {
    companion object{
        var status: Int = 0

        const val BLAZER_OFFSET_LEFT = 0.529f
        const val BLAZER_OFFSET_TOP = 0.568f
        const val PANTS_OFFSET_LEFT = 0.442f
        const val PANTS_OFFSET_TOP = 0.908f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo))
        var blazerBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_blazer_red)
        var pantsBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_pants_black)
        var bitmapsToDraw: ArrayList<Item> = arrayListOf(Item(baseBitmap, 0f, 0f),
            Item(blazerBitmap, BLAZER_OFFSET_LEFT, BLAZER_OFFSET_TOP),
            Item(pantsBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP))

        fun draw() {
            ImageFactory.mergeScaleBitmaps(bitmapsToDraw)
        }
    }
}