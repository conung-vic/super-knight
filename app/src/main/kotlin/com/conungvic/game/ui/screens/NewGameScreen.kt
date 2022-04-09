package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.utils.FontManager

class NewGameScreen(game: KnightGame): CommonScreen(game) {
    private val font45 = Label.LabelStyle(FontManager.astigma45, Color.YELLOW)
    private val font32selected = Label.LabelStyle(FontManager.astigma32selected, Color.WHITE)
    private val font32light = Label.LabelStyle(FontManager.astigma32light, Color.WHITE)
    private val mainTitleLabel = Label("New Game", font45)

    private val menuItems = listOf(
        MainMenuItem(Label("Start Game", font32light), GameScreen(game)),
        MainMenuItem(Label("Pause Game", font32light), null)
    )
    var selectedItemIdx: Int = 0

    init {
        mainTitleLabel.setPosition(Config.width / 2 - 100, Config.height / 2 + 150)
        stage.addActor(mainTitleLabel)

        for (i in menuItems.indices) {
            menuItems[i].label.setPosition(75f, 300f - i*menuItems[i].label.height-5)
            stage.addActor(menuItems[i].label)
        }
    }

    override fun update(delta: Float) {
        super.update(delta)
        for (i in menuItems.indices) {
            if (i == selectedItemIdx) {
                menuItems[i].label.style = font32selected
            } else {
                menuItems[i].label.style = font32light
            }
        }
        handleInput(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = TitleScreen(game)
            (this.game.screen as TitleScreen).selectedItemIdx = 0
            dispose()
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
            selectedItemIdx < menuItems.size-1) {
            selectedItemIdx++
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
            selectedItemIdx > 0) {
            selectedItemIdx--
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (menuItems[selectedItemIdx].screen != null) {
                this.game.screen = menuItems[selectedItemIdx].screen
                dispose()
            } else {
                dispose()
                Gdx.app.exit()
            }
        }
    }
}