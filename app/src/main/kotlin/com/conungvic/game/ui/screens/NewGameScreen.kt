package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.Config
import com.conungvic.game.KnightGame

class NewGameScreen(game: KnightGame): ScreenWithItems(game) {

    init {
        menuItems.add(MainMenuItem(Label("Start Map Game", font32light), ScreenFactory.ScreenType.MAPPED_LEVEL))
        menuItems.add(MainMenuItem(Label("Generated Level Game", font32light), ScreenFactory.ScreenType.GENERATED_LEVEL))

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

    override fun handleInput(delta: Float) {
        super.handleInput(delta)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = TitleScreen(game)
            (this.game.screen as TitleScreen).selectedItemIdx = 0
            dispose()
        }
    }
}