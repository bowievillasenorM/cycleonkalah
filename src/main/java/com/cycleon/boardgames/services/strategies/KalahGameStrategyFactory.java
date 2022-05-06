package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * this is to accomodate different variations if there are any in the future
 */
@Component
public class KalahGameStrategyFactory {
    private final Map<KalahGame.KalahType, KalahGameStrategy> registry;

    public KalahGameStrategyFactory() {
        registry = new HashMap<>();
        registry.put(KalahGame.KalahType.STANDARD, new TraditionalStrategy());
    }

    public KalahGameStrategy getKalahGameStrategyByType(KalahGame.KalahType kalahType) {
        return registry.get(kalahType);
    }
}
