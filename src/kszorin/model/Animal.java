package kszorin.model;


import java.util.*;

public abstract class Animal extends SeaCreature {
    protected int age;
    protected int reproductionPeriod;
    protected int timeFromEating=0;
    protected int timeFromReproduction=0;

    protected EatingBehaviour eatingBehaviour;
    protected ReproductionBehaviour reproductionBehaviour;
    protected MovingBehaviour movingBehaviour;

    public Animal(int id, Position pos, byte environs) {
        super(id, pos, environs);
        this.age = 0;
        this.timeFromEating = 0;
        this.timeFromReproduction = 0;
    }

    private List<Position> findInEnvirons (PlayingWorld playingWorld) {
        int waterSpace[][] = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();
        int beginRangeBypassX=0, endRangeBypassX=0, beginRangeBypassY=0, endRangeBypassY=0;

//        Определяем границу окрестности по X.
        if ((beginRangeBypassX = pos.getX() - environs) < 0)
            beginRangeBypassX = 0;
        if ((endRangeBypassX = pos.getX() + environs) > (playingWorld.getFieldSizeX()-1))
            endRangeBypassX = playingWorld.getFieldSizeX()-1;
//        Определяем границу окрестности по Y.
        if ((beginRangeBypassY = pos.getY() - environs) < 0)
            beginRangeBypassY = 0;
        if ((endRangeBypassY = pos.getY() + environs) > (playingWorld.getFieldSizeY()-1))
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
        return freePlacePosBuffer;
    }

    private List<Position> findInEnvirons (PlayingWorld playingWorld, List<SealCreatureSpecies> targets) {
        int waterSpace[][] = playingWorld.getWaterSpace();
        Map<Integer, SeaCreature> seaCreaturesMap = playingWorld.getSeaCreaturesMap();
        int beginRangeBypassX=0, endRangeBypassX=0, beginRangeBypassY=0, endRangeBypassY=0;

        if (targets.size() == 0)
            return Collections.emptyList();
        else {
//        Определяем границу окрестности по X.
            if ((beginRangeBypassX = pos.getX() - environs) < 0)
                beginRangeBypassX = 0;
            if ((endRangeBypassX = pos.getX() + environs) > (playingWorld.getFieldSizeX() - 1))
                endRangeBypassX = playingWorld.getFieldSizeX() - 1;
//        Определяем границу окрестности по Y.
            if ((beginRangeBypassY = pos.getY() - environs) < 0)
                beginRangeBypassY = 0;
            if ((endRangeBypassY = pos.getY() + environs) > (playingWorld.getFieldSizeY() - 1))
                endRangeBypassY = playingWorld.getFieldSizeY() - 1;

//        Определение свободных мест в окрестности и заполнение буфера свободными позициями.
            List<Position> freePlacePosBuffer = new ArrayList<Position>();
            for (int i = beginRangeBypassY, j = beginRangeBypassX; i <= endRangeBypassY; i++)
                for (j = beginRangeBypassX; j <= endRangeBypassX; j++) {
                    if ((i == pos.getY()) && (j == pos.getX()))
                        continue;
                    else {
                        if (targets.indexOf(seaCreaturesMap.get(waterSpace[i][j]).getSpecies()) != 0)
                            freePlacePosBuffer.add(new Position(j,i));
                    }
                }
            System.out.printf("Количество свободных клеток - %d ", freePlacePosBuffer.size());
            return freePlacePosBuffer;
        }
    }


    @Override
    public void lifeStep(PlayingWorld playingWorld) {
//        eatingBehaviour.eat(playingWorld);

        movingBehaviour.move(this, playingWorld, findInEnvirons (playingWorld));
//        reproductionBehaviour.reproduct(playingWorld);

    }
}
