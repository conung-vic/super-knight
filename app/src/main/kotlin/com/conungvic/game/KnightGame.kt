package com.conungvic.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.conungvic.game.ui.screens.TitleScreen
import com.conungvic.game.utils.CustomAssetManager

class KnightGame: Game() {
    val assetManager: CustomAssetManager = CustomAssetManager()
    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        assetManager.loadResources()
        setScreen(TitleScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}