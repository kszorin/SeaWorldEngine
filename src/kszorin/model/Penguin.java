package kszorin.model;

public class Penguin extends Animal {
    public static final int REPRODUCTION_PERIOD_PENGUIN = 3;

    public Penguin(int id, int posX, int posY, int environs) {
        super(id, posX, posY, environs);
        this.species = SealCreatureSpecies.Penguin;
        reproductionPeriod = REPRODUCTION_PERIOD_PENGUIN;
        eatingBehaviour = new NotEating();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new OneSquareMoving();
    }
}
