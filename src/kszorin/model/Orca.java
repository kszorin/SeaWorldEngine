package kszorin.model;

import kszorin.model.behaviour.Hunting;
import kszorin.model.behaviour.InEnvironsMoving;
import kszorin.model.behaviour.PeriodicReproduction;

public class Orca extends Animal {
    public static final byte ORCA_REPRODUCTION_PERIOD = 8;
    public static final byte ORCA_ENVIRONS = 1;
    public static final byte ORCA_HUNGER_DEATH_PERIOD = 3;

    public Orca(int id, Position pos) {
        super(id, pos);
        this.species = SealCreatureSpecies.Orca;
        this.reproductionPeriod = ORCA_REPRODUCTION_PERIOD;
        this.environs = ORCA_ENVIRONS;
        eatingBehaviour = new Hunting();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new InEnvironsMoving();
    }
}