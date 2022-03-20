package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.utils.FontManager

class TitleScreen(game: KnightGame): CommonScreen(game) {
    private val font45 = Label.LabelStyle(FontManager.astigma45, Color.YELLOW)
    private val font32light = Label.LabelStyle(FontManager.astigma32light, Color.WHITE)
    private val font32selected = Label.LabelStyle(FontManager.astigma32selected, Color.WHITE)
    private val mainTitleLabel = Label("Super Game About \nKnight's Poor Life", font45)

    private val labels = listOf(
        Label("New Game", font32light),
        Label("Load Game", font32light),
        Label("Options", font32light),
        Label("Credits", font32light),
        Label("Quit", font32light)
    )
    private var selectedItemIdx: Int = 0

    init {
        mainTitleLabel.setPosition(Config.width / 2 - 100, Config.height / 2 + 150)

        stage.addActor(mainTitleLabel)
        for (i in labels.indices) {
            labels[i].setPosition(75f, 300f - i*labels[i].height-5)
            stage.addActor(labels[i])
        }
    }

    override fun update(delta: Float) {
        super.update(delta)
        for (i in labels.indices) {
            if (i == selectedItemIdx) {
                labels[i].style = font32selected
            } else {
                labels[i].style = font32light
            }
        }
        handleInput(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
            selectedItemIdx < labels.size-1) {
            selectedItemIdx++
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
            selectedItemIdx > 0) {
            selectedItemIdx--
        }
    }

    override fun render(delta: Float) {
        super.render(delta)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }
}