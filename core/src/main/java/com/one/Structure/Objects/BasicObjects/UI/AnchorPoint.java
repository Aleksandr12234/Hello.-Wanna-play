package com.one.Structure.Objects.BasicObjects.UI;

public enum AnchorPoint {
    TOP_LEFT( -1,  1),
    CENTER_LEFT(-1, 0),
    BUTTOM_LEFT(-1, -1),

    TOP_CENTER( 0,  1),
    CENTER(0, 0),
    BUTTOM_CENTER(0, -1),

    TOP_RIGHT( 1,  1),
    CENTER_RIGHT(1, 0),
    BUTTOM_RIGHT(1, -1);

    public final byte positionX;
    public final byte positionY;

    AnchorPoint(int positionX, int positionY){
        this.positionX=(byte) positionX;
        this.positionY=(byte) positionY;
    }
}
