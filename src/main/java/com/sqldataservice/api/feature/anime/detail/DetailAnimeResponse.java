package com.sqldataservice.api.feature.anime.detail;

public record DetailAnimeResponse(int id, String title, String synopsis, String imageUrl, String type, int episodes,
    String status, String rating, String[] genres, String[] studios, DetailAnimeResponseCharacter[] characters) {

}
