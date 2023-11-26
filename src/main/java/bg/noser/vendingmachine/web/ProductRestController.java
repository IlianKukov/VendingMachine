package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.ProductRepository;
import bg.noser.vendingmachine.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductRestController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> countOfProducts = productService.getAllProducts();
        if (!countOfProducts.isEmpty()){
            return ResponseEntity.ok(productService.getAllProducts());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);
        return productDTOOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable("id") Long id) {
        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);

        if (productDTOOptional.isPresent()){
            productService.deleteProductById(id);
            return ResponseEntity.ok()
                    .build();
        }else {
            productService.deleteProductById(id);
            return ResponseEntity.status(404)
                    .build();
        }

    }

    @DeleteMapping("/deleteallproducts")
    public ResponseEntity<ProductDTO> deleteAllProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, UriComponentsBuilder uriComponentsBuilder) {
        int thisProductCount = productRepository
                .findByType(productDTO.
                        getType()).size();

        String name = productDTO.getName();
        Double price = productDTO.getPrice();
        String type = productDTO.getType();

        if (price==null || price<=0 || name==null || type==null){
            return ResponseEntity.status(422)
                    .build();
        }

        if (thisProductCount <= 9) {

            long newProductID = productService.createProduct(productDTO);

            return ResponseEntity.created(
                    uriComponentsBuilder.path("/api/products/{id}")
                            .build(newProductID)).build();
        }

        return ResponseEntity.status(422)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {

        Optional<ProductEntity> updateProduct = productRepository.findById(id);

        int thisProductCount = productRepository
                .findByType(productDTO.
                        getType()).size();

        if (updateProduct.isPresent() && thisProductCount<9){
            updateProduct.get().setType(productDTO.getType());
            updateProduct.get().setName(productDTO.getName());
            updateProduct.get().setPrice(productDTO.getPrice());

            ProductEntity updated = updateProduct.get();

            productRepository.save(updated);
            return ResponseEntity.ok(updated);
        }
        else {
            return ResponseEntity.status(406).build();
        }




    }


}
