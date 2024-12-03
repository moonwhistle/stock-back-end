package com.example.investment_api.virtual.calculator.mapper;

import com.example.investment_api.member.application.member.MemberService;
import com.example.investment_api.virtual.account.controller.dto.AllResultDTO;
import com.example.investment_api.virtual.account.controller.dto.AccountStockData;
import com.example.investment_api.virtual.account.controller.dto.ResultDTO;
import com.example.investment_api.virtual.calculator.service.MemberRankService;
import com.example.investment_api.virtual.calculator.service.StockCalculatorService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StockCalculationMapper {

    private final StockCalculatorService stockCalculatorService;
    private final MemberService memberService;
    private final MemberRankService memberRankService;

    public StockCalculationMapper(StockCalculatorService stockCalculatorService, MemberService memberService, MemberRankService memberRankService) {
        this.stockCalculatorService = stockCalculatorService;
        this.memberService = memberService;
        this.memberRankService = memberRankService;
    }

    public ResultDTO toResultDTO(String stockName, AccountStockData dto) {
        double evaluationProfit = stockCalculatorService.calculateEvaluationProfit(dto.buyPrice(), dto.currentPrice(), dto.stockCount());
        double profitRate = stockCalculatorService.calculateProfitRate(dto.buyPrice(), dto.currentPrice());
        int purchaseAmount = stockCalculatorService.calculatePurchaseAmount(dto.buyPrice(), dto.stockCount());
        int evaluationAmount = stockCalculatorService.calculateEvaluationAmount(dto.currentPrice(), dto.stockCount());

        return new ResultDTO(
                stockName,
                dto.currentPrice(),
                dto.stockCount(),
                dto.prevChangeRate(),
                evaluationProfit,
                profitRate,
                purchaseAmount,
                evaluationAmount
        );
    }

    public AllResultDTO toAllResultDTO(List<AccountStockData> dtoList, Long memberId) {
        double totalEvaluationProfit = stockCalculatorService.calculateTotalEvaluationProfit(dtoList);
        double totalPurchaseAmount = stockCalculatorService.calculateTotalPurchaseAmount(dtoList);
        double totalProfit = stockCalculatorService.calculateTotalProfit(dtoList);
        int totalEvaluationAmount = stockCalculatorService.calculateTotalEvaluationAmount(dtoList);
        String memberNickname = memberService.getMemberNickName(memberId);

        Map<Long, Integer> rankMap = memberRankService.calculateMemberRanks();
        int rank = rankMap.getOrDefault(memberId, -1);
        return new AllResultDTO(
                totalEvaluationProfit,
                totalPurchaseAmount,
                totalProfit,
                totalEvaluationAmount,
                memberNickname,
                rank
        );
    }
}

