package com.conungvic.game.controllers

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.conungvic.game.ui.sprites.Action
import com.conungvic.game.ui.sprites.Knight

class GamePlayerInputHandler (
    private val player: Knight
    ): InputAdapter() {

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.SPACE -> {
                player.isAttacking = true
            }
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.SPACE -> {
                player.isAttacking = false
            }
            Input.Keys.LEFT, Input.Keys.RIGHT,
            Input.Keys.DOWN, Input.Keys.UP-> {
                player.state = Action.STAND
            }
        }
        return false
    }
}