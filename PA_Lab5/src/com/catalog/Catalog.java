package com.catalog;

import com.exceptions.InvalidNameException;
import com.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Item> items;

    public Catalog(String name, String path) throws InvalidNameException {
        if (name == null || name.trim().equals(""))
            throw new InvalidNameException(name);
        this.name = name;
        this.path = path;
        this.items = new ArrayList<>();
    }

    public Catalog() {
        this.name = null;
        this.path = null;
        this.items = new ArrayList<>();
    }

    public Item findById(String id){
        return items.stream()
                .filter(current -> current.getId().equals(id)).findFirst().orElse(null);
    }

    //name methods
    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidNameException {
        if(name == null || name.trim().equals(""))
            throw new InvalidNameException(name);
        this.name = name;
    }

    //path methods
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    //items methods
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
