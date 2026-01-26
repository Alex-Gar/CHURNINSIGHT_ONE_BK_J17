package com.churninsight.one.models.dto.statistics;

import java.math.BigDecimal;

public record RevenueStatsDTO(
        String period,
        BigDecimal revenue,
        Long customers,
        BigDecimal averagePerCustomer) {
}
