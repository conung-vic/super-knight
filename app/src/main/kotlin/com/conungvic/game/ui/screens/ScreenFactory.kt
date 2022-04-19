package com.conungvic.game.ui.screens

import com.conungvic.game.KnightGame

object ScreenFactory {
    enum class ScreenType {
        TITLE,
        LOAD,
        OPTION,
        CREDIT,
        NEW_GAME,
        MAPPED_LEVEL,
        GENERATED_LEVEL,
        NONE
    }

    fun getScreen(type: ScreenType, game: KnightGame): CommonScreen? {
        return when (type) {
            ScreenType.TITLE -> TitleScreen(game)
            ScreenType.LOAD -> LoadScreen(game)
            ScreenType.OPTION -> OptionScreen(game)
            ScreenType.CREDIT -> CreditScreen(game)
            ScreenType.NEW_GAME -> NewGameScreen(game)
            ScreenType.MAPPED_LEVEL -> GameScreen(game)
            ScreenType.GENERATED_LEVEL -> GeneratedLevelScreen(game)
            ScreenType.NONE -> null
        }
    }
}