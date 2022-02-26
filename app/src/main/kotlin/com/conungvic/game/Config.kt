package com.conungvic.game

import com.badlogic.gdx.utils.ObjectMap
import com.badlogic.gdx.utils.PropertiesUtils
import java.io.InputStream
import java.io.InputStreamReader

const val WIDTH = "WIDTH"
const val HEIGHT = "HEIGHT"

object Config {

    val prop = ObjectMap<String, String>()

    init {
        val iStream: InputStream? = this.javaClass.classLoader.getResourceAsStream("knight-app.cfg")
        val reader = InputStreamReader(iStream!!)

        PropertiesUtils.load(prop, reader)
    }
}