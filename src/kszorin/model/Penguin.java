package kszorin.model;

public class Penguin extends Animal {
    public static final int PENGUIN_REPRODUCTION_PERIOD = 3;
    public static final byte PENGUIN_ENVIRONS = 1;

    public Penguin(int id, Position pos) {
        super(id, pos);
        this.species = SealCreatureSpecies.Penguin;
        this.environs = PENGUIN_ENVIRONS;
        reproductionPeriod = PENGUIN_REPRODUCTION_PERIOD;
        eatingBehaviour = new NotEating();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new InEnvironsMoving();
    }
}
