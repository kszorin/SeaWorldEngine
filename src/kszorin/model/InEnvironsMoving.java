package kszorin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InEnvironsMoving implements MovingBehaviour {
    @Override
    public void move(Animal animal, PlayingWorld playingWorld, List<Position> findInEnvirons) {
        int waterSpace[][] = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();
        Position pos = animal.getPos();

        if (findInEnvirons.size() > 0) {
//        Случайным образом выбираем позицию из буфера свободных мест.
            int bufferRandomNum = (int) (Math.random() * (findInEnvirons.size()));
            Position selectedFreePos = findInEnvirons.get(bufferRandomNum);
//        Перемещаем животину в новую позицию.
            waterSpace[selectedFreePos.getY()][selectedFreePos.getX()] = waterSpace[pos.getY()][pos.getX()];
            waterSpace[pos.getY()][pos.getX()] = -1;
            seaCreaturesMap.get(animal.getId()).setPos(selectedFreePos);
            System.out.printf("%c (id=%d): [%d,%d] -> [%d,%d]\n", animal.species.toString().charAt(0), animal.getId(), pos.getX(), pos.getY(),
                    animal.getPos().getX(), animal.getPos().getY());
        }
    }
}
