package kszorin.model;

import java.util.HashMap;
import java.util.Map;

public class PlayingWorld {
    private final byte fieldSizeX;
    private byte fieldSizeY;
    private final int orcasQuantity;
    private final int penguinsQuantity;
    private byte environs;
    private int waterSpace[][];
    private Map<Integer, SeaCreature> seaCreaturesMap;

    //TODO: сделать порверку вводимых значений
    public PlayingWorld(byte fieldSizeX, byte fieldSizeY, byte orcasPercentFilling, byte penguinsPercentFilling, byte environs) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        orcasQuantity = fieldSizeX * fieldSizeY * orcasPercentFilling / 100;
        penguinsQuantity = fieldSizeX * fieldSizeY * penguinsPercentFilling / 100;
        this.environs = environs;
        waterSpace = new int[fieldSizeY][fieldSizeX];
        seaCreaturesMap = new HashMap<Integer, SeaCreature>();
        reset();
    }

    public void reset () {
//        Создаём чистое водное пространство.
        for (int i = 0, j = 0; i < fieldSizeY; i++)
            for (j=0; j < fieldSizeX; j++)
                waterSpace[i][j] = -1;
//        Обнуляем счётчик и массив морских созданий.
        seaCreaturesMap.clear();

        System.out.println("Размер хранилища существ:" + seaCreaturesMap.size());

//        Создаём и расставляем касаток на поле
        int possiblePos=0, possiblePosX=0, possiblePosY=0, seaCreatureIdcounter = 0;;
        for (int i=0; i < orcasQuantity; i++) {
            do {
                possiblePos = (int) (Math.random() * (fieldSizeY * fieldSizeX));
                possiblePosY = possiblePos / fieldSizeX;
                possiblePosX = possiblePos % fieldSizeX;
                if (waterSpace[possiblePosY][possiblePosX] == -1 ) {
                    waterSpace[possiblePosY][possiblePosX] = seaCreatureIdcounter;
                    seaCreaturesMap.put(seaCreatureIdcounter, new Orca(seaCreatureIdcounter, possiblePosX, possiblePosY, environs));
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
                    seaCreaturesMap.put(seaCreatureIdcounter, new Penguin(seaCreatureIdcounter, possiblePosX, possiblePosY));
                    seaCreatureIdcounter++;
                    System.out.printf("В позицию [%d,%d] ДОБАВЛЕН пингвин с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
                    break;
                }
                else
                    System.out.printf("Позиция [%d,%d] УЖЕ ЗАНЯТА существом с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
            } while(true);
        }
    }

    public void nextLifeStep() {
        for (int i = 0, j = 0; i < fieldSizeY; i++)
            for (j=0; j < fieldSizeX; j++) {
                if (waterSpace[i][j] != -1) {
                    seaCreaturesMap.get(waterSpace[i][j]).lifeStep(this);
                }
            }

    }

    public Map<Integer, SeaCreature> getSeaCreaturesMap() {
        return seaCreaturesMap;
    }

    public int[][] getWaterSpace() {
        return waterSpace;
    }

    public byte getFieldSizeX() {
        return fieldSizeX;
    }

    public byte getFieldSizeY() {
        return fieldSizeY;
    }
}
