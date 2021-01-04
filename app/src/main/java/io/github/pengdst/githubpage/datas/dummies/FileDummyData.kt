package io.github.pengdst.githubpage.datas.dummies

import android.content.Context
import java.io.BufferedReader
import java.io.IOException

class FileDummyData(context: Context){

    private val datas = readJsonFile(context)

    private fun readJsonFile(context: Context): String? {
        val file: BufferedReader?

        try {
            file = context.assets.open("githubuser.json").bufferedReader()

            return file.use {
                it.readText()
            }
        }catch (e: IOException){
            e.printStackTrace()
            return null
        }
    }

//    override fun getUserList(): List<UserModel> {
//        val data = Gson().fromJson(datas, UserResponse::class.java)
//
//        return data.users
//    }
}