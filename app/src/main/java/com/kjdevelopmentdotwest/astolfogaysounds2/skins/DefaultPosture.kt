package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

open class DefaultPosture {
    companion object{
        var status: Int = 0

        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.default_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.default_astolfo))

        fun drawDefault(){
            if (CasualPosture.status == 2){
                CasualPosture.status = 1
            }
            if (FormalPosture.status == 2){
                FormalPosture.status = 1
            }
            if (SchoolPosture.status == 2){
                SchoolPosture.status = 1
            }
            status = 2
            ImageFactory.mergeScaleBitmaps(arrayListOf(Item(baseBitmap, 0f, 0f)))
        }
    }
}