package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.*


class UserData {
    companion object{
        var clickCount : Long = 0
        var moneyCount : Long = 0
        var context: Context? = null

        //TAG ORDER: CLICK COUNT, MONEY COUNT, .....
        fun retrieveUserInfoFromFile(){
            var userInfoString = ""
            try {
                val fileInputStream: FileInputStream = context!!.openFileInput("userdata.txt")
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                userInfoString = bufferedReader.readLine()
            } catch (ignored: FileNotFoundException) {
            } catch (ignored: IOException) {
            }

            var tagEndsCounter = 0
            userInfoString.forEachIndexed() {i, it ->
                if (it == '>'){
                    val stringBuilder = StringBuilder()
                    var counter = i + 1
                    loop@while (userInfoString[counter].isDigit()){
                        stringBuilder.append(userInfoString[counter])
                        counter++
                        if (counter == userInfoString.length){
                            break@loop
                        }
                    }
                    when (tagEndsCounter){
                        0 -> clickCount = try {
                            stringBuilder.toString().toLong()
                        } catch (ignored: NumberFormatException){
                            0
                        }
                        1 -> moneyCount = try {
                            stringBuilder.toString().toLong()
                        } catch (ignored: NumberFormatException){
                            0
                        }
                    }
                    tagEndsCounter++
                }
            }
        }

        fun saveProgress(){
            val temp = StringBuilder()
            temp.append('>').append(clickCount).append('>').append(moneyCount)
            val fileOutputStream = context!!.openFileOutput("userdata.txt", MODE_PRIVATE)
            fileOutputStream.write(temp.toString().toByteArray())
            fileOutputStream.close()
        }
    }
}