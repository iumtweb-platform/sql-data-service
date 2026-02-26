package com.sqldataservice.api.feature.anime.list;

import com.sqldataservice.api.shared.AnimeSummaryItem;

public record ListAnimeResponse(int elementsPerPage, int page, long totalElements, int totalPages,
    AnimeSummaryItem[] items) {
}
