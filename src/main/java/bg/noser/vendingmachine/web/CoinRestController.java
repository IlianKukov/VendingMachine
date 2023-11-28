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
    @Parameter(name = "Parameters" ,description = "No Parameters required", required = false)
    @GetMapping
    public ResponseEntity<List<CoinDTO>> getAllCoins(){
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @Operation( summary = "Returns all coins currently in the Vending Machine")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays nothing because all coins are returned to the buyer",
                            content = @Content
                    )
            }
    )
    @Parameter(name = "Parameters" ,description = "No Parameters required", required = false)
    @GetMapping("/returncoins")
    public ResponseEntity<List<CoinDTO>> returnCoins() {
        coinService.resetCoins();
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @Operation( summary = "Insert Coin in the Vending Machine")
    @ApiResponses(
            value ={
                    @ApiResponse(
                            responseCode = "200",
                            description = "Displays status 200 if the coin is the correct type",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Displays status 404 if the coins is not correctly specified",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductDTO.class))}
                    )
            }
    )
    @Parameter(name = "Parameters" ,description = "Accepted are only 0.1(10st), 0.2(20st), 0.5(50st), 1(1lv), 2(2lv)", required = false)
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
