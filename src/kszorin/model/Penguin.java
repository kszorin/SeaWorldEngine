package kszorin.model;


public class Penguin extends Animal {
    public Penguin(int id, int posX, int posY) {
        super(id, posX, posY);
        this.species = SealCreatureSpecies.Penguin;
        reproductionPeriod = REPRODUCTION_PERIOD_PENGUIN;
        eatingBehaviour = new NotEating();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new OneSquareMoving();
    }
}
