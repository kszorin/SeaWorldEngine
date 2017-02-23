package kszorin.model;

public class Penguin extends Animal {
    public static final int REPRODUCTION_PERIOD_PENGUIN = 3;

    public Penguin(int id, Position pos, byte environs) {
        super(id, pos, environs);
        this.species = SealCreatureSpecies.Penguin;
        reproductionPeriod = REPRODUCTION_PERIOD_PENGUIN;
        eatingBehaviour = new NotEating();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new InEnvironsMoving();
    }
}
