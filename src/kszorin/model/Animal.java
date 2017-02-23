package kszorin.model;


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

    @Override
    public void lifeStep(PlayingWorld playingWorld) {
//        eatingBehaviour.eat(playingWorld);
//        reproductionBehaviour.reproduct(playingWorld);
        movingBehaviour.move(this, playingWorld);
    }
}
