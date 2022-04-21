package com.conungvic.game.ui.screens

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.scenes.MainHud
import com.conungvic.game.utils.MapObjectCreator

class GameScreen(game: KnightGame): LevelScreen(game) {

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

        handleInput(delta)
        hud.update(delta)
        updateCameraPos()
        mapRenderer.setView(camera)
    }

    private fun updateCameraPos() {
        val playerX = player.sprite.x
        val playerY = player.sprite.y
        camera.position.set(playerX, playerY, 0f)
        camera.update()
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