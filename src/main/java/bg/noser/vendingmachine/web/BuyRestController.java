package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.service.CoinService;
import bg.noser.vendingmachine.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buy")
public class BuyRestController {

    private final ProductService productService;
    private final CoinService coinService;

    public BuyRestController(ProductService productService, CoinService coinService) {
        this.productService = productService;
        this.coinService = coinService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);
        Double sumCoins = 0.0;
        Double productPrice = 0.0;


        if (productDTOOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            List<CoinDTO> allCoins = coinService.getAllCoins();
            for (CoinDTO coin: allCoins){
                sumCoins+=coin.getValue();
            }
            productPrice = productService.findProductById(id).get().getPrice();
        }

        if (sumCoins>=productPrice){
            // buy product if enough coins are available
            productService.deleteProductById(id);
            coinService.resetCoins();
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(426).build();
        }

    }
}
