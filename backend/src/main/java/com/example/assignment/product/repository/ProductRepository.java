package com.example.assignment.product.repository;

import com.example.assignment.product.model.Product;
import com.example.assignment.product.model.ProductCreateRequest;
import com.example.assignment.product.model.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@CrossOrigin
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Product> getProducts() {
        String sql = "SELECT id, name, description, price, stock, thumbnail " +
                "FROM product ";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setStock(rs.getInt("stock"));
            product.setThumbnail(rs.getString("thumbnail"));
            return product;
        });
    }

    public void createProduct(ProductCreateRequest request, String userId) {
        jdbcTemplate.update(
                "INSERT INTO product (id, name, description, price, stock, created_by, created) VALUES (UUID(), ?, ?, ?, ?, ?, ?)",
                request.getName(), request.getDescription(), request.getPrice(), request.getStock(), userId, Instant.now()
        );
    }

    public void updateProduct(ProductUpdateRequest request, String userId) {
        jdbcTemplate.update(
                "UPDATE product SET name = ?, description = ?, price = ?, stock = ?, updated_by = ?, updated = ? WHERE id = ?",
                request.getName(), request.getDescription(), request.getPrice(), request.getStock(),
                userId, Instant.now(), request.getId());
    }

    public void deleteProduct(String productId) {
        jdbcTemplate.update(
                "DELETE FROM product WHERE id = ?", productId);
    }

    public void updateProductBase64(String productId, String thumbnail, String userId) {
        jdbcTemplate.update(
                "UPDATE product SET thumbnail = ?, updated_by = ?, updated = ? WHERE id = ?",
                thumbnail, userId, Instant.now(), productId);
    }

}