package kszorin.model;

public class Orca extends Animal {
    public Orca(int id, int posX, int posY) {
        super(id, posX, posY);
        this.species = SealCreatureSpecies.Orca;
        reproductionPeriod = REPRODUCTION_PERIOD_ORCA;
        eatingBehaviour = new Hunting();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new OneSquareMoving();
    }
}