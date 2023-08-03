package com.example.assignment.product.service;

import com.example.assignment.product.model.Product;
import com.example.assignment.product.model.ProductCreateRequest;
import com.example.assignment.product.model.ProductDeleteRequest;
import com.example.assignment.product.model.ProductUpdateRequest;
import com.example.assignment.product.repository.ProductRepository;
import com.example.assignment.util.FileConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@CrossOrigin
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.getProducts();
    }

    public void createNewProduct(ProductCreateRequest request, String id) {
        productRepository.createProduct(request, id);
    }

    public void updateProduct(ProductUpdateRequest request, String id) {
        productRepository.updateProduct(request, id);
    }

    public void deleteProduct(ProductDeleteRequest request) {
        productRepository.deleteProduct(request.getId());
    }

    public void uploadProductImage(String productId, MultipartFile file, String userId) throws IOException {
        String base64 = FileConverterUtil.generateThumbnailBase64EncodedString(file.getInputStream());
        productRepository.updateProductBase64(productId, base64, userId);
    }

}
