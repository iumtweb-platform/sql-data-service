package com.sqldataservice.api.feature.anime.list;

public record ListAnimeResponseContent(int id, String title, Integer year, String imageUrl, String type,
    String synopsis) {
}