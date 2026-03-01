package com.sqldataservice.api.feature.anime.list;

public record ListAnimeQuery(int page, int elementsPerPage, String search, String sortBy, String sortDirection,
        String types, String genres) {
}