package com.example.assignment3_ChhetriHallRogers.controller;

import com.example.assignment3_ChhetriHallRogers.model.Cart;
import com.example.assignment3_ChhetriHallRogers.repository.CartsRepository;
import com.example.assignment3_ChhetriHallRogers.repository.ShoesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final CartsRepository cartsRepository;
    private final ShoesRepository shoesRepository;

    public CartController(CartsRepository cartsRepository, ShoesRepository shoesRepository) {
        this.cartsRepository = cartsRepository;
        this.shoesRepository = shoesRepository;
    }

    @GetMapping("/cart")
    public Cart getCart(HttpSession session) {
        String sessionId = session.getId();
        session.setAttribute("sessionId", sessionId);
        return cartsRepository.getCart(sessionId);
    }
}
