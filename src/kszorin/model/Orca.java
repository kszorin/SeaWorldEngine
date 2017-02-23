package kszorin.model;

public class Orca extends Animal {
    public static final int REPRODUCTION_PERIOD_ORCA = 8;
    public static final int HUNGER_DEATH_PERIOD_ORCA = 3;

    public Orca(int id, Position pos, byte environs) {
        super(id, pos, environs);
        this.species = SealCreatureSpecies.Orca;
        reproductionPeriod = REPRODUCTION_PERIOD_ORCA;
        eatingBehaviour = new Hunting();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new InEnvironsMoving();
    }
}