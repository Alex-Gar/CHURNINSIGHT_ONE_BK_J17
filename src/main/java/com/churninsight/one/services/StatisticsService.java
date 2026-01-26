package com.churninsight.one.services;

import java.util.List;
import com.churninsight.one.models.dto.statistics.CompanyStatisticsDTO;
import com.churninsight.one.models.dto.statistics.ChurnByCategoryDTO;
import com.churninsight.one.models.dto.statistics.RevenueStatsDTO;

public interface StatisticsService {
    CompanyStatisticsDTO getCompanyStatistics();

    List<ChurnByCategoryDTO> getChurnByCategory(String category);

    List<RevenueStatsDTO> getRevenueStats();
}
