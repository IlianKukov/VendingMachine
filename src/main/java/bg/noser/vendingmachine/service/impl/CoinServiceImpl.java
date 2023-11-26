package bg.noser.vendingmachine.service.impl;

import bg.noser.vendingmachine.model.dto.CoinDTO;
import bg.noser.vendingmachine.model.entity.CoinEntity;
import bg.noser.vendingmachine.repository.CoinsRepository;
import bg.noser.vendingmachine.service.CoinService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoinServiceImpl implements CoinService {

    private final CoinsRepository coinsRepository;

    public CoinServiceImpl(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    @Override
    public List<CoinDTO> getAllCoins() {
        return coinsRepository.findAll()
                .stream()
                .map(CoinServiceImpl::mapCoinToDTO)
                .toList();
    }

    @Override
    public void resetCoins() {
        this.coinsRepository.deleteAll();
    }

    @Override
    public Double insertCoin(Double coin) {

        CoinEntity newCoin = new CoinEntity()
                .setValue(coin);
        newCoin = coinsRepository.save(newCoin);

        return newCoin.getValue();
    }

    private static CoinDTO mapCoinToDTO(CoinEntity coinEntity){
        return new CoinDTO()
                .setId(coinEntity.getId())
                .setValue(coinEntity.getValue());
    }
}
