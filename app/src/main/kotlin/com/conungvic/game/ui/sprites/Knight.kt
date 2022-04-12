package com.conungvic.game.ui.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.utils.Array
import com.conungvic.game.Config
import com.conungvic.game.KnightGame
import com.conungvic.game.utils.PLAYER_BIT
import org.lwjgl.opengl.Display.getHeight

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class Action {
    WALK, STAND
}

class Knight(val game: KnightGame) {
    var direction = Direction.LEFT
    var state = Action.STAND
    var isAttacking = false
    val velocity = Vector2(0f, 0f)

    private var stateTimer: Float = 0.0f
    private val animations : MutableMap<Direction, HeroAnimation> = mutableMapOf()

    private lateinit var sprite: Sprite
    private lateinit var body : Body

    init {
        addAnimation(Direction.DOWN, 0)
        addAnimation(Direction.UP, 48)
        addAnimation(Direction.LEFT, 96)
        addAnimation(Direction.RIGHT, 144)
    }

    fun create() {
        sprite = Sprite(Texture("sprites/knight.png"))
        sprite.setSize(48f, 48f)
        sprite.setPosition(200f, 200f)
        defineBody()
    }

    private fun defineBody() {
        val bDef = BodyDef()
        bDef.position.set(sprite.x, sprite.y)
        bDef.type = BodyDef.BodyType.DynamicBody
        body = game.world.createBody(bDef)

        val fDef = FixtureDef()
        fDef.filter.categoryBits = PLAYER_BIT
        fDef.filter.maskBits = PLAYER_BIT

        val shape = PolygonShape()
        val vertices = arrayOfNulls<Vector2>(4)
        vertices[0] = Vector2(-10f, 16f) // .scl(1 / Config.ppm)
        vertices[1] = Vector2(10f, 16f) // .scl(1 / Config.ppm)
        vertices[2] = Vector2(10f, -13f) // .scl(1 / Config.ppm)
        vertices[3] = Vector2(-10f, -13f) // .scl(1 / Config.ppm)
        shape.set(vertices)

        fDef.shape = shape
        body.createFixture(fDef).userData = this
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
        body.linearVelocity = velocity
        sprite.setPosition(body.position.x - sprite.width / 2, body.position.y - sprite.height / 2)
    }

    private fun getFrame(): TextureRegion {
        return if (isAttacking) {
            animations[direction]!!.attack.getKeyFrame(stateTimer)
        } else {
            when (state) {
                Action.WALK -> animations[direction]!!.walk.getKeyFrame(stateTimer)
                Action.STAND -> animations[direction]!!.stand
            }
        }
    }

    fun draw(batch: SpriteBatch) {
        sprite.draw(batch)
    }
}
