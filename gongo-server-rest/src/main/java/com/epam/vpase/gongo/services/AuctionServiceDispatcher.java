package com.epam.vpase.gongo.services;

import org.springframework.stereotype.Service;

@Service
public class AuctionServiceDispatcher {
    public AuctionService dispatch(String auctionId){
        return new AuctionService();
    }
}
