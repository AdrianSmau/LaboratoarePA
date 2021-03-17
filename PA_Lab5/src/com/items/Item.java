package com.items;

import com.exceptions.InvalidIDException;
import com.exceptions.InvalidNameException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Item implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags;

    public Item(String id, String name, String location) throws InvalidNameException, InvalidIDException {
        if (id == null || id.trim().equals(""))
            throw new InvalidIDException(id);
        if (name == null || name.trim().equals(""))
            throw new InvalidNameException(name);
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = new HashMap<>();
    }

    //id methods
    public String getId() {
        return id;
    }

    public void setId(String id) throws InvalidIDException {
        if (id == null || id.trim().equals(""))
            throw new InvalidIDException(id);
        this.id = id;
    }

    //name methods
    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
        if (name == null || name.trim().equals(""))
            throw new InvalidNameException(name);
        this.name = name;
    }

    //location methods
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //tags methods
    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }
}
