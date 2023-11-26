package bg.noser.vendingmachine.service;

import bg.noser.vendingmachine.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> findProductById(Long id);
    void deleteProductById(Long id);
    void deleteAllProducts();
    Long createProduct(ProductDTO productDTO);
//    ProductEntity findSpecificProduct(Long id);
}
