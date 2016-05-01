package com.xm.sample.binding.entity;


public class Task {

    private String id;

    private String name;

    private boolean complete;

    public Task() {
    }

    public Task(String id, String name, boolean complete) {
        this.id = id;
        this.name = name;
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
