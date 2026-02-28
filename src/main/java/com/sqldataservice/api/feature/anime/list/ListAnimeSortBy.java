package com.sqldataservice.api.feature.anime.list;

public class ListAnimeSortBy {
    public static final String TITLE = "title";
    public static final String RELEASE_DATE = "startDate";
    public static final String POPULARITY = "popularity";
    public static final String SCORE = "score";
    public static final String YEAR = "year";

    public static boolean isValid(String value) {
        return TITLE.equalsIgnoreCase(value) ||
                RELEASE_DATE.equalsIgnoreCase(value) ||
                POPULARITY.equalsIgnoreCase(value) ||
                SCORE.equalsIgnoreCase(value) ||
                YEAR.equalsIgnoreCase(value);
    }

    public static String listAllString() {
        return String.join(", ", TITLE, RELEASE_DATE, POPULARITY, SCORE, YEAR);
    }
}
