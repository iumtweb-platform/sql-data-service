package com.sqldataservice.api.feature.anime.detail;

import com.sqldataservice.api.shared.KeyValueItem;

public record DetailAnimeResponse(int id, String title, String synopsis, String imageUrl, KeyValueItem type,
    int episodes,
    KeyValueItem status, KeyValueItem rating, KeyValueItem[] genres, KeyValueItem[] studios,
    KeyValueItem[] themes,
    KeyValueItem[] demographics,
    KeyValueItem source, KeyValueItem[] licensors, KeyValueItem[] producers,
    KeyValueItem[] streamingServices,
    DetailAnimeResponseVoice[] voices) {

}
