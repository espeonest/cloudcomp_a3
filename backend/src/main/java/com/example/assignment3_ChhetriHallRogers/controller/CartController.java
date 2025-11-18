package com.example.assignment3_ChhetriHallRogers.controller;

import com.example.assignment3_ChhetriHallRogers.model.Cart;
import com.example.assignment3_ChhetriHallRogers.model.CartViewModel;
import com.example.assignment3_ChhetriHallRogers.model.Shoes;
import com.example.assignment3_ChhetriHallRogers.repository.CartsRepository;
import com.example.assignment3_ChhetriHallRogers.repository.ShoesRepository;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartsRepository cartsRepository;
    public CartController(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    // debating taking out the get mapping here
    // might only be used for internal calls
    @GetMapping("/cart")
    public Cart getCart(HttpSession session) {
        String sessionId = session.getId();
        session.setAttribute("sessionId", sessionId);
        return cartsRepository.getCart(sessionId);
    }

    @GetMapping("/cart/items")
    public CartViewModel getCartItems(HttpSession session) {
        Cart currentCart = getCart(session);
        return cartsRepository.getCartViewModel(currentCart);
    }

    @PostMapping("/cart/add/{shoeid}")
    public CartViewModel addToCart(@PathVariable int shoeid, HttpSession session) {
        Cart currentCart = getCart(session);
        cartsRepository.addToCart(currentCart, shoeid);
        return cartsRepository.getCartViewModel(currentCart);
    }

    @DeleteMapping("/cart/remove/{entryid}")
    public CartViewModel removeFromCart(HttpSession session, @PathVariable int entryid) {
        String sessionId = session.getAttribute("sessionId").toString();
        Cart currentCart = cartsRepository.getCart(sessionId);
        return cartsRepository.removeFromCart(currentCart, entryid);
    }

    @DeleteMapping("/cart/remove-all")
    public Cart removeAllFromCart(HttpSession session) {
        String sessionId = session.getAttribute("sessionId").toString();
        Cart currentCart = cartsRepository.getCart(sessionId);
        return cartsRepository.removeAllFromCart(currentCart);
    }
}
