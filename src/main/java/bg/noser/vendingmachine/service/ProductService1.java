package bg.noser.vendingmachine.service;

public interface ProductService1 {
//
//    private final ProductRepository productRepository;
//    private ManufacturerRepository manufacturerRepository;
//
//    public ProductService1(ProductRepository productRepository,
//                           ManufacturerRepository manufacturerRepository) {
//        this.productRepository = productRepository;
//        this.manufacturerRepository = manufacturerRepository;
//    }
//
//
//    public long createProduct(ProductDTO newProduct) {
//        String manufacturerName = newProduct.getManufacturer().getName();
//        Optional<ManufacturerEntity> authorOpt = this.manufacturerRepository.findManufacturerEntityByName(manufacturerName);
//
//        ProductEntity newProductEntity = new ProductEntity();
//        newProductEntity.setName(newProduct.getName());
//        newProductEntity.setType(newProduct.getType());
//        newProductEntity.setManufacturer(authorOpt.orElseGet(() -> createNewAuthor(manufacturerName)));
//
//        return productRepository.save(newProductEntity).getId();
//    }
//
//    private ManufacturerEntity createNewAuthor(String authorName) {
//        return manufacturerRepository.save(new ManufacturerEntity().setName(authorName));
//    }
//
//    public void deleteById(Long bookId) {
//        productRepository.
//                findById(bookId).
//                ifPresent(productRepository::delete);
//    }
//
//    public Optional<ProductDTO> findBookById(Long productId) {
//        return productRepository.
//                findById(productId).
//                map(this::map);
//    }
//
//    public List<ProductDTO> getAllProducts() {
//        return productRepository.findAll().
//                stream().
//                map(this::map).
//                toList();
//    }
//
//    private ProductDTO map(ProductEntity productEntity) {
//
//        ManufacturerDTO manufacturerDTO = new ManufacturerDTO().
//                setName(productEntity.getManufacturer().getName());
//
//        return new ProductDTO().
//                setId(productEntity.getId()).
//                setManufacturer(manufacturerDTO).
//                setType(productEntity.getType()).
//                setName(productEntity.getName());
//    }
}
