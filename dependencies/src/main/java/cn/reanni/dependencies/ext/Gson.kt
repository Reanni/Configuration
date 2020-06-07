package cn.reanni.dependencies.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Gson {

    private val gson = Gson()

    fun <T> T.toJson(): String = gson.toJson(this)

    fun <T> String.fromJson(): T = gson.fromJson(this, object : TypeToken<T>() {}.type)

}