package com.sqldataservice.api.feature.anime.detail;

import com.sqldataservice.api.shared.AnimeSummaryItem;
import com.sqldataservice.api.shared.KeyValueItem;

public record DetailAnimeResponse(int id, String title, String synopsis, String imageUrl, KeyValueItem type,
		Double episodes,
		KeyValueItem status, KeyValueItem rating, KeyValueItem[] genres, KeyValueItem[] studios,
		KeyValueItem[] themes,
		KeyValueItem[] demographics,
		KeyValueItem source, KeyValueItem[] licensors, KeyValueItem[] producers,
		KeyValueItem[] streamingServices,
		AnimeSummaryItem[] recommendations,
		DetailAnimeResponseVoice[] voices) {

}
