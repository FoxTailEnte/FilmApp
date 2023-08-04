package com.example.cinematicapp.repository.utils

object SearchUtils {

    fun setYears(item: String): MutableSet<String> {
        val prepareYearsList = mutableSetOf<String>()
        when (item) {
            TEN_TO_FIFTEEN -> {
                prepareYearsList.add("2010")
                prepareYearsList.add("2011")
                prepareYearsList.add("2012")
                prepareYearsList.add("2013")
                prepareYearsList.add("2014")
                prepareYearsList.add("2015")
            }

            FIVE_TO_TEN -> {
                prepareYearsList.add("2005")
                prepareYearsList.add("2006")
                prepareYearsList.add("2007")
                prepareYearsList.add("2008")
                prepareYearsList.add("2009")
                prepareYearsList.add("2010")
            }

            TWO_THOUSAND_TO_FIVE -> {
                prepareYearsList.add("2000")
                prepareYearsList.add("2001")
                prepareYearsList.add("2002")
                prepareYearsList.add("2003")
                prepareYearsList.add("2004")
                prepareYearsList.add("2005")
            }

            NINETY_TO_TWO_THOUSAND -> {
                prepareYearsList.add("1990")
                prepareYearsList.add("1991")
                prepareYearsList.add("1992")
                prepareYearsList.add("1993")
                prepareYearsList.add("1994")
                prepareYearsList.add("1995")
                prepareYearsList.add("1996")
                prepareYearsList.add("1997")
                prepareYearsList.add("1998")
                prepareYearsList.add("1999")
                prepareYearsList.add("2000")
            }

            EIGHTY_TO_NINETY -> {
                prepareYearsList.add("1980")
                prepareYearsList.add("1981")
                prepareYearsList.add("1982")
                prepareYearsList.add("1983")
                prepareYearsList.add("1984")
                prepareYearsList.add("1985")
                prepareYearsList.add("1986")
                prepareYearsList.add("1987")
                prepareYearsList.add("1988")
                prepareYearsList.add("1989")
                prepareYearsList.add("1990")
            }

            EIGHTY -> {
                prepareYearsList.add("1950")
                prepareYearsList.add("1951")
                prepareYearsList.add("1952")
                prepareYearsList.add("1953")
                prepareYearsList.add("1954")
                prepareYearsList.add("1955")
                prepareYearsList.add("1956")
                prepareYearsList.add("1957")
                prepareYearsList.add("1958")
                prepareYearsList.add("1959")
                prepareYearsList.add("1960")
                prepareYearsList.add("1961")
                prepareYearsList.add("1962")
                prepareYearsList.add("1963")
                prepareYearsList.add("1964")
                prepareYearsList.add("1965")
                prepareYearsList.add("1966")
                prepareYearsList.add("1967")
                prepareYearsList.add("1968")
                prepareYearsList.add("1969")
                prepareYearsList.add("1970")
                prepareYearsList.add("1971")
                prepareYearsList.add("1972")
                prepareYearsList.add("1973")
                prepareYearsList.add("1974")
                prepareYearsList.add("1975")
                prepareYearsList.add("1976")
                prepareYearsList.add("1977")
                prepareYearsList.add("1978")
                prepareYearsList.add("1979")
            }

            else -> {
                prepareYearsList.add(item)
            }
        }
        return prepareYearsList
    }

    fun setRating(item: MutableList<String>): String {
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
            NINE_RATING -> "9.0-9.9"
            EIGHT_RATING -> "8.0-9.9"
            SEVEN_RATING -> "7.0-9.9"
            SIX_RATING -> "6.0-9.9"
            FIVE_RATING -> "5.0-9.9"
            else -> "9.0-9.9"
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