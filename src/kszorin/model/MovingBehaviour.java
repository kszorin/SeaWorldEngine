package kszorin.model;

import java.util.List;

public interface MovingBehaviour {
    void move (Animal animal, PlayingWorld playingWorld, List<Position> findInEnvirons);
}
