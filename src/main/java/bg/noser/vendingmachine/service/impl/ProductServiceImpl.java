package bg.noser.vendingmachine.service.impl;

import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.ProductRepository;
import bg.noser.vendingmachine.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductServiceImpl::mapProductToDTO)
                .toList();
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return productRepository
                .findById(id)
                .map(ProductServiceImpl::mapProductToDTO);
    }

    @Override
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        this.productRepository.deleteAll();
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {

        ProductEntity newProduct = new ProductEntity()
                .setType(productDTO.getType())
                .setPrice(productDTO.getPrice())
                .setName(productDTO.getName());
        newProduct = productRepository.save(newProduct);

        return newProduct.getId();
    }


    private static ProductDTO mapProductToDTO(ProductEntity productEntity){

        return new ProductDTO()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setType(productEntity.getType())
                .setPrice(productEntity.getPrice());


    }
}
