package kszorin.model;

import java.util.*;

public class PlayingWorld {
    private final byte fieldSizeX;
    private final byte fieldSizeY;
    private final int orcasQuantity;
    private final int penguinsQuantity;
    private int waterSpace[][];
    private Map<Integer, SeaCreature> seaCreaturesMap;

    //TODO: сделать проверку вводимых значений
    //TODO: подумать над передачей существ и их количество в массиве
    public PlayingWorld(byte fieldSizeX, byte fieldSizeY, byte orcasPercentFilling, byte penguinsPercentFilling) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        orcasQuantity = fieldSizeX * fieldSizeY * orcasPercentFilling / 100;
        penguinsQuantity = fieldSizeX * fieldSizeY * penguinsPercentFilling / 100;
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
        int possiblePos=0, possiblePosX=0, possiblePosY=0, seaCreatureIdCounter = 0;;
        for (int i=0; i < orcasQuantity; i++) {
            do {
                possiblePos = (int) (Math.random() * (fieldSizeY * fieldSizeX));
                possiblePosY = possiblePos / fieldSizeX;
                possiblePosX = possiblePos % fieldSizeX;
                if (waterSpace[possiblePosY][possiblePosX] == -1 ) {
                    waterSpace[possiblePosY][possiblePosX] = seaCreatureIdCounter;
                    seaCreaturesMap.put(seaCreatureIdCounter, new Orca(seaCreatureIdCounter, new Position(possiblePosX, possiblePosY)));
                    seaCreatureIdCounter++;
                    System.out.printf("В позицию [%d,%d] ДОБАВЛЕНА orca с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
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
                    waterSpace[possiblePosY][possiblePosX] = seaCreatureIdCounter;
                    seaCreaturesMap.put(seaCreatureIdCounter, new Penguin(seaCreatureIdCounter, new Position(possiblePosX, possiblePosY)));
                    seaCreatureIdCounter++;
                    System.out.printf("В позицию [%d,%d] ДОБАВЛЕН penguin с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
                    break;
                }
                else
                    System.out.printf("Позиция [%d,%d] УЖЕ ЗАНЯТА существом с id=%d\n", possiblePosX, possiblePosY, waterSpace[possiblePosY][possiblePosX]);
            } while(true);
        }
    }

    public void nextLifeStep() {
        List<SeaCreature> seaCreatures = new ArrayList<SeaCreature>();
//        Набираем список существ в порядке обхода поля.
        for (int i = 0, j = 0; i < fieldSizeY; i++)
            for (j=0; j < fieldSizeX; j++) {
                if (waterSpace[i][j] != -1)
                    seaCreatures.add(seaCreaturesMap.get(waterSpace[i][j]));
            }
//        Запускаем очередной жизненный цикл.
        for (SeaCreature seaCreature: seaCreatures)
            seaCreature.lifeStep(this);
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
