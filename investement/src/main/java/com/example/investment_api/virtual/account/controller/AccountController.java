package com.example.investment_api.virtual.account.controller;

import com.example.investment_api.global.annotation.Member;

import com.example.investment_api.virtual.account.controller.dto.*;

import com.example.investment_api.virtual.account.domain.MemberAccount;

import com.example.investment_api.virtual.account.domain.MemberOrder;
import com.example.investment_api.virtual.account.service.AccountService;
import com.example.investment_api.virtual.account.service.OrderService;

import com.example.investment_api.virtual.account.service.StockDataTransferService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final OrderService memberAccountService;
    private final StockDataTransferService transferService;
    private final AccountService accountService;

    public AccountController(OrderService memberAccountService, StockDataTransferService transferService, AccountService accountService) {
        this.memberAccountService = memberAccountService;
        this.transferService = transferService;
        this.accountService = accountService;
    }

    @PostMapping("/buy")
    public BuyResponse buyStock(@Member Long memberId,
                                @RequestBody BuyRequest request
    ) {
        return memberAccountService.buyStockImmediately(memberId, request.stockName(), request.quantity());
    }

    @PostMapping("/sell")
    public SellResponse sellStock(@Member Long memberId,
                                  @RequestBody SellRequest request
    ) {
        return memberAccountService.sellStockImmediately(memberId, request.stockName(), request.quantity());
    }

    @PostMapping("/order/buy")
    public LimitOrderResponse placeLimitOrderForBuy(@Member Long memberId,
                                                    @RequestBody OrderRequest request
    ) {
        return memberAccountService.placeLimitOrderForBuy(memberId, request.stockName(), request.limitPrice(), request.quantity());
    }

    @PostMapping("/order/sell")
    public LimitOrderResponse placeLimitOrderForSell(@Member Long memberId,
                                                     @RequestBody OrderRequest orderRequest) {
        return memberAccountService.placeLimitOrderForSell(memberId, orderRequest.stockName(), orderRequest.limitPrice(), orderRequest.quantity());
    }

    @GetMapping("/accounts")
    public List<MemberAccount> getMemberAccounts(@Member Long memberId) {
        return accountService.getMemberAccounts(memberId);
    }

    @GetMapping("/accounts/{stockName}")
    public MemberAccount getMemberAccount(@Member Long memberId,
                                          @PathVariable String stockName) {
        return accountService.getMemberAccount(memberId, stockName);
    }

    @GetMapping("/orders/{stockName}")
    public List<OrderData> getMemberOrders(@Member Long memberId, @PathVariable String stockName) {
        return transferService.getStockOrderData(memberId, stockName);
    }


    @PutMapping("/order/{orderId}/modify")
    public String modifyOrder(@Member Long memberId,
                              @PathVariable int orderId,
                              @RequestBody StockOrderDTO updatedOrder) {
        return memberAccountService.modifyOrder(memberId, orderId, updatedOrder);
    }

    @DeleteMapping("/order/{orderId}/cancel")
    public String cancelOrder(@Member Long memberId, @PathVariable int orderId) {
        return memberAccountService.cancelOrder(memberId, orderId);
    }

    @GetMapping("accounts/save/{stockName}")
    public List<MemberOrder> getMemberOrdersList(@Member Long memberId, @PathVariable String stockName) {
        return memberAccountService.getMemberOrders(memberId, stockName);
    }
}
