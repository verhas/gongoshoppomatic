package com.epam.vpase.gongo.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions {
    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Only USD is allowed as currency")
    public static class Currency extends RuntimeException {
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Auction time is not appropriate")
    public static class BadTime extends RuntimeException {
        public BadTime(String message) {
            super(message);
        }
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Bid amount is not high enough")
    public static class AmountIsLow extends RuntimeException {
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Product id is invalid")
    public static class Product extends RuntimeException {
        public Product(String id) {
            super(id);
        }
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Auction id is invalid")
    public static class Auction extends RuntimeException {
        public Auction(String id) {
            super(id);
        }
    }
}
