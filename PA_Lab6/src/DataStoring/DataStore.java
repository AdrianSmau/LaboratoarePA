package DataStoring;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore implements Serializable {
    private List<Shape> shapes;
    private Map<Shape, Color> shapeColorMap;

    public DataStore() {
        this.shapes = new ArrayList<>();
        this.shapeColorMap = new HashMap<>();
    }

    public void addToDataStore(Shape shape, Color color) {
        this.shapes.add(shape);
        this.shapeColorMap.put(shape, color);
    }

    public void removeFromDataStore(Shape shape) {
        this.shapes.remove(shape);
        this.shapeColorMap.remove(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Map<Shape, Color> getShapeColorMap() {
        return shapeColorMap;
    }

    public void setShapeColorMap(Map<Shape, Color> shapeColorMap) {
        this.shapeColorMap = shapeColorMap;
    }
}
