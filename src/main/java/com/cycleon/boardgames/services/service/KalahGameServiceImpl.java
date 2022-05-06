package com.cycleon.boardgames.services.service;

import com.cycleon.boardgames.services.models.KalahGame;
import com.cycleon.boardgames.services.strategies.KalahGameStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalahGameServiceImpl implements KalahGameService {

    static List<KalahGame> games;

    @Autowired
    KalahGameStrategyFactory kalahGameStrategyFactory;

    public KalahGameServiceImpl () {
        // default values for now
        games = new ArrayList<>();
        KalahGame standard = new KalahGame();
        standard.setType(KalahGame.KalahType.STANDARD);
        games.add(standard);
    }

    @Override
    public KalahGame initializeKalahGame(int id, int numberOfHouses, int numberOfSeeds) {
        games.set(id, kalahGameStrategyFactory.getKalahGameStrategyByType(games.get(id).getType()).initializeKalahGame(games.get(id),numberOfHouses,numberOfSeeds));
        return getKalahGame(id);
    }

    public KalahGame sowAndSimulate(int id, int index) {
        games.set(id, kalahGameStrategyFactory.getKalahGameStrategyByType(games.get(id).getType()).sowAndSimulate(index, games.get(id)));
        return getKalahGame(id);
    }

    @Override
    public KalahGame getKalahGame(int id) {
        return games.get(id);
    }

}
