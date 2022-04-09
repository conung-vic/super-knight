package com.conungvic.game.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class CustomAssetManager: AssetManager() {
    private var assetsLoading = true
    val atlas: TextureAtlas = TextureAtlas()

    override fun update(): Boolean {
        val res = super.update()
        if(isFinished && assetsLoading) {
            assetsLoading = false
        }
        return res
    }

    fun loadResources() {
        val atlasData: TextureAtlas.TextureAtlasData = TextureAtlas.TextureAtlasData()
        atlasData.load(
            Gdx.files.internal("sprites/knight.atlas"),
            Gdx.files.internal("sprites/"),
            false
        )
        atlas.load(atlasData)
    }
}