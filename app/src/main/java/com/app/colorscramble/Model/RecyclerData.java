package com.app.colorscramble.Model;

public class RecyclerData {

    private int color;
    private int pos;
    private boolean value;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }


    public RecyclerData(int pos,int color,boolean value) {
        this.color = color;
        this.pos = pos;
        this.value = value;
    }

}
