package com.kjdevelopmentdotwest.astolfogaysounds2.tools

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.*

class UserData {
    companion object{
        var sharedPreferences: SharedPreferences? = null
        var sharedPreferencesStatus: SharedPreferences? = null

        var clickCount: Long = 0
        var moneyCount: Long = 0

        var defaultPostureStatus = 0
        var casualPostureStatus = 0
        var formalPostureStatus = 0
        var schoolPostureStatus = 0

        var isMuted: Boolean = false

        val casualPostureSkirts = arrayListOf<CasualPostureSkirt>()

        val formalPostureBlazers = arrayListOf<FormalPostureBlazer>()
        val formalPosturePants = arrayListOf<FormalPosturePants>()

        val schoolPostureShirts = arrayListOf<SchoolPostureShirt>()
        val schoolPostureSkirts = arrayListOf<SchoolPostureSkirt>()
        val schoolPostureStockings = arrayListOf<SchoolPostureStockings>()

        val backgrounds = arrayListOf<Background>()

        val clicksList = arrayListOf<Long>()//contain time of clicks
        var clickAchievement = false

        fun retrieveUserData(){
            //retrieveStatusInfo(sharedPreferencesStatus!!)
            retrieveStatusInfo(sharedPreferences!!)
            retrieveClickMoneyData(sharedPreferences!!)
            retrieveCasualPostureData(sharedPreferences!!)
            retrieveFormalPostureData(sharedPreferences!!)
            retrieveSchoolPostureData(sharedPreferences!!)
            retrieveDefaultPostureData(sharedPreferences!!)
            retrieveBackgroundData(sharedPreferences!!)
            retrieveOtherInfo(sharedPreferences!!)
        }

        fun saveUserData(){
            val editor = sharedPreferences!!.edit()
            //val editorStatus = sharedPreferencesStatus!!.edit()
            //saveStatusInfo(editorStatus)
            saveStatusInfo(editor)
            saveClickMoneyData(editor)
            saveCasualPostureData(editor)
            saveFormalPostureData(editor)
            saveSchoolPostureData(editor)
            saveDefaultPostureData(editor)
            saveBackgroundData(editor)
            saveOtherInfo(editor)
            editor.apply()
            //editorStatus.apply()
        }

        private fun retrieveClickMoneyData(sharedPreferences: SharedPreferences){
            clickCount = sharedPreferences.getLong("clicks", 0)
            moneyCount = sharedPreferences.getLong("money", 0)
        }

        private fun retrieveBackgroundData(sharedPreferences: SharedPreferences){
            backgrounds.add(Background(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.background_black), sharedPreferences.getInt("blackBackground", 2)))
            backgrounds.add(Background(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.background_green), sharedPreferences.getInt("greenBackground", 0)))
            backgrounds.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }
        }

        private fun retrieveCasualPostureData(sharedPreferences: SharedPreferences){
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_black), sharedPreferences.getInt("blackSkirt", 0)))
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_green), sharedPreferences.getInt("greenSkirt", 0)))
            casualPostureSkirts.forEach {
                if (it.status == 2){
                    it.drawCasual()
                    return@forEach
                }
            }
        }

        private fun retrieveFormalPostureData(sharedPreferences: SharedPreferences){

            formalPostureBlazers.add(FormalPostureBlazer(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_blazer_red), sharedPreferences.getInt("formalBlazerRed", 0)))
            formalPostureBlazers.forEach {
                if (it.status == 2){
                    it.addToDrawQueue()
                    return@forEach
                }
            }

            formalPosturePants.add(FormalPosturePants(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_pants_black), sharedPreferences.getInt("formalPantsBlack", 0)))
            formalPosturePants.add(FormalPosturePants(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_pants_green), sharedPreferences.getInt("formalPantsGreen", 0)))
            formalPosturePants.forEach {
                if (it.status == 2){
                    it.addToDrawQueue()
                    return@forEach
                }
            }
        }

        private fun retrieveSchoolPostureData(sharedPreferences: SharedPreferences){

            schoolPostureShirts.add(SchoolPostureShirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_shirt_white), sharedPreferences.getInt("schoolShirtWhite", 2)))
            schoolPostureShirts.add(SchoolPostureShirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_shirt_blue), sharedPreferences.getInt("schoolShirtBlue", 0)))
            schoolPostureShirts.add(SchoolPostureShirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_shirt_purple), sharedPreferences.getInt("schoolShirtPurple", 0)))
            schoolPostureShirts.forEach {
                if (it.status == 2){
                    it.addToDrawQueue()
                    return@forEach
                }
            }

            schoolPostureSkirts.add(SchoolPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_skirt_pink), sharedPreferences.getInt("schoolSkirtPink", 2)))
            schoolPostureSkirts.add(SchoolPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_skirt_blue), sharedPreferences.getInt("schoolSkirtBlue", 0)))
            schoolPostureSkirts.add(SchoolPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_skirt_purple), sharedPreferences.getInt("schoolSkirtPurple", 0)))
            schoolPostureSkirts.forEach {
                if (it.status == 2){
                    it.addToDrawQueue()
                    return@forEach
                }
            }

            schoolPostureStockings.add(SchoolPostureStockings(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_stockings_white), sharedPreferences.getInt("schoolStockingsWhite", 2)))
            schoolPostureStockings.add(SchoolPostureStockings(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_stockings_blue), sharedPreferences.getInt("schoolStockingsBlue", 0)))
            schoolPostureStockings.add(SchoolPostureStockings(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_stockings_purple), sharedPreferences.getInt("schoolStockingsPurple", 0)))
            schoolPostureStockings.forEach {
                if (it.status == 2){
                    it.addToDrawQueue()
                    return@forEach
                }
            }
        }

        private fun retrieveDefaultPostureData(sharedPreferences: SharedPreferences){
            if (DefaultPosture.status == 2){
                DefaultPosture.draw()
            }
        }

        private fun retrieveStatusInfo(sharedPreferences: SharedPreferences){
            schoolPostureStatus = sharedPreferences.getInt("schoolPosture", 2)
            formalPostureStatus = sharedPreferences.getInt("formalPosture", 0)
            casualPostureStatus = sharedPreferences.getInt("casualPosture", 0)
            defaultPostureStatus = sharedPreferences.getInt("defaultPosture", 0)
        }

        private fun retrieveOtherInfo(sharedPreferences: SharedPreferences){
            isMuted = sharedPreferences.getBoolean("isMuted", false)
            clickAchievement = sharedPreferences.getBoolean("clickAchievement", false)
        }

        private fun saveClickMoneyData(editor: SharedPreferences.Editor){
            editor.putLong("clicks", clickCount)
            editor.putLong("money", moneyCount)
        }

        private fun saveBackgroundData(editor: SharedPreferences.Editor){
            editor.putInt("blackBackground", backgrounds[0].status)
            editor.putInt("greenBackground", backgrounds[1].status)
        }

        private fun saveCasualPostureData(editor: SharedPreferences.Editor){
            editor.putInt("blackSkirt", casualPostureSkirts[0].status)
            editor.putInt("greenSkirt", casualPostureSkirts[1].status)
        }

        private fun saveFormalPostureData(editor: SharedPreferences.Editor){
            editor.putInt("formalBlazerRed", formalPostureBlazers[0].status)

            editor.putInt("formalPantsBlack", formalPosturePants[0].status)
            editor.putInt("formalPantsGreen", formalPosturePants[1].status)
        }

        private fun saveSchoolPostureData(editor: SharedPreferences.Editor){
            editor.putInt("schoolShirtWhite", schoolPostureShirts[0].status)
            editor.putInt("schoolShirtBlue", schoolPostureShirts[1].status)
            editor.putInt("schoolShirtPurple", schoolPostureShirts[2].status)

            editor.putInt("schoolSkirtPink", schoolPostureSkirts[0].status)
            editor.putInt("schoolSkirtBlue", schoolPostureSkirts[1].status)
            editor.putInt("schoolSkirtPurple", schoolPostureSkirts[2].status)

            editor.putInt("schoolStockingsWhite", schoolPostureStockings[0].status)
            editor.putInt("schoolStockingsBlue", schoolPostureStockings[1].status)
            editor.putInt("schoolStockingsPurple", schoolPostureStockings[2].status)
        }

        private fun saveDefaultPostureData(editor: SharedPreferences.Editor){
            //editor.putInt("defaultPosture", DefaultPosture.status)
        }

        private fun saveStatusInfo(editor: SharedPreferences.Editor){
            editor.putInt("defaultPosture", defaultPostureStatus)
            editor.putInt("schoolPosture", schoolPostureStatus)
            editor.putInt("casualPosture", casualPostureStatus)
            editor.putInt("formalPosture", formalPostureStatus)
        }

        private fun saveOtherInfo(editor: SharedPreferences.Editor){
            editor.putBoolean("isMuted", isMuted)
            editor.putBoolean("clickAchievement", clickAchievement)
        }
    }
}
