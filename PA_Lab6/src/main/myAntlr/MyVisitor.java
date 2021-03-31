package main.myAntlr;

import main.myAntlr.returnTypes.DrawCmdReturn;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyVisitor extends Java8BaseVisitor<Object> {

    @Override
    public Object visitParse(Java8Parser.ParseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Object visitExpr(Java8Parser.ExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public DrawCmdReturn visitDrawcmd(Java8Parser.DrawcmdContext ctx) {
        String shape;
        try {
            shape = ctx.SHAPE().getText();
        } catch (NullPointerException ex) {
            System.err.println("The specified Shape is not valid!...");
            return null;
        }
        List<Integer> coords = new ArrayList<>();
        Integer radius = null;
        try {
            for (TerminalNode x : ctx.INTEGER()) {
                if (coords.size() == 2)
                    radius = Integer.parseInt(x.getText());
                else
                    coords.add(Integer.parseInt(x.getText()));
            }
        } catch (NumberFormatException ex) {
            System.err.println("The specified coordinates or radius are not valid!...");
        }
        Color color;
        try {
            String colorName = ctx.COLOR().getText();
            if (colorName.equals("black"))
                color = Color.BLACK;
            else {
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                color = new Color(r, g, b);
            }
        } catch (NullPointerException ex) {
            System.err.println("The specified Color is not valid!...");
            return null;
        }
        return new DrawCmdReturn(shape, color, coords, radius);
    }

    @Override
    public Integer[] visitDeletecmd(Java8Parser.DeletecmdContext ctx) {
        Integer[] coords = new Integer[2];
        try {
            for (TerminalNode x : ctx.INTEGER()) {
                if (coords[0] == null)
                    coords[0] = Integer.parseInt(x.getText());
                else
                    coords[1] = Integer.parseInt(x.getText());
            }
        } catch (NumberFormatException ex) {
            System.err.println("Error! Command does not respect the requirements!...");
            return null;
        }
        return coords;
    }

    @Override
    public String visitFreehandcmd(Java8Parser.FreehandcmdContext ctx) {
        String cmd;
        try {
            cmd = ctx.getText();
        } catch (NullPointerException ex) {
            System.err.println("The specified Command is not valid!...");
            return null;
        }
        return cmd;
    }

    @Override
    public String visitResetcmd(Java8Parser.ResetcmdContext ctx) {
        String cmd;
        try {
            cmd = ctx.getText();
        } catch (NullPointerException ex) {
            System.err.println("The specified Command is not valid!...");
            return null;
        }
        return cmd;
    }

    @Override
    public String visitExitcmd(Java8Parser.ExitcmdContext ctx) {
        String cmd;
        try {
            cmd = ctx.getText();
        } catch (NullPointerException ex) {
            System.err.println("The specified Command is not valid!...");
            return null;
        }
        return cmd;
    }
}
