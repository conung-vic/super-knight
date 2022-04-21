package com.conungvic.game.ui.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.scenes.MainHud

class GeneratedLevelScreen(game: KnightGame): LevelScreen(game) {

    private var mapRenderer: OrthogonalTiledMapRenderer
    private val hud: MainHud
    private val map = TiledMap()
    private val tileSize = 16
    private val mapSize = 60

    private val allTextures: Texture
    private val groundTexture: TextureRegion

    init {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        this.player.create()
        hud = MainHud(this.game, this.player)

        allTextures = Texture("map/overworld_tileset_grass.png")
        groundTexture = TextureRegion(allTextures, tileSize, tileSize, tileSize, tileSize)

        val baseLayer = TiledMapTileLayer(mapSize, mapSize, tileSize, tileSize)
        this.map.layers.add(baseLayer)

        for (x in 0 until mapSize) {
            for (y in 0 until mapSize) {
                val cell = TiledMapTileLayer.Cell()
                cell.tile = StaticTiledMapTile(groundTexture)
                baseLayer.setCell(x, y, cell)
            }
        }
        mapRenderer = OrthogonalTiledMapRenderer(map, this.game.batch)
    }

    override fun update(delta: Float) {
        super.update(delta)
        hud.update(delta)
        handleInput(delta)
    }

    override fun render(delta: Float) {
        super.render(delta)

        mapRenderer.setView(camera)
        mapRenderer.render()

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        drawSprites()
        game.batch.end()

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
        super.dispose()
    }
}