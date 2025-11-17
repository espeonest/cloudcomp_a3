package com.example.assignment3_ChhetriHallRogers.repository;

import com.example.assignment3_ChhetriHallRogers.model.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CartsRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cart> rowMapper = ( rs, rowNum ) -> ( new Cart(
            rs.getInt("CartId"),
            rs.getString("sessionId"),
            rs.getInt("productCount"),
            rs.getFloat("totalPrice")
    ));

    // create & return cart if none exists for current session
    // returns cart from database otherwise
    public Cart getCart(String sessionId) {
        var existingCart = jdbcTemplate.query(
             "SELECT * FROM carts WHERE sessionid = ?",
                new Object[]{sessionId},
                rowMapper
        );
        if(existingCart.size() != 0) {
            return existingCart.getFirst(); // there should only ever be one value so "getfirst" is a syntax formality
        } else {
            var newCart = new Cart(sessionId);
            jdbcTemplate.update(
                    "INSERT INTO carts(sessionid) VALUES(?)",
                    newCart.getSessionId()
            );
            // now that a cart has been created, we need to update our newCart with the database's instance of it
            newCart = jdbcTemplate.query(
                    "SELECT * FROM carts WHERE sessionid = ?",
                    new Object[]{sessionId},
                    rowMapper
            ).getFirst();
            return newCart;
        }
    }

}
