package kszorin.view;

import kszorin.model.*;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static final byte FIELD_SIZE_X = 10;
    public static final byte FIELD_SIZE_Y = 15;
    public static final byte ORCAS_PERCENT_FILLING = 5;
    public static final byte PENGUINS_PERCENT_FILLING = 20;

    public static void displayWorld(PlayingWorld playingWorld) {
        int[][] waterSpace = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();

        System.out.println();
        for (int i=0, j; i < playingWorld.getFieldSizeY(); i++) {
            for (j = 0; j < playingWorld.getFieldSizeX(); j++) {
                if (waterSpace[i][j] == -1)
                    System.out.print("   .  ");
                else
                    System.out.printf(" %c%3d ", seaCreaturesMap.get(waterSpace[i][j]).getSpecies().toString().charAt(0), waterSpace[i][j]);
            }
            System.out.println();
        }
        System.out.printf("Количество существ %d\n", playingWorld.getSeaCreaturesMap().size());
    }

    public static void main(String[] args) {
        try {
        PlayingWorld playingWorld = new PlayingWorld(FIELD_SIZE_X, FIELD_SIZE_Y, ORCAS_PERCENT_FILLING, PENGUINS_PERCENT_FILLING);
        displayWorld(playingWorld);
            int c=0;
            do {
                if (c==32) {
                    playingWorld.nextLifeStep();
                    displayWorld(playingWorld);
                }
                c = System.in.read();
            }while (c != 'q');
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
