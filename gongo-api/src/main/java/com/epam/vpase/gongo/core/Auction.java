package com.epam.vpase.gongo.core;

import java.math.BigDecimal;
import java.util.List;

public class Auction implements HasId {
    public String id;
    public String auctioner;
    public String productId;
    public BigDecimal minimumPrice;
    public String currency;
    public List<String> bids;
    public long startTime;
    public long endTime;

    @Override
    public String getId() {
        return id;
    }

}
