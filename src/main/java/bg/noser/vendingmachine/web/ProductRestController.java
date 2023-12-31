package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.ProductRepository;
import bg.noser.vendingmachine.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation( summary = "Get All Products in the Vending Machine")
    @ApiResponses(
      value ={
      @ApiResponse(
              responseCode = "200",
              description = "Displays all products found in the vending machine",
              content = @Content
      ),
      @ApiResponse(
       responseCode = "404",
       description = "No products found in the vending machine",
              content = @Content
      )
      }
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> countOfProducts = productService.getAllProducts();
        if (!countOfProducts.isEmpty()){
            return ResponseEntity.ok(productService.getAllProducts());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation( summary = "Find Product by id")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays the product found in the vending machine",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The Product is not found in the vending machine",
                            content = @Content
                    )
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);
        return productDTOOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation( summary = "Delete Product by id")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays status code 200 if the product is successfully deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Displays status code 404 if the product is not found",
                            content = @Content
                    )
            }
    )
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
    @Operation( summary = "Purge all available products")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays status code 200 if all products are deleted successfully",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/deleteallproducts")
    public ResponseEntity<ProductDTO> deleteAllProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok()
                .build();
    }

    @Operation( summary = "Creates a new product using JSON format as in readme.md")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "201",
                            description = "Displays status code 200 if the products is successfully created",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Displays status code 400 if the format is bad",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "The count of products with the same type exceeds 10 as per requirements",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, UriComponentsBuilder uriComponentsBuilder) {
        int thisProductCount = productRepository
                .findByType(productDTO.
                        getType()).size();

        String name = productDTO.getName();
        Double price = productDTO.getPrice();
        String type = productDTO.getType();

        if (price==null || price<=0 || name==null || type==null){
            //if product is not correctly specified
            return ResponseEntity.status(400)
                    .build();
        }

        if (thisProductCount <= 9) {
            //if count of the same product type is larger than 10
            long newProductID = productService.createProduct(productDTO);

            return ResponseEntity.created(
                    uriComponentsBuilder.path("/api/products/{id}")
                            .build(newProductID)).build();
        }

        return ResponseEntity.status(422)
                .build();
    }
    @Operation( summary = "Updates a particular product by id with the specified JSON format")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays status code 200 if the product is updated successfully",
                            content = @Content

                    ),
                    @ApiResponse(
                            responseCode = "405",
                            description = "Displays status code 405 if the product id is wrong",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Displays status code 406 if the product count from same type exceeds 10",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Displays status code 400 if the JSON format is wrong",
                            content = @Content
                    )
            }
    )
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
        } else if (updateProduct.isPresent()) {
            //Product count over 10
            return ResponseEntity.status(406).build();
        } else {
            //wrong format or id
            return ResponseEntity.status(405).build();
        }




    }


}
