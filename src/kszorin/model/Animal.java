package kszorin.model;


public abstract class Animal extends SeaCreature {
    protected  int age;
    protected int reproductionPeriod;
    protected int timeFromEating=0;
    protected int timeFromReproduction=0;

    protected EatingBehaviour eatingBehaviour;
    protected ReproductionBehaviour reproductionBehaviour;
    protected MovingBehaviour movingBehaviour;

    public static final int REPRODUCTION_PERIOD_ORCA = 8;
    public static final int REPRODUCTION_PERIOD_PENGUIN = 3;
    public static final int HUNGER_DEATH_PERIOD_ORCA = 3;

    public Animal(int id, int posX, int posY) {
        super(id, posX, posY);
        this.age = 0;
        this.timeFromEating = 0;
        this.timeFromReproduction = 0;
    }

    @Override
    public void lifeStep(PlayingWorld playingWorld) {

    }
}
