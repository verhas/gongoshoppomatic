package com.epam.vpase.gongo;

import com.epam.vpase.gongo.core.Auction;
import com.epam.vpase.gongo.core.Bid;
import com.epam.vpase.gongo.core.Product;
import com.epam.vpase.gongo.core.User;
import com.epam.vpase.gongo.persistence.Manager;
import com.epam.vpase.gongo.persistence.file.ManagerImpl;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestCreateSampleAuction {
    public Manager<Auction> auctionManager = new ManagerImpl<>("/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/gongo-server-rest/src/main/resources/db/auction");
    public Manager<User> userManager = new ManagerImpl<>("/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/gongo-server-rest/src/main/resources/db/user");
    public Manager<Product> productManager = new ManagerImpl<>("/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/gongo-server-rest/src/main/resources/db/product");
    public Manager<Bid> bidManager = new ManagerImpl<>("/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/gongo-server-rest/src/main/resources/db/bid");

    //@Test
    void createSampleAuctions() {
        List<String> products = productManager.list("");
        List<String> users = userManager.list("");
        Random random = new Random();
        int j = 10000;
        for (String productId : products) {
            Auction auction = new Auction();
            auction.id = "" + j++;
            auction.productId = productId;
            String minp = "" + Math.abs(random.nextInt() % 1000) + "." + Math.abs(random.nextInt() % 100);
            auction.minimumPrice = new BigDecimal(minp);
            auction.currency = "USD";
            auction.auctioner = users.get(j % users.size());
            auction.bids = new LinkedList<>();
            long now = System.currentTimeMillis();
            auction.startTime = now - 24 * 60 * 60 * 1000 * Math.abs(random.nextInt() % 7);
            auction.endTime = now + 24 * 60 * 60 * 1000 * Math.abs(random.nextInt() % 7);
            int nrOfBids = Math.abs(random.nextInt() % 10);
            BigDecimal bidPrice = auction.minimumPrice;
            long bidTime = auction.startTime + 60 * 1000;
            for (int i = 0; i < nrOfBids; i++) {
                Bid bid = new Bid();
                bid.amount = bidPrice;
                bid.currency = "USD";
                bid.convertedAmount = bidPrice;
                bid.conversionRate = BigDecimal.ONE;
                bid.time = bidTime;
                bid.id = "" + auction.id + "_" + i;
                bid.bidder = users.get(Math.abs(random.nextInt() % users.size()));
                bidPrice = bidPrice.add(new BigDecimal(Math.abs(random.nextInt() % 50)));
                bidTime = (now + bidTime) / 2;
                bidManager.save(bid);
                auction.bids.add(bid.id);
            }
            auctionManager.save(auction);
        }
    }

    //@Test
    void createSampleUsers() throws IOException {
        final String csv = "/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/REFERENCE_DATA/users.txt";
        final BufferedReader reader = new BufferedReader(new FileReader(new File(csv)));
        String line = reader.readLine();// skip header line
        System.out.println("header:" + line);
        Manager<Product> productManager = new ManagerImpl<>("db/product");
        Random random = new Random();
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            User user = new User();
            user.name = elements[0] + ", " + elements[1];
            user.login = elements[2];
            user.verified = random.nextBoolean();
            userManager.save(user);
        }
    }

    //@Test
    void createProducts() throws IOException {
        final String csv = "/Users/verhasp/OneDrive - EPAM/ASE_assessment/gongoshoppomatic/REFERENCE_DATA/DatafinitiElectronicsProductData.csv";
        final BufferedReader reader = new BufferedReader(new FileReader(new File(csv)));
        String line = reader.readLine();// skip header line
        System.out.println("header:" + line);
        Manager<Product> productManager = new ManagerImpl<>("db/product");
        int j = 100;
        String previousName = "";
        while ((line = reader.readLine()) != null && j > 0) {
            String[] elements = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            Product product = new Product();
            int i = 0;
            product.id = elements[i++] + "_" + j;
            product.asins = elements[i++];
            product.brand = elements[i++];
            product.categories = elements[i++];
            product.colors = elements[i++];
            product.dateAdded = elements[i++];
            product.dateUpdated = elements[i++];
            product.dimension = elements[i++];
            product.ean = elements[i++];
            product.imageURLs = elements[i++];
            product.keys = elements[i++];
            product.manufacturer = elements[i++];
            product.manufacturerNumber = elements[i++];
            product.name = elements[i++];
            product.primaryCategories = elements[i++];
            product.reviewsDate = elements[i++];
            product.reviewsDateSeen = elements[i++];
            product.reviewsDoRecommend = elements[i++];
            product.reviewsNumHelpful = elements[i++];
            product.reviewsRating = elements[i++];
            product.reviewsSourceURLs = elements[i++];
            product.reviewsText = elements[i++];
            product.reviewsTitle = elements[i++];
            product.reviewsUsername = elements[i++];
            product.sourceURLs = elements[i++];
            product.upc = elements[i++];
            product.weight = elements[i++];
            if (!previousName.equals(product.name)) {
                productManager.save(product);
                j--;
            }
            previousName = product.name;
        }
    }

    //@Test
    void time(){
        System.out.println(System.currentTimeMillis());
    }
}
