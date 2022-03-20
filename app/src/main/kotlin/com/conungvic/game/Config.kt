package com.conungvic.game

import com.badlogic.gdx.utils.ObjectMap
import com.badlogic.gdx.utils.PropertiesUtils
import java.io.InputStream
import java.io.InputStreamReader

const val WIDTH = "WIDTH"
const val HEIGHT = "HEIGHT"

object Config {
    private val prop = ObjectMap<String, String>()

    val width: Float
    val height: Float

    init {
        val iStream: InputStream? = this.javaClass.classLoader.getResourceAsStream("knight-app.cfg")
        val reader = InputStreamReader(iStream!!)

        PropertiesUtils.load(prop, reader)

        this.width = prop.get(WIDTH).toInt() * 1.0f
        this.height = prop.get(HEIGHT).toInt() * 1.0f
    }
}