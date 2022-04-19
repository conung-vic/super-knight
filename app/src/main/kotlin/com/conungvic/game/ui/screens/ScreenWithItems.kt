package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.conungvic.game.KnightGame
import com.conungvic.game.utils.FontManager

abstract class ScreenWithItems(game: KnightGame): CommonScreen(game) {
    private val font45 = Label.LabelStyle(FontManager.astigma45, Color.YELLOW)
    protected val font32selected = Label.LabelStyle(FontManager.astigma32selected, Color.WHITE)
    protected val font32light = Label.LabelStyle(FontManager.astigma32light, Color.WHITE)
    protected val mainTitleLabel = Label("New Game", font45)
    protected val menuItems: MutableList<MainMenuItem> = mutableListOf()

    var selectedItemIdx: Int = 0

    protected open fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
            selectedItemIdx < menuItems.size-1) {
            selectedItemIdx++
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
            selectedItemIdx > 0) {
            selectedItemIdx--
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            val screen = ScreenFactory.getScreen(menuItems[selectedItemIdx].screenType, game)
            if (screen != null) {
                this.game.screen = screen
                dispose()
            } else {
                dispose()
                Gdx.app.exit()
            }
        }
    }
}