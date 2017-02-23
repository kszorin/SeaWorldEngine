package kszorin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InEnvironsMoving implements MovingBehaviour {
    @Override
    public void move(Animal animal, PlayingWorld playingWorld) {
        int waterSpace[][] = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();
        int beginRangeBypassX=0, endRangeBypassX=0, beginRangeBypassY=0, endRangeBypassY=0;
        Position pos = animal.getPos();

//        Определяем границу окрестности по X.
        if ((beginRangeBypassX = pos.getX() - animal.getEnvirons()) < 0)
            beginRangeBypassX = 0;
        if ((endRangeBypassX = pos.getX() + animal.getEnvirons()) > (playingWorld.getFieldSizeX()-1))
            endRangeBypassX = playingWorld.getFieldSizeX()-1;
//        Определяем границу окрестности по Y.
        if ((beginRangeBypassY = pos.getY() - animal.getEnvirons()) < 0)
            beginRangeBypassY = 0;
        if ((endRangeBypassY = pos.getY() + animal.getEnvirons()) > (playingWorld.getFieldSizeY()-1))
            endRangeBypassY = playingWorld.getFieldSizeY()-1;

//        Определение свободных мест в окрестности и заполнение буфера свободными позициями.
        List<Position> freePlacePosBuffer = new ArrayList<Position>();
        for (int i = beginRangeBypassY, j = beginRangeBypassX; i <= endRangeBypassY; i++)
            for (j = beginRangeBypassX; j <= endRangeBypassX; j++) {
                if ((i == pos.getY()) && (j == pos.getX()))
                    continue;
                else {
                    if (waterSpace[i][j] == -1)
                        freePlacePosBuffer.add(new Position(j,i));
                }
            }
        System.out.printf("Количество свободных клеток - %d ", freePlacePosBuffer.size());
        if (freePlacePosBuffer.size() > 0) {
//        Случайным образом выбираем позицию из буфера свободных мест.
            int bufferRandomNum = (int) (Math.random() * (freePlacePosBuffer.size()));
            Position selectedFreePos = freePlacePosBuffer.get(bufferRandomNum);
//        Перемещаем животину в новую позицию.
            waterSpace[selectedFreePos.getY()][selectedFreePos.getX()] = waterSpace[pos.getY()][pos.getX()];
            waterSpace[pos.getY()][pos.getX()] = -1;
            seaCreaturesMap.get(animal.getId()).setPos(selectedFreePos);
            System.out.printf("%c (id=%d): [%d,%d] -> [%d,%d]\n", animal.species.toString().charAt(0), animal.getId(), pos.getX(), pos.getY(),
                    animal.getPos().getX(), animal.getPos().getY());
        }
    }
}
