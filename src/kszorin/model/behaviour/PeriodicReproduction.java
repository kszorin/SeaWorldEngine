package kszorin.model.behaviour;

import kszorin.model.Animal;
import kszorin.model.PlayingWorld;
import kszorin.model.Position;
import kszorin.model.SeaCreature;

import java.util.List;
import java.util.Map;

public class PeriodicReproduction implements ReproductionBehaviour {
    @Override
    public void reproduct(Animal animal, PlayingWorld playingWorld, List<Position> foundPositionsInEnvirons) {
        int waterSpace[][] = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();
        Position pos = animal.getPos();

        if (foundPositionsInEnvirons.size() > 0) {
//        Случайным образом выбираем позицию из буфера свободных мест.
            int bufferRandomNum = (int) (Math.random() * (foundPositionsInEnvirons.size()));
            Position selectedFreePos = foundPositionsInEnvirons.get(bufferRandomNum);
//        Создаём детёныша на свободном месте.
            Animal baby = animal.getBaby(playingWorld.getSeaCreatureIdCounter(), selectedFreePos);
            seaCreaturesMap.put(playingWorld.getSeaCreatureIdCounter(), baby);
            waterSpace[selectedFreePos.getY()][selectedFreePos.getX()] = playingWorld.getSeaCreatureIdCounter();
            playingWorld.setSeaCreatureIdCounter(playingWorld.getSeaCreatureIdCounter() + 1);

            System.out.printf("%c(id=%d) [%d,%d]: produce %c (id=%d) [%d,%d]\n", animal.getSpecies().toString().charAt(0), animal.getId(),
                    animal.getPos().getX(), animal.getPos().getY(), baby.getSpecies().toString().charAt(0), baby.getId(),
                    baby.getPos().getX(), baby.getPos().getY());
        }
    }
}
