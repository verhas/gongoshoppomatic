package com.epam.vpase.gongo;

import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.core.Bid;
import com.epam.vpase.gongo.core.Product;
import com.epam.vpase.gongo.persistence.Manager;
import com.epam.vpase.gongo.persistence.file.ManagerImpl;

public class PersistenceManagers {
    public static Manager<Auction> auctionManager = new ManagerImpl<>("gongo-server-rest/src/main/resources/db/auction/");
    public static Manager<Product> productManager = new ManagerImpl<>("gongo-server-rest/src/main/resources/db/product/");
    public static Manager<Bid> bidManager = new ManagerImpl<>("gongo-server-rest/src/main/resources/db/bid/");
}
