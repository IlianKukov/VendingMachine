package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.service.CoinService;
import bg.noser.vendingmachine.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation( summary = "Buy a particular product by id. Make sure to insert enough coins first!")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Buying product is successful!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Buying product is unsuccessful. Product not found!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "426",
                            description = "Buying product is unsuccessful. Not enough coins!",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> buyProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);
        Double sumCoins = 0.0;
        Double productPrice = 0.0;


        if (productDTOOptional.isEmpty()){
            // Product not found!
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
            //not enough coins
            return ResponseEntity.status(426).build();
        }

    }
}
