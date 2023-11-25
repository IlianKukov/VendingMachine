package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products/")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTOOptional = productService.findAllProductsById(id);
        return productDTOOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
