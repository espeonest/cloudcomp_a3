package com.example.assignment3_ChhetriHallRogers.repository;

import com.example.assignment3_ChhetriHallRogers.model.Cart;
import com.example.assignment3_ChhetriHallRogers.model.CartList;
import com.example.assignment3_ChhetriHallRogers.model.Shoes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ShoesRepository shoesRepository;

    public CartsRepository(JdbcTemplate jdbcTemplate, ShoesRepository shoesRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.shoesRepository = shoesRepository;
    }

    private final RowMapper<Cart> cartRowMapper = (rs, rowNum ) -> ( new Cart(
            rs.getInt("cartid"),
            rs.getString("sessionid"),
            rs.getInt("productcount"),
            rs.getFloat("totalprice")
    ));

    public final RowMapper<Shoes> shoesRowMapper = (rs, rowNum) -> new Shoes(
            rs.getInt("shoeid"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("sku"),
            rs.getBoolean("isactive"),
            rs.getString("image")
    );

    private final RowMapper<CartList> cartListRowMapper = (rs, rowNum ) -> ( new CartList(
            rs.getInt("entryid"),
            rs.getInt("cartid"),
            rs.getInt("shoeid")
    ));

    // create & return cart if none exists for current session
    // returns cart from database otherwise
    public Cart getCart(String sessionId) {
        var existingCart = jdbcTemplate.query(
             "SELECT * FROM carts WHERE sessionid = ?",
                new Object[]{sessionId},
                cartRowMapper
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
                    cartRowMapper
            ).getFirst();
            return newCart;
        }
    }

    // grabs all items that match current session's cartId
    public List<Shoes> getCartContents(Cart cart) {
        List<Shoes> shoesList =  new ArrayList<>();
        var cartList = jdbcTemplate.query(
                "SELECT * FROM products_carts WHERE cartid=?",
                new Object[]{cart.getCartID()},
                cartListRowMapper
        );
        cartList.forEach(cartItem -> {
            if(cartItem.getCartId() == cart.getCartID()) {
                var shoeId = jdbcTemplate.query(
                        "SELECT * FROM products_carts WHERE entryid=?",
                        new Object[]{cartItem.getEntryId()},
                        cartListRowMapper).getFirst().getShoeId();
                var shoe = jdbcTemplate.query(
                        "SELECT * FROM shoes WHERE shoeid=?",
                        new Object[]{shoeId},
                        shoesRowMapper
                ).getFirst();
                shoesList.add(shoe);
            }
        });
        return shoesList;
    }

    public List<Shoes> addToCart(Cart cart, int shoeId) {
        jdbcTemplate.update(
                "INSERT INTO products_carts(cartid, shoeid) VALUES (?, ?)",
                cart.getCartID(),
                shoeId
                );

        List<Shoes> shoesList =  getCartContents(cart);
        return shoesList;
    }

}
