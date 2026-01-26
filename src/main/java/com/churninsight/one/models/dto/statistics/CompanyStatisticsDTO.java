package com.churninsight.one.models.dto.statistics;

import java.math.BigDecimal;

public record CompanyStatisticsDTO(
        Long totalCustomers,
        Long activeCustomers,
        Long churnedCustomers,
        Double churnRate,
        BigDecimal averageRevenue,
        BigDecimal totalRevenue,
        Double averageTenure,
        Long newCustomersThisMonth) {
}
