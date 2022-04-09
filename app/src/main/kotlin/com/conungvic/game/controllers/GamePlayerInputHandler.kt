package com.conungvic.game.controllers

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Direction
import com.conungvic.game.ui.sprites.Knight

class GamePlayerInputHandler (
    private val player: Knight
    ): InputAdapter() {

    private var prevPlayerState = Action.STAND

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.DOWN -> {
                player.state = Action.WALK
                player.direction = Direction.DOWN
                return true
            }
            Input.Keys.UP -> {
                player.state = Action.WALK
                player.direction = Direction.UP
                return true
            }
            Input.Keys.LEFT -> {
                player.state = Action.WALK
                player.direction = Direction.LEFT
                return true
            }
            Input.Keys.RIGHT -> {
                player.state = Action.WALK
                player.direction = Direction.RIGHT
                return true
            }
            Input.Keys.SPACE -> {
                this.prevPlayerState = player.state
                player.state = Action.ATTACK
                return true
            }
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.DOWN -> {
                player.state = Action.STAND
                player.direction = Direction.DOWN
                return true
            }
            Input.Keys.UP -> {
                player.state = Action.STAND
                player.direction = Direction.UP
                return true
            }
            Input.Keys.LEFT -> {
                player.state = Action.STAND
                player.direction = Direction.LEFT
                return true
            }
            Input.Keys.RIGHT -> {
                player.state = Action.STAND
                player.direction = Direction.RIGHT
                return true
            }
            Input.Keys.SPACE -> {
                player.state = prevPlayerState
                return true
            }
        }
        return false
    }
}