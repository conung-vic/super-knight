package com.conungvic.game.utils

import com.badlogic.gdx.assets.AssetManager

class CustomAssetManager: AssetManager() {
    private var assetsLoading = true

    override fun update(): Boolean {
        val res = super.update()
        if(isFinished && assetsLoading) {
            assetsLoading = false

        }
        return res
    }

    fun loadResources() {

    }
}