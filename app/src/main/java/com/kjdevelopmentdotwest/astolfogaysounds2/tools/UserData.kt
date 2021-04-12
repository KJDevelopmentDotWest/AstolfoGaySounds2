package com.kjdevelopmentdotwest.astolfogaysounds2.tools

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*

class UserData {
    companion object{
        var sharedPreferences: SharedPreferences? = null

        var clickCount : Long = 0
        var moneyCount : Long = 0

        val casualPostureSkirts = arrayListOf<CasualPostureSkirt>()

        val formalPostureBlazers = arrayListOf<FormalPostureBlazer>()
        val formalPosturePants = arrayListOf<FormalPosturePants>()

        val backgrounds = arrayListOf<Background>()

        fun retrieveUserData(){
            retrieveClickMoneyData(sharedPreferences!!)
            retrieveCasualPostureData(sharedPreferences!!)
            retrieveFormalPostureData(sharedPreferences!!)
            retrieveSchoolPostureData(sharedPreferences!!)
            retrieveDefaultPostureData(sharedPreferences!!)
            retrieveBackgroundData(sharedPreferences!!)
        }

        fun saveUserData(){
            val editor = sharedPreferences!!.edit()
            saveClickMoneyData(editor)
            saveCasualPostureData(editor)
            saveFormalPostureData(editor)
            saveSchoolPostureData(editor)
            saveDefaultPostureData(editor)
            saveBackgroundData(editor)
            editor.apply()
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
            CasualPosture.status = sharedPreferences.getInt("casualPosture", 0)
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_black), sharedPreferences.getInt("blackSkirt", 2)))
            casualPostureSkirts.add(CasualPostureSkirt(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_green), sharedPreferences.getInt("greenSkirt", 0)))
            casualPostureSkirts.forEach {
                if (it.status == 2){
                    it.draw()
                    return@forEach
                }
            }
        }

        private fun retrieveFormalPostureData(sharedPreferences: SharedPreferences){
            FormalPosture.status = sharedPreferences.getInt("formalPosture", 0)
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
            FormalPosture.draw()
        }

        private fun retrieveSchoolPostureData(sharedPreferences: SharedPreferences){
            SchoolPosture.status = sharedPreferences.getInt("schoolPosture", 0)
        }

        private fun retrieveDefaultPostureData(sharedPreferences: SharedPreferences){
            DefaultPosture.status = sharedPreferences.getInt("defaultPosture", 0)
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

            editor.putInt("casualPosture", CasualPosture.status)
        }

        private fun saveFormalPostureData(editor: SharedPreferences.Editor){
            editor.putInt("formalBlazerRed", formalPostureBlazers[0].status)

            editor.putInt("formalPantsBlack", formalPosturePants[0].status)
            editor.putInt("formalPantsGreen", formalPosturePants[1].status)

            editor.putInt("formalPosture", FormalPosture.status)
        }

        private fun saveSchoolPostureData(editor: SharedPreferences.Editor){
            editor.putInt("schoolPosture", SchoolPosture.status)
        }

        private fun saveDefaultPostureData(editor: SharedPreferences.Editor){
            editor.putInt("defaultPosture", DefaultPosture.status)
        }
    }
}
