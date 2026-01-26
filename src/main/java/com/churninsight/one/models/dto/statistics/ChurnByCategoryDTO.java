package com.churninsight.one.models.dto.statistics;

public record ChurnByCategoryDTO(
        String category,
        String value,
        Long churnCount,
        Long totalCount,
        Double churnRate) {
}
