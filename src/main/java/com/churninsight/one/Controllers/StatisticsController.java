package com.churninsight.one.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.churninsight.one.models.dto.statistics.ChurnByCategoryDTO;
import com.churninsight.one.models.dto.statistics.CompanyStatisticsDTO;
import com.churninsight.one.models.dto.statistics.RevenueStatsDTO;
import com.churninsight.one.services.StatisticsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/statistics")
@Tag(name = "Statistics", description = "Endpoints for company and churn statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/company")
    public ResponseEntity<CompanyStatisticsDTO> getCompanyStatistics() {
        return ResponseEntity.ok(statisticsService.getCompanyStatistics());
    }

    @GetMapping("/churn-by/{category}")
    public ResponseEntity<List<ChurnByCategoryDTO>> getChurnByCategory(@PathVariable String category) {
        return ResponseEntity.ok(statisticsService.getChurnByCategory(category));
    }

    @GetMapping("/revenue")
    public ResponseEntity<List<RevenueStatsDTO>> getRevenueStats() {
        return ResponseEntity.ok(statisticsService.getRevenueStats());
    }
}
