package bg.noser.vendingmachine.web;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;
import bg.noser.vendingmachine.service.CoinService;
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
@RequestMapping("/api/coins")
public class CoinRestController {

    private final CoinService coinService;
    public CoinRestController(CoinService coinService) {
        this.coinService = coinService;
    }

    @Operation( summary = "Displays all coins in the Vending Machine")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays all Coins found in the vending machine",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CoinDTO>> getAllCoins(){
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @Operation( summary = "Returns all coins currently in the Vending Machine")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays the list of returned coins",
                            content = @Content
                    )
            }
    )
    @GetMapping("/returncoins")
    public ResponseEntity<List<CoinDTO>> returnCoins() {
        List<CoinDTO> returnCoinsList = coinService.getAllCoins();
        coinService.resetCoins();
        return ResponseEntity.ok(returnCoinsList);
    }

    @Operation( summary = "Insert Coin in the Vending Machine. Accepted are only 0.1 (10st), 0.2 (20st), 0.5 (50st), 1 (1lv), 2 (2lv)")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "201",
                            description = "Displays status 201 if the inserted coin is the correct type",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Displays status 400 if the inserted coin is not correctly specified",
                            content = @Content
                    )
            }
    )
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
