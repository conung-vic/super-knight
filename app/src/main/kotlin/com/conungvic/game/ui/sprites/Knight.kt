package com.conungvic.game.ui.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.conungvic.game.KnightGame

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class Action {
    ATTACK, WALK, STAND
}

class Knight(val game: KnightGame) {
    private var stateTimer: Float = 0.0f
    private val animations : MutableMap<Direction, HeroAnimation> = mutableMapOf()
    var direction = Direction.LEFT
    var state = Action.STAND
    lateinit var sprite: Sprite

    init {
        addAnimation(Direction.DOWN, 0)
        addAnimation(Direction.UP, 48)
        addAnimation(Direction.LEFT, 96)
        addAnimation(Direction.RIGHT, 144)
    }

    fun create() {
        sprite = Sprite(Texture("sprites/knight.png"))
        sprite.setSize(48f, 48f)
    }

    private fun addAnimation(dir: Direction, top: Int) {
        val walkFrames = Array<TextureRegion>()
        val attackFrames = Array<TextureRegion>()

        for (i in 0..3) {
            walkFrames.add(TextureRegion(game.assetManager.atlas.findRegion("knight"), i * 48, top, 48, 48))
            attackFrames.add(TextureRegion(game.assetManager.atlas.findRegion("knight"), 192+ i * 48, top, 48, 48))
        }
        animations[dir] = HeroAnimation(
            walkFrames[1],
            Animation(0.1f, walkFrames, Animation.PlayMode.LOOP),
            Animation(0.1f, attackFrames, Animation.PlayMode.LOOP)
        )
        walkFrames.clear()
        attackFrames.clear()
    }

    fun update(dt: Float) {
        stateTimer += dt
        sprite.setRegion(getFrame())
        updatePosition()
    }

    private fun updatePosition() {
        var oldX = sprite.x
        var oldY = sprite.y
        val step = 2f

        if (state == Action.WALK) {
            when (direction) {
                Direction.LEFT -> oldX -= step
                Direction.RIGHT -> oldX += step
                Direction.DOWN -> oldY -= step
                Direction.UP -> oldY += step
            }
        }

        sprite.setPosition(oldX, oldY)
    }

    private fun getFrame(): TextureRegion {
        return when (state) {
            Action.WALK -> animations[direction]!!.walk.getKeyFrame(stateTimer)
            Action.ATTACK -> animations[direction]!!.attack.getKeyFrame(stateTimer)
            Action.STAND -> animations[direction]!!.stand
        }
    }
}
