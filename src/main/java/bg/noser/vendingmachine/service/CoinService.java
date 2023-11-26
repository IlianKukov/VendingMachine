package bg.noser.vendingmachine.service;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.dto.ProductDTO;

import java.util.List;

public interface CoinService {
    List<CoinDTO> getAllCoins();
    void resetCoins();
    Double insertCoin(Double coin);

}
