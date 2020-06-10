package com.epam.vpase.gongo.core;

import java.math.BigDecimal;

public class Bid implements HasId {
    public String id;
    public BigDecimal amount;
    public String currency;
    public BigDecimal conversionRate;
    public BigDecimal convertedAmount;
    public String bidder;
    public long time;

    @Override
    public String getId() {
        return id;
    }

    public String calculateId() {
        return bidder + "-" + time;
    }


}
