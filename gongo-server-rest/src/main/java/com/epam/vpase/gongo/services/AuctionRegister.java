package com.epam.vpase.gongo.services;

import com.epam.vpase.gongo.PersistenceManagers;
import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.message.Exceptions;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.epam.vpase.gongo.PersistenceManagers.auctionManager;
import static com.epam.vpase.gongo.PersistenceManagers.productManager;

@Service
public class AuctionRegister {

    public String scheduleAuction(Auction auction) {
        long now = System.currentTimeMillis();
        if (auction.startTime < now|| auction.startTime > auction.endTime ){
            throw new Exceptions.BadTime("Start or End Time is bad.");
        }
        if( !auction.currency.equals("USD")){
            throw new Exceptions.Currency();
        }
        try {
            productManager.load(auction.productId);
        }catch(Exception e){
            throw new Exceptions.Product(auction.productId);
        }
        auction.id = UUID.randomUUID().toString();
        auctionManager.save(auction);
        return auction.id;
    }
}
