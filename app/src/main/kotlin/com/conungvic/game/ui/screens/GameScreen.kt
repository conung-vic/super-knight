package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.controllers.GamePlayerInputHandler
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction

class GameScreen(game: KnightGame): CommonScreen(game) {
    private val gamecam: OrthographicCamera = OrthographicCamera()
    private val viewPort: Viewport

    init {
        viewPort = FillViewport(Config.width / Config.ppm, Config.height / Config.ppm, gamecam)
        viewPort.apply()

        gamecam.position.set(gamecam.viewportWidth / 2, gamecam.viewportHeight / 2, 0f)
        this.game.player.create()
        this.game.player.sprite.setPosition(200f, 200f)

        Gdx.input.inputProcessor = GamePlayerInputHandler(this.game.player)
    }

    override fun update(delta: Float) {
        super.update(delta)
        handleInput(delta)
        gamecam.update()
        this.game.player.update(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = NewGameScreen(game)
            dispose()
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.game.player.direction = Direction.LEFT
            this.game.player.state = Action.WALK
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.game.player.direction = Direction.RIGHT
            this.game.player.state = Action.WALK
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.game.player.direction = Direction.DOWN
            this.game.player.state = Action.WALK
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.game.player.direction = Direction.UP
            this.game.player.state = Action.WALK
        }
    }

    override fun render(delta: Float) {
        super.render(delta)
        game.batch.projectionMatrix = gamecam.combined
        game.batch.begin()
        drawSprites()
        game.batch.end()
    }

    private fun drawSprites() {
        this.game.player.sprite.draw(game.batch)
    }

}