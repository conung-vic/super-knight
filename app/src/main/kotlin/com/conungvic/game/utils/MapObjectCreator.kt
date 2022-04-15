package com.conungvic.game.utils

import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.*
import com.conungvic.game.Config
import com.conungvic.game.ui.screens.GameScreen

object MapObjectCreator {
    fun createBodiesForMap(screen: GameScreen) {
        val world = screen.game.world
        val map = screen.map
        createBodiesForLayer(world, map, "collisions", OBJECT_BIT)
    }

    private fun createBodiesForLayer(world: World, map: TiledMap, layerName: String, objectBit: Short) {
        map.layers
            .first { f -> f.name == layerName }
            .objects
            .getByType(RectangleMapObject::class.java)
            .forEach { o ->
                val rect = o.rectangle
                createBody(world, rect, objectBit)
            }
    }

    private fun createBody(world: World, rect: Rectangle, objectBit: Short) {
        val bodyDef = BodyDef()
        val fixtureDef = FixtureDef()
        val shape = PolygonShape()

        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set((rect.x + rect.width / 2) / Config.ppm, (rect.y + rect.height / 2) / Config.ppm)

        val body = world.createBody(bodyDef)
        shape.setAsBox(rect.width / 2 / Config.ppm, rect.height / 2 / Config.ppm)
        fixtureDef.shape = shape
        fixtureDef.filter.categoryBits = objectBit
        body.createFixture(fixtureDef)
    }
}