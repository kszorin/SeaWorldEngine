package kszorin.model;

import kszorin.model.behaviour.Hunting;
import kszorin.model.behaviour.InEnvironsMoving;
import kszorin.model.behaviour.PeriodicReproduction;

import java.util.ArrayList;
import java.util.List;

public class Orca extends Animal {
    private static final byte ORCA_REPRODUCTION_PERIOD = 8;
    private static final byte ORCA_ENVIRONS = 1;
    private static final byte ORCA_HUNGER_DEATH_PERIOD = 3;
    private List<SealCreatureSpecies> targetList = new ArrayList<SealCreatureSpecies>();

    public Orca(int id, Position pos) {
        super(id, pos);
        this.species = SealCreatureSpecies.Orca;
        this.reproductionPeriod = ORCA_REPRODUCTION_PERIOD;
        this.environs = ORCA_ENVIRONS;
        eatingBehaviour = new Hunting();
        reproductionBehaviour = new PeriodicReproduction();
        movingBehaviour = new InEnvironsMoving();
        targetList.add(SealCreatureSpecies.Penguin);
    }

    @Override
    public void lifeStep(PlayingWorld playingWorld) {
        if (eatingBehaviour.eat(this, playingWorld, findInEnvirons (playingWorld, targetList))) {
            timeFromEating = 0;
        }
        else
            movingBehaviour.move(this, playingWorld, findInEnvirons (playingWorld));
        if (timeFromEating >= ORCA_HUNGER_DEATH_PERIOD) {
            //гибель
        }
        else {
            age++;
            if ((age!=0) && (age%ORCA_REPRODUCTION_PERIOD == 0)) {
//              TODO: размножение
//              reproductionBehaviour.reproduct(playingWorld);
            }
        }
    }
}