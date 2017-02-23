package kszorin.model;


public class PlayingWorld {
    public static final byte FIELD_WIDTH = 10;
    public static final byte FIELD_HEIGHT = 20;
    public static final byte ORCAS_PERCENT_FILLING = 5;
    public static final byte PENGUIN_PERCENT_FILLING = 20;

    private int orcasQuantity = FIELD_WIDTH * FIELD_HEIGHT / 100 * 5;
    private int penguinQuantity = FIELD_WIDTH * FIELD_HEIGHT / 100 * 20;
    private int waterSpace[][] = new int[FIELD_WIDTH][FIELD_HEIGHT];

}
