package com.conungvic.game

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


fun main() {
    val config = LwjglApplicationConfiguration()
    config.width = Config.width.toInt()
    config.height = Config.height.toInt()
    config.title = "Knight"
    config.resizable = true
    checkIsUsernameNotLatin()
//    config.fullscreen = true
    LwjglApplication(KnightGame(), config)
}

fun checkIsUsernameNotLatin() {
    val name = System.getProperty("user.name")
    if (!isPureAscii(name)) {
        val buf = StandardCharsets.UTF_8.encode(name)
        val sBuf = StringBuffer()
        for (b in buf.array()) {
            if (1*b != 0) {
                val hex = Integer.toHexString(1 * b).substring(6, 8).uppercase()
                sBuf.append("\\x$hex")
            }
        }
        System.setProperty("user.name", sBuf.toString())
    }
}

fun isPureAscii(v: String): Boolean {
    return Charset.forName(StandardCharsets.ISO_8859_1.name()).newEncoder().canEncode(v)
    // or "ISO-8859-1" for ISO Latin 1
    // or StandardCharsets.US_ASCII with JDK1.7+
}