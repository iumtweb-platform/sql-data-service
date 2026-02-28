package com.sqldataservice.api.shared;

public record AnimeSummaryItem(int id, String title, Integer year, String imageUrl, String type, double score,
        String synopsis) {

}
