package bg.noser.vendingmachine.service;

import bg.noser.vendingmachine.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> findAllProductsById(Long id);
}
