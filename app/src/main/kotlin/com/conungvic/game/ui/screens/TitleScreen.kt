package com.conungvic.game.ui.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.Config
import com.conungvic.game.KnightGame

class TitleScreen(game: KnightGame): ScreenWithItems(game) {
    init {
        menuItems.add(MainMenuItem(Label("New Game", font32light), ScreenFactory.ScreenType.NEW_GAME))
        menuItems.add(MainMenuItem(Label("Load Game", font32light), ScreenFactory.ScreenType.LOAD))
        menuItems.add(MainMenuItem(Label("Options", font32light), ScreenFactory.ScreenType.OPTION))
        menuItems.add(MainMenuItem(Label("Credits", font32light), ScreenFactory.ScreenType.CREDIT))
        menuItems.add(MainMenuItem(Label("Quit", font32light), ScreenFactory.ScreenType.NONE))

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
}