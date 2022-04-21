package com.conungvic.game.ui.scenes

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.sprites.Knight
import kotlin.math.roundToInt

class MainHud(
    val game: KnightGame,
    val player: Knight
    ): Disposable {
    val stage: Stage

    private val viewport: Viewport
    private val playerPosLabel: Label
    private val playerXYLabel: Label

    init {
        viewport = FitViewport(Config.width / Config.ppm, Config.height / Config.ppm, OrthographicCamera())
        stage = Stage(viewport, game.batch)

        val table = Table()
        table.top()
        table.setFillParent(true)

        playerPosLabel = Label("Player position: ", LabelStyle(BitmapFont(), Color.WHITE))
        playerXYLabel = Label("", LabelStyle(BitmapFont(), Color.WHITE))

        table.add(playerPosLabel)
        table.row()
        table.add(playerXYLabel)

        stage.addActor(table)
    }

    fun update(delta: Float) {
        val playerX = player.sprite.x.roundToInt()
        val playerY = player.sprite.y.roundToInt()
        playerXYLabel.setText("X: $playerX; Y: $playerY")
    }

    override fun dispose() {
        stage.dispose()
    }
}