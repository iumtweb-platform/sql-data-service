package com.sqldataservice.api.feature.anime.detail;

public record DetailAnimeResponseCharacter(int characterId, String characterName, String characterImageUrl,
        int voiceActorId,
        String voiceActorName, String voiceActorImageUrl) {

}