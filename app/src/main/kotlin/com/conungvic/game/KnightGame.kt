package com.conungvic.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.conungvic.game.ui.screens.TitleScreen
import com.conungvic.game.ui.sprites.Knight
import com.conungvic.game.utils.CustomAssetManager

class KnightGame: Game() {
    var world: World = World(Vector2(0f, 0f), true)
    val assetManager: CustomAssetManager = CustomAssetManager()
    lateinit var batch: SpriteBatch
    lateinit var player: Knight

    override fun create() {
        batch = SpriteBatch()
        assetManager.loadResources()
        setScreen(TitleScreen(this))
        player = Knight(this)
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}