package com.sqldataservice.api.feature.anime.list;

public record ListAnimeResponse(int elementsPerPage, int page, long totalElements, int totalPages,
                ListAnimeResponseContent[] content) {
}
