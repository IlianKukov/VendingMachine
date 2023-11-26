package bg.noser.vendingmachine.init;

import bg.noser.vendingmachine.model.entity.CoinEntity;
import bg.noser.vendingmachine.model.entity.ProductEntity;
import bg.noser.vendingmachine.repository.CoinsRepository;
import bg.noser.vendingmachine.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CoinsRepository coinsRepository;


    DBInit(ProductRepository bookRepository, CoinsRepository coinsRepository) {
        this.productRepository = bookRepository;
        this.coinsRepository = coinsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//     //    Uncomment for db Seeding if required
//
//        if (productRepository.count() == 0) {
//            initWaffle();
//            initCola();
//            initChocolate();
//            initCoffie();
//            initCoins();
//        }

    }
    public void initCoins(){
        initCoins(0.1);
        initCoins(0.2);
        initCoins(0.5);
        initCoins(1.0);
        initCoins(2.0);

    }

    private void initCola() {
        initProduct(10.0,
                "Cola Light",
                "Cola"
        );
    }

    private void initChocolate() {
        initProduct(5.0,
                "KitKat",
                "Nestle"
        );
    }

    private void initCoffie() {
        initProduct(15.0,
                "Costa Signature Brew",
                "Coffie"
        );
    }

    private void initWaffle() {

        initProduct(6.0,
                "Black waffle",
        "Chocolate");
    }

    private void initProduct(Double price, String productName, String type) {


        List<ProductEntity> allProducts = new ArrayList<>();

            ProductEntity aProduct = new ProductEntity();
            aProduct.setName(productName);
            aProduct.setPrice(price);
            aProduct.setType(type);
            allProducts.add(aProduct);

        productRepository.saveAll(allProducts);
    }

    private void initCoins(Double coinValue) {
        CoinEntity coin = new CoinEntity();
        coin.setValue(coinValue);

        coinsRepository.save(coin);
    }
}
