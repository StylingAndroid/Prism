package com.stylingandroid.prism;

public final class ColourUtils {
    public static final int HEX_000 = 0x00;
    public static final int HEX_255 = 0xFF;
    public static final int HEX_128 = 0x80;
    public static final int EIGHT_BITS = 8;
    public static final int SIXTEEN_BITS = 16;
    public static final int TWENTY_FOUR_BITS = 24;

    public static final int TEST_COLOUR = rgb(HEX_255, HEX_255, HEX_255);

    private ColourUtils() {
        //NO-OP
    }

    public static int rgb(int red, int green, int blue) {
        return (HEX_255 << TWENTY_FOUR_BITS) | (red << SIXTEEN_BITS) | (green << EIGHT_BITS) | blue;
    }

    public static int argb(int alpha, int red, int green, int blue) {
        return (alpha << TWENTY_FOUR_BITS) | (red << SIXTEEN_BITS) | (green << EIGHT_BITS) | blue;
    }
}
