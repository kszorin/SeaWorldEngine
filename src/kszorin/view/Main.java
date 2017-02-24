package kszorin.view;

import kszorin.model.*;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static final byte FIELD_SIZE_X = 4;
    public static final byte FIELD_SIZE_Y = 5;
    public static final byte ORCAS_PERCENT_FILLING = 5;
    public static final byte PENGUINS_PERCENT_FILLING = 20;

    public static void displayWorld(PlayingWorld world) {
        int[][] waterSpace = world.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = world.getSeaCreaturesMap();

        System.out.println();
        for (int i=0, j; i < world.getFieldSizeY(); i++) {
            for (j = 0; j < world.getFieldSizeX(); j++) {
                if (waterSpace[i][j] == -1)
                    System.out.print("  .  ");
                else
                    System.out.printf(" %c%2d ", seaCreaturesMap.get(waterSpace[i][j]).getSpecies().toString().charAt(0), waterSpace[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
	    PlayingWorld playingWorld = new PlayingWorld(FIELD_SIZE_X, FIELD_SIZE_Y, ORCAS_PERCENT_FILLING, PENGUINS_PERCENT_FILLING);
        displayWorld(playingWorld);
        try {
            int c=0;
            do {
                if (c==32) {
                    playingWorld.nextLifeStep();
                    displayWorld(playingWorld);
                }
                c = System.in.read();
            }while (c != 'q');

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
