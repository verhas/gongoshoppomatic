package com.epam.vpase.gongo.controllers;

import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.core.Bid;
import com.epam.vpase.gongo.core.Product;
import com.epam.vpase.gongo.message.AuctionRequest;
import com.epam.vpase.gongo.message.AuctionResponse;
import com.epam.vpase.gongo.message.BidRequest;
import com.epam.vpase.gongo.services.AuctionRegister;
import com.epam.vpase.gongo.services.AuctionService;
import com.epam.vpase.gongo.services.AuctionServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.epam.vpase.gongo.PersistenceManagers.auctionManager;
import static com.epam.vpase.gongo.PersistenceManagers.bidManager;
import static com.epam.vpase.gongo.PersistenceManagers.productManager;

@RestController
public class PresentationService {

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @GetMapping("/list/auction/{id}")
    public AuctionResponse listAuction(@PathVariable(value = "id", required = true) final String id) {
        AuctionResponse response = new AuctionResponse();
        response.auction = auctionManager.load(id);
        response.product = productManager.load(response.auction.productId);
        for (String bidId : response.auction.bids) {
            Bid bid = bidManager.load(bidId);
            response.bidList.add(bid);
        }
        return response;
    }

    @GetMapping("/list/auctions/{id}")
    public List<AuctionResponse> listAuctions(@PathVariable(value = "id", required = true) String id) {
        if (id.equals("*")) {
            id = "";
        }
        List<AuctionResponse> res = new ArrayList<AuctionResponse>();
        for (String auctionId : auctionManager.list(id)) {
            AuctionResponse auctionResponse = new AuctionResponse();
            auctionResponse.auction = auctionManager.load(auctionId);
            auctionResponse.product = productManager.load(auctionResponse.auction.productId);
            for (String bidId : auctionResponse.auction.bids) {
                Bid bid = bidManager.load(bidId);
                auctionResponse.bidList.add(bid);
            }
            res.add(auctionResponse);
        }
        return res;
    }

    @GetMapping("/list/product/{id}")
    public Product listProduct(@PathVariable(value = "id", required = true) final String id) {
        return productManager.load(id);
    }

    @GetMapping("/list/products/{id}")
    public List<Product> listProducts(@PathVariable(value = "id", required = true) String id) {
        if (id.equals("*")) {
            id = "";
        }
        List<Product> products = new ArrayList<>();
        for (String productId : productManager.list(id)) {
            Product product = productManager.load(productId);
            products.add(product);
        }
        return products;
    }


    @GetMapping("/html/{page}")
    @ResponseBody
    public String index(@PathVariable(value = "page", required = true) final String page) throws IOException {
        return Files.readString(Path.of(
            "/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/gongo-server-rest/src/main/resources/"
                + page + ".html"));
    }

    @Autowired
    public AuctionServiceDispatcher dispatcher;

    @PostMapping("/bid/{auction}")
    public void bid(@PathVariable(value = "auction", required = true) final String auctionId,
                            @RequestBody BidRequest bidRequest, Principal principal) {
        String login = principal.getName();

        AuctionService service = dispatcher.dispatch(auctionId);
        Bid newBid = convertPostDataToBid(bidRequest, login);
        service.processBid(newBid, auctionId);
    }

    @Autowired
    AuctionRegister register;

    @PostMapping("/auction/")
    public String auction(@RequestBody AuctionRequest auctionRequest, Principal principal) {
        String login = principal.getName();

        Auction auction = convertPostDataToAuction(auctionRequest, login);
        return register.scheduleAuction(auction);
    }

    private Auction convertPostDataToAuction(AuctionRequest auction, String user) {
        Auction newAuction = new Auction();
        newAuction.bids = new ArrayList<>();
        newAuction.startTime = auction.startTime;
        newAuction.endTime = auction.endTime;
        newAuction.minimumPrice = auction.minPrice;
        newAuction.currency = auction.currency;
        newAuction.productId = auction.productId;
        newAuction.auctioner = user;
        return newAuction;
    }

    private Bid convertPostDataToBid(BidRequest bid, String user) {
        Bid newBid = new Bid();
        newBid.amount = bid.amount;
        newBid.currency = bid.currency;
        newBid.bidder = user;
        newBid.time = System.currentTimeMillis();
        newBid.conversionRate = BigDecimal.ONE;
        newBid.convertedAmount = newBid.conversionRate.multiply(bid.amount);
        return newBid;
    }
}
