package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.utils.FontManager

class LoadScreen(game: KnightGame): CommonScreen(game) {
    private val font45 = Label.LabelStyle(FontManager.astigma45, Color.YELLOW)
    private val font32light = Label.LabelStyle(FontManager.astigma32light, Color.WHITE)

    private val mainTitleLabel = Label("Load Game", font45)

    init {
        mainTitleLabel.setPosition(Config.width / 2 - 100, Config.height / 2 + 150)

        stage.addActor(mainTitleLabel)
    }

    override fun update(delta: Float) {
        super.update(delta)
        handleInput(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = TitleScreen(game)
            (this.game.screen as TitleScreen).selectedItemIdx = 1
            dispose()
        }
    }
}