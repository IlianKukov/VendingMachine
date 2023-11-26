package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coins")
public class CoinRestController {

    private final CoinService coinService;
    public CoinRestController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public ResponseEntity<List<CoinDTO>> getAllCoins(){
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @GetMapping("/returncoins")
    public ResponseEntity<List<CoinDTO>> returnCoins() {
        coinService.resetCoins();
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @PostMapping("/{id}")
    public ResponseEntity<CoinDTO> insertCoin(@PathVariable("id") Double value, UriComponentsBuilder uriComponentsBuilder) {

        if (value==0.1 || value==0.2 || value==0.5 || value==1 || value==2){
            Double newCoinID = coinService.insertCoin(value);
            return ResponseEntity.created(
                    uriComponentsBuilder.path("/api/coins/")
                            .build(newCoinID)).build();
        }

        return ResponseEntity.badRequest().build();
    }

}
