package com.example.cinematicapp.repository.utils

object Constants {
    object FilmInfo {
        const val EMPTY_TEXT = ""
        const val COMMA = ", "
        const val PLUS = "+"
        const val PRODUCERS = "режиссеры"
        const val EMPTY_FILM_INFO = "---"
        const val SPACE_TEXT = " "
    }

    object FireBase {
        const val USERS = "Users"
        const val PASS = "pass"
        const val NAME = "name"
        const val SECOND_NAME = "secondName"
        const val LIBRARY = "library"
        const val WATCH_LATER = "watchLater"
        const val FAIL = "fail"
    }

    object Validate {
        const val VALIDATE_NUMBER = "+7"
        const val PLUS = "+"
        const val ERROR_EMPTY_EDIT_TEXT = "Поле должно быть заполнено"
        const val ERROR_VALIDATE_NUMBER_SIZE = "Не верный формат номера"
    }

    object Pref {
        const val SHARED_PREF = "shared_preferences"
    }

    object Request {
        const val SEARCH = "Search"
        const val ID = "Id"
        const val GENRES_FILTER = "Жанры"
        const val YEARS_FILTER = "Год"
        const val RATING_FILTER = "Рейтинг"
        const val COUNTRY_FILTER = "Страна"
    }
}