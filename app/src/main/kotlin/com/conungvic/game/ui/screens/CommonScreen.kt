package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.conungvic.game.Config
import com.conungvic.game.KnightGame

abstract class CommonScreen(game: KnightGame): Screen {
    protected val stage: Stage
    protected val game: KnightGame
    protected val camera: OrthographicCamera = OrthographicCamera()
    protected val viewPort: Viewport

    private val b2dr: Box2DDebugRenderer = Box2DDebugRenderer()

    init {
        this.game = game
        viewPort = FillViewport(Config.width / Config.ppm, Config.height / Config.ppm, camera)
        viewPort.apply()
        stage = Stage(viewPort, game.batch)
    }

    override fun show() {
        Gdx.app.log("CommonScreen:show", "Not yet implemented")
    }

    protected open fun update(delta: Float) {
        if (!this.game.assetManager.isFinished)
            this.game.assetManager.update()

        val fps = Gdx.graphics.framesPerSecond
        val timeStep = if (fps > 60) 1f / fps else 1 / 60f
        this.game.world.step(timeStep, 1, 1)
    }

    override fun render(delta: Float) {
        update(delta)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {
        Gdx.app.log("CommonScreen:pause", "Not yet implemented")
    }

    override fun resume() {
        Gdx.app.log("CommonScreen:resume", "Not yet implemented")
    }

    override fun hide() {
        Gdx.app.log("CommonScreen:hide", "Not yet implemented")
    }

    override fun dispose() {
        b2dr.dispose()
        stage.dispose()
    }
}