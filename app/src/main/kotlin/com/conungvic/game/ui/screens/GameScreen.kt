package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.scenes.MainHud
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction
import com.conungvic.game.ui.sprites.Knight
import com.conungvic.game.utils.MapObjectCreator

class GameScreen(game: KnightGame): CommonScreen(game) {
    val world: World = World(Vector2(0f, 0f), true)
    private val player = Knight(game, world)

    private val vel = 80f

    val map: TiledMap
    private var mapRenderer: OrthogonalTiledMapRenderer

    private val hud: MainHud
    private val layers1ToRender: IntArray = arrayOf(0, 1, 2, 3).toIntArray()
    private val layers2ToRender: IntArray = arrayOf(4).toIntArray()

    init {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        this.player.create()
        val mapLoader = TmxMapLoader()
        map = mapLoader.load("map/map1.tmx")
        mapRenderer = OrthogonalTiledMapRenderer(map, 1f / Config.ppm)
        hud = MainHud(this.game, this.player)
        MapObjectCreator.createBodiesForMap(this)
    }

    override fun update(delta: Float) {
        super.update(delta)

        val fps = Gdx.graphics.framesPerSecond
        val timeStep = if (fps > 60) 1f / fps else 1 / 60f
        this.world.step(timeStep, 1, 1)

        handleInput(delta)
        hud.update(delta)
        updateCameraPos()
        this.player.update(delta)
        mapRenderer.setView(camera)
    }

    private fun updateCameraPos() {
        val playerX = player.sprite.x
        val playerY = player.sprite.y
        camera.position.set(playerX, playerY, 0f)
        camera.update()
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.screen = NewGameScreen(game)
            this.dispose()
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
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_ADD)) {
            this.camera.zoom -= 0.01f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_SUBTRACT)) {
            this.camera.zoom += 0.01f
        }

        this.player.isAttacking = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    }

    override fun render(delta: Float) {
        super.render(delta)

        mapRenderer.render(layers1ToRender)

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        drawSprites()
        game.batch.end()

        mapRenderer.render(layers2ToRender)

//        b2dr.render(this.world, camera.combined)

        game.batch.projectionMatrix = hud.stage.camera.combined
        hud.stage.draw()
    }

    private fun drawSprites() {
        this.player.draw(game.batch)
    }

    override fun dispose() {
        hud.dispose()
        world.dispose()
//        mapRenderer.dispose()
        super.dispose()
    }

}