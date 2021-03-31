package main.myAntlr.returnTypes;

import java.awt.*;
import java.util.List;

public class DrawCmdReturn {
    String retShape;
    Color retColor;
    List<Integer> retCoords;
    Integer retRadius;

    public DrawCmdReturn(String retShape, Color retColor, List<Integer> retCoords, Integer retRadius) {
        this.retShape = retShape;
        this.retColor = retColor;
        this.retCoords = retCoords;
        this.retRadius = retRadius;
    }

    public String getRetShape() {
        return retShape;
    }

    public Color getRetColor() {
        return retColor;
    }

    public List<Integer> getRetCoords() {
        return retCoords;
    }

    public Integer getRetRadius() {
        return retRadius;
    }
}
