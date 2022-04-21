package com.conungvic.game.ui.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.conungvic.game.KnightGame
import com.conungvic.game.ui.scenes.MainHud
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction
import com.conungvic.game.ui.sprites.Knight

class GeneratedLevelScreen(game: KnightGame): CommonScreen(game) {
    private val world: World = World(Vector2(0f, 0f), true)
    private val player = Knight(game, world)
    
    private var mapRenderer: OrthogonalTiledMapRenderer
    private val hud: MainHud
    private val vel = 80f
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

        val fps = Gdx.graphics.framesPerSecond
        val timeStep = if (fps > 60) 1f / fps else 1 / 60f
        this.world.step(timeStep, 1, 1)


        hud.update(delta)
        handleInput(delta)
        this.player.update(delta)
    }

    private fun handleInput(delta: Float) {
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