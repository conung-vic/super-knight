package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.controllers.GamePlayerInputHandler
import com.conungvic.game.ui.sprites.Knight

class GameScreen(game: KnightGame): CommonScreen(game) {
    private val gamecam: OrthographicCamera = OrthographicCamera()
    private val viewPort: Viewport
    private val player = Knight(game)

    init {
        viewPort = FillViewport(Config.width / Config.ppm, Config.height / Config.ppm, gamecam)
        viewPort.apply()

        gamecam.position.set(gamecam.viewportWidth / 2, gamecam.viewportHeight / 2, 0f)
        player.create()
        player.sprite.setPosition(200f, 200f)

        Gdx.input.inputProcessor = GamePlayerInputHandler(player)
    }

    override fun update(delta: Float) {
        super.update(delta)
        handleInput(delta)
        gamecam.update()
        player.update(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = NewGameScreen(game)
            dispose()
        }
        /*if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.direction = Direction.RIGHT
            player.state = Action.WALK
            Gdx.app.log("walk", "right")
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.direction = Direction.LEFT
            player.state = Action.WALK
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.direction = Direction.UP
            player.state = Action.WALK
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.direction = Direction.DOWN
            player.state = Action.WALK
        }*/
//        player.state = Action.STAND
    }

    override fun render(delta: Float) {
        super.render(delta)
        game.batch.projectionMatrix = gamecam.combined
        game.batch.begin()
        drawSprites()
        game.batch.end()
    }

    private fun drawSprites() {
        player.sprite.draw(game.batch)
    }

}