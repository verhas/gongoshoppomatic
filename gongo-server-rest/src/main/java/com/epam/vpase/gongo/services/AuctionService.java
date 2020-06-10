package com.epam.vpase.gongo.services;

import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.core.Bid;
import com.epam.vpase.gongo.message.Exceptions;

import static com.epam.vpase.gongo.PersistenceManagers.auctionManager;
import static com.epam.vpase.gongo.PersistenceManagers.bidManager;

public class AuctionService {

    public void processBid(Bid bid, String auctionId) {
        if (!bid.currency.equals("USD")) {
            throw new Exceptions.Currency();
        }
        final Auction auction = auctionManager.load(auctionId);
        for (String bidId : auction.bids) {
            Bid existingBid = bidManager.load(bidId);
            if (existingBid.amount.compareTo(bid.amount) > 0) {
                throw new Exceptions.AmountIsLow();
            }
        }
        bid.id = auctionId + "_" + auction.bids.size();
        auction.bids.add(bid.id);
        bidManager.save(bid);
        auctionManager.save(auction);
    }

}
