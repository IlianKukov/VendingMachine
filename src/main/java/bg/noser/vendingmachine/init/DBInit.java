package bg.noser.vendingmachine.init;

import bg.noser.vendingmachine.model.entity.CoinsEintity;
import bg.noser.vendingmachine.model.entity.ManufacturerEntity;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.CoinsRepository;
import bg.noser.vendingmachine.repository.ManufacturerRepository;
import bg.noser.vendingmachine.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;
    private final CoinsRepository coinsRepository;


    DBInit(ManufacturerRepository authorRepository,
           ProductRepository bookRepository, CoinsRepository coinsRepository) {

        this.manufacturerRepository = authorRepository;
        this.productRepository = bookRepository;
        this.coinsRepository = coinsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (productRepository.count() == 0 && manufacturerRepository.count() == 0) {
            initWaffle();
            initCola();
            initChocolate();
            initCoffie();
        }

    }
    public void initCoins(){
        initCoins("10st",0.1);
        initCoins("20st",0.2);
        initCoins("50st",0.5);
        initCoins("1lv",1.0);
        initCoins("2lv",2.0);

    }

    private void initCola() {
        initManufacturer("CokaCola",
                10.0,
                "Cola Light"

        );
    }

    private void initChocolate() {
        initManufacturer("Nestle",
                5.0,
                "KitKat"
        );
    }

    private void initCoffie() {
        initManufacturer("Costa",
                15.0,
                "Signature Brew"
        );
    }

    private void initWaffle() {

        initManufacturer("Chipita",
                6.0,
                "Black waffle");
    }

    private void initManufacturer(String manufacturerName, Double price, String productName) {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName(manufacturerName);
        manufacturer = manufacturerRepository.save(manufacturer);

        List<ProductEntity> allProducts = new ArrayList<>();

            ProductEntity aProduct = new ProductEntity();
            aProduct.setManufacturer(manufacturer);
            aProduct.setName(productName);
            aProduct.setPrice(price);
            aProduct.setType(UUID.randomUUID().toString());//random string, not real, dummy
            allProducts.add(aProduct);


        manufacturer.setProducts(allProducts);
        manufacturerRepository.save(manufacturer);

        productRepository.saveAll(allProducts);
    }

    private void initCoins(String coinName, Double coinValue) {
        CoinsEintity coin = new CoinsEintity();
        coin.setName(coinName);
        coin.setValue(coinValue);

        coinsRepository.save(coin);
    }
}
