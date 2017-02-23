package kszorin.model;

public abstract class SeaCreature {
    protected int id;
    protected SealCreatureSpecies species;
    protected int posX;
    protected int posY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SealCreatureSpecies getSpecies() {
        return species;
    }

    public void setSpecies(SealCreatureSpecies species) {
        this.species = species;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public SeaCreature(int id, int posX, int posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "SeaCreature{" +
                "id=" + id +
                ", species=" + species +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    public abstract void lifeStep (PlayingWorld playingWorld);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeaCreature that = (SeaCreature) o;

        if (id != that.id) return false;
        return species == that.species;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + species.hashCode();
        return result;
    }

}
