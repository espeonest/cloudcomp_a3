package com.example.assignment3_ChhetriHallRogers.repository;

import com.example.assignment3_ChhetriHallRogers.model.Shoes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoesRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShoesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Shoes> shoesRowMapper = (rs, rowNum) -> new Shoes(
            rs.getInt("shoeid"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("sku"),
            rs.getBoolean("isactive"),
            rs.getString("image")
    );

    public List<Shoes> findAll() {
        return jdbcTemplate.query("SELECT * FROM shoes", shoesRowMapper);
    }

    public Shoes findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM shoes WHERE shoeid = ?",
                new Object[]{id},
                shoesRowMapper
        );
    }


    public int save(Shoes shoe) {
        return jdbcTemplate.update(
                "INSERT INTO shoes(shoeid, name, description, price, sku, isactive, image) VALUES (?, ?, ?, ?, ?, ?, ?)",
                shoe.getShoeID(),
                shoe.getName(),
                shoe.getDescription(),
                shoe.getPrice(),
                shoe.getSku(),
                shoe.isActive(),
                shoe.getImage()
        );
    }

    //Update Shoe By ID sql statement
    public int update (Shoes shoe) {
        return jdbcTemplate.update(
                "UPDATE shoes SET name = ?, description = ?, price = ?, sku = ?, isactive = ?, image =? WHERE shoeid = ?",
                shoe.getName(),
                shoe.getDescription(),
                shoe.getPrice(),
                shoe.getSku(),
                shoe.isActive(),
                shoe.getImage(),
                shoe.getShoeID()
        );
    }

    //Delete Shoe by ID
    public int delete (int shoeID) {
        return jdbcTemplate.update(
                "DELETE FROM shoes WHERE shoeid = ?", shoeID );
    }
}
