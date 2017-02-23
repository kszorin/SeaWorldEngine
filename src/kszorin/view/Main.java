package kszorin.view;

import kszorin.model.*;

public class Main {

    public static final byte FIELD_SIZE_X = 10;
    public static final byte FIELD_SIZE_Y = 15;
    public static final byte ORCAS_PERCENT_FILLING = 5;
    public static final byte PENGUINS_PERCENT_FILLING = 20;

    public static void main(String[] args) {
	    PlayingWorld world = new PlayingWorld(FIELD_SIZE_X, FIELD_SIZE_Y, ORCAS_PERCENT_FILLING, PENGUINS_PERCENT_FILLING);
    }
}
