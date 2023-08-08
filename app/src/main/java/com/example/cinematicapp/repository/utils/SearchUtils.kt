package com.example.cinematicapp.repository.utils

object SearchUtils {

    fun setYears(item: String): MutableSet<String> {
        val prepareYearsList = mutableSetOf<String>()
        when (item) {
            TEN_TO_FIFTEEN -> {
                addYearsInRange(prepareYearsList, 2010, 2015)
            }

            FIVE_TO_TEN -> {
                addYearsInRange(prepareYearsList, 2005, 2010)
            }

            TWO_THOUSAND_TO_FIVE -> {
                addYearsInRange(prepareYearsList, 2000, 2005)
            }

            NINETY_TO_TWO_THOUSAND -> {
                addYearsInRange(prepareYearsList, 1990, 2000)
            }

            EIGHTY_TO_NINETY -> {
                addYearsInRange(prepareYearsList, 1980, 1990)
            }

            EIGHTY -> {
                addYearsInRange(prepareYearsList, 1900, 1979)
            }

            else -> {
                prepareYearsList.add(item)
            }
        }
        return prepareYearsList
    }

    private fun addYearsInRange(yearsList: MutableSet<String>, startYear: Int, endYear: Int) {
        for (year in startYear..endYear) {
            yearsList.add(year.toString())
        }
    }

    fun setRating(item: List<String>): Array<String> {
        var currentRating = "9"
        val parseRating = item.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(".", "")
            .replace("-", "")
            .replace(" ", "")
            .replace(",", "")
            .replace("0", "")

        parseRating.forEach {
            if (it.toString().toInt() < currentRating.toInt()) currentRating = it.toString()
        }
        return when (currentRating) {
            NINE_RATING -> arrayOf("9.0-9.9")
            EIGHT_RATING -> arrayOf("8.0-9.9")
            SEVEN_RATING -> arrayOf("7.0-9.9")
            SIX_RATING -> arrayOf("6.0-9.9")
            FIVE_RATING -> arrayOf("5.0-9.9")
            else -> arrayOf("9.0-9.9")
        }
    }

    const val NINE_RATING = "9"
    const val EIGHT_RATING = "8"
    const val SEVEN_RATING = "7"
    const val SIX_RATING = "6"
    const val FIVE_RATING = "5"

    const val TEN_TO_FIFTEEN = "2010-2015"
    const val FIVE_TO_TEN = "2005-2010"
    const val TWO_THOUSAND_TO_FIVE = "2000-2005"
    const val NINETY_TO_TWO_THOUSAND = "1990-2000"
    const val EIGHTY_TO_NINETY = "1980-1990"
    const val EIGHTY = "до 1980"
}