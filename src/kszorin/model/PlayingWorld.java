package kszorin.model;

import java.util.HashMap;
import java.util.Map;

public class PlayingWorld {
    private final byte fieldSizeX;
    private byte fieldSizeY;
    private byte orcasPercentFilling;
    private byte penguinsPercentFilling;
    private final int orcasQuantity;
    private final int penguinsQuantity;
    private int waterSpace[][];
    private Map<Integer, SeaCreature> seaCreatureMap;

    private int seaCreatureIdcounter = 0;

    public PlayingWorld(byte fieldSizeX, byte fieldSizeY, byte orcasPercentFilling, byte penguinsPercentFilling) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.orcasPercentFilling = orcasPercentFilling;
        this.penguinsPercentFilling = penguinsPercentFilling;
        orcasQuantity = fieldSizeX * fieldSizeY * orcasPercentFilling / 100;
        penguinsQuantity = fieldSizeX * fieldSizeY * penguinsPercentFilling / 100;
        waterSpace = new int[fieldSizeY][fieldSizeX];
        seaCreatureMap = new HashMap<Integer, SeaCreature>();
        reset();
    }

    public void reset () {
//        Создаём чистое водное пространство.
        for (int i = 0, j = 0; i < fieldSizeY; i++)
            for (j=0; j < fieldSizeX; j++)
                waterSpace[i][j] = -1;
//        Обнуляем счётчик морских созданий и массив.
        seaCreatureMap.clear();
        seaCreatureIdcounter = 0;
        System.out.println("Размер хранилища существ:" + seaCreatureMap.size());

//        Создаём и расставляем касаток на поле
        int possiblePos=0, possiblePosX=0, possiblePosY=0;
        for (int i=0; i < orcasQuantity; i++) {
            do {
                possiblePos = (int) (Math.random() * (fieldSizeY * fieldSizeX));
                possiblePosY = possiblePos / fieldSizeX;
                possiblePosX = possiblePos % fieldSizeX;
                if (waterSpace[possiblePosY][possiblePosX] == -1 ) {
                    waterSpace[possiblePosY][possiblePosX] = seaCreatureIdcounter;
                    seaCreatureMap.put(seaCreatureIdcounter, new Orca(seaCreatureIdcounter, possiblePosX, possiblePosY));
                    seaCreatureIdcounter++;
                    System.out.printf("В позицию [%d,%d] ДОБАВЛЕНА касатка с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
                    break;
                }
                else
                    System.out.printf("Позиция [%d,%d] УЖЕ ЗАНЯТА существом с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
            } while(true);
        }

        //        Создаём и расставляем пингвинов на поле
        for (int i=0; i < penguinsQuantity; i++) {
            do {
                possiblePos = (int) (Math.random() * (fieldSizeY * fieldSizeX));
                possiblePosY = possiblePos / fieldSizeX;
                possiblePosX = possiblePos % fieldSizeX;
                if (waterSpace[possiblePosY][possiblePosX] == -1 ) {
                    waterSpace[possiblePosY][possiblePosX] = seaCreatureIdcounter;
                    seaCreatureMap.put(seaCreatureIdcounter, new Penguin(seaCreatureIdcounter, possiblePosX, possiblePosY));
                    seaCreatureIdcounter++;
                    System.out.printf("В позицию [%d,%d] ДОБАВЛЕН пингвин с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
                    break;
                }
                else
                    System.out.printf("Позиция [%d,%d] УЖЕ ЗАНЯТА существом с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
            } while(true);
        }
    }
}
