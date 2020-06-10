package com.epam.vpase.gongo.message;

import java.math.BigDecimal;

public class AuctionRequest {
    public String productId;
    public String currency;
    public BigDecimal minPrice;
    public long startTime;
    public long endTime;
}
/*
{
"productId" : "AVpf3txeLJeJML43FN82_0",
"currency" : "EUR",
"minPrice" : "99.34",
"startTime" : "1591832921716",
"endTime" :   "1591842921716"
}
 */