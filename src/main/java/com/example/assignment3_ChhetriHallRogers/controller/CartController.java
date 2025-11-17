package com.example.assignment3_ChhetriHallRogers.controller;

import com.example.assignment3_ChhetriHallRogers.model.Cart;
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
    private final ShoesRepository shoesRepository;

    public CartController(CartsRepository cartsRepository, ShoesRepository shoesRepository) {
        this.cartsRepository = cartsRepository;
        this.shoesRepository = shoesRepository;
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
    public List<Shoes> getCartItems(HttpSession session) {
        Cart currentCart = getCart(session);
        return cartsRepository.getCartContents(currentCart);
    }

    @PostMapping("/cart/add/{shoeid}")
    public List<Shoes> addToCart(@PathVariable int shoeid, HttpSession session) {
        Cart currentCart = getCart(session);
        return cartsRepository.addToCart(currentCart, shoeid);
    }

    @DeleteMapping("/cart/remove")
    public Cart removeFromCart(HttpSession session) {
        String sessionId = session.getAttribute("sessionId").toString();
        // rep remove from cart function
        return cartsRepository.getCart(sessionId);
    }

    @DeleteMapping("/cart/remove-all")
    public Cart removeAllFromCart(HttpSession session) {
        String sessionId = session.getAttribute("sessionId").toString();
        // rep remove all from cart function
        return cartsRepository.getCart(sessionId);
    }
}
