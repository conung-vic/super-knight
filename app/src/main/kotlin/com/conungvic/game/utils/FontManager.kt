package com.conungvic.game.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion

object FontManager {
    var astigma32light: BitmapFont
    var astigma32selected: BitmapFont
    var astigma45: BitmapFont

    init {
        var texture = Texture(Gdx.files.internal("fonts/astigma-32-light.png"))
        astigma32light = BitmapFont(Gdx.files.internal("fonts/astigma-32-light.fnt"), TextureRegion(texture), false)

        texture = Texture(Gdx.files.internal("fonts/astigma-32-selected.png"))
        astigma32selected = BitmapFont(Gdx.files.internal("fonts/astigma-32-selected.fnt"), TextureRegion(texture), false)

        texture = Texture(Gdx.files.internal("fonts/astigma-45.png"))
        astigma45 = BitmapFont(Gdx.files.internal("fonts/astigma-45.fnt"), TextureRegion(texture), false)
    }
}