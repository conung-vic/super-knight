package com.conungvic.game.ui.sprites

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

class HeroAnimation(
    val stand: TextureRegion,
    val walk: Animation<TextureRegion>,
    val attack: Animation<TextureRegion>
)
