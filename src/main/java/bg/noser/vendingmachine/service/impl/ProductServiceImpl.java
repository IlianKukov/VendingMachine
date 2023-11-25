package bg.noser.vendingmachine.service.impl;

import bg.noser.vendingmachine.model.dto.ManufacturerDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.ProductRepository;
import bg.noser.vendingmachine.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductServiceImpl::mapProductToDTO)
                .toList();
    }

    @Override
    public Optional<ProductDTO> findAllProductsById(Long id) {
        return productRepository
                .findById(id)
                .map(ProductServiceImpl::mapProductToDTO);
    }

    private static ProductDTO mapProductToDTO(ProductEntity productEntity){
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO().setName(productEntity.getManufacturer().getName());

        return new ProductDTO()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setType(productEntity.getType())
                .setPrice(productEntity.getPrice())
                .setManufacturer(manufacturerDTO);


    }
}
