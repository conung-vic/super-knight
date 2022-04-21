package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction
import com.conungvic.game.ui.sprites.Knight

abstract class LevelScreen(game: KnightGame): CommonScreen(game) {
    val world: World = World(Vector2(0f, 0f), true)
    protected val player: Knight = Knight(game, world)
    private val vel = 80f

    protected fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = NewGameScreen(game)
            dispose()
        }
        this.player.state = Action.STAND
        this.player.velocity.set(0f, 0f)

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.player.direction = Direction.LEFT
            this.player.state = Action.WALK
            this.player.velocity.set(-vel, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.player.direction = Direction.RIGHT
            this.player.state = Action.WALK
            this.player.velocity.set(vel, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.player.direction = Direction.DOWN
            this.player.state = Action.WALK
            this.player.velocity.set(0f, -vel)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.player.direction = Direction.UP
            this.player.state = Action.WALK
            this.player.velocity.set(0f, vel)
        }
        this.player.isAttacking = Gdx.input.isKeyPressed(Input.Keys.SPACE)

        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_ADD)) {
            this.camera.zoom -= 0.01f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_SUBTRACT)) {
            this.camera.zoom += 0.01f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
            this.camera.position.x -= 1f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
            this.camera.position.x += 1f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
            this.camera.position.y -= 1f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)) {
            this.camera.position.y += 1f
        }
    }

    override fun update(delta: Float) {
        super.update(delta)
        val fps = Gdx.graphics.framesPerSecond
        val timeStep = if (fps > 60) 1f / fps else 1 / 60f
        this.world.step(timeStep, 1, 1)
        this.player.update(delta)
    }
}