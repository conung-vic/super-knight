package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.scenes.MainHud
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction

class GeneratedLevelScreen(game: KnightGame): CommonScreen(game) {
    private val b2dr = Box2DDebugRenderer()
//    private var mapRenderer: OrthogonalTiledMapRenderer
    private val hud: MainHud
    private val vel = 80f

    init {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        this.game.player.create()
        hud = MainHud(this.game)

    }

    override fun update(delta: Float) {
        super.update(delta)
        hud.update(delta)
        handleInput(delta)
        this.game.player.update(delta)
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = NewGameScreen(game)
            dispose()
        }
        this.game.player.state = Action.STAND
        this.game.player.velocity.set(0f, 0f)

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.game.player.direction = Direction.LEFT
            this.game.player.state = Action.WALK
            this.game.player.velocity.set(-vel, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.game.player.direction = Direction.RIGHT
            this.game.player.state = Action.WALK
            this.game.player.velocity.set(vel, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.game.player.direction = Direction.DOWN
            this.game.player.state = Action.WALK
            this.game.player.velocity.set(0f, -vel)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.game.player.direction = Direction.UP
            this.game.player.state = Action.WALK
            this.game.player.velocity.set(0f, vel)
        }
        this.game.player.isAttacking = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    }

    override fun render(delta: Float) {
        super.render(delta)

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        drawSprites()
        game.batch.end()

//        mapRenderer.render(layers2ToRender)

        b2dr.render(this.game.world, camera.combined)

        game.batch.projectionMatrix = hud.stage.camera.combined
        hud.stage.draw()
    }

    private fun drawSprites() {
        this.game.player.draw(game.batch)
    }
}