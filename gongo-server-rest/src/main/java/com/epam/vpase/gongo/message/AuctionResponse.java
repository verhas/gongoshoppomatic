package com.epam.vpase.gongo.message;

import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.core.Bid;
import com.epam.vpase.gongo.core.Product;

import java.util.ArrayList;
import java.util.List;

public class AuctionResponse {
    public Auction auction;
    public Product product;
    public List<Bid> bidList = new ArrayList<>();
}
