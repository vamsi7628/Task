package com.example.vamsi.task.Class;

/**
 * Created by Vamsi on 08-01-2018.
 */

public class Repositors {

    public Repositors() {
    }

    public Repositors(String name, String description, String stars, String forks, String watcher, String contributors) {
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.forks = forks;
        this.watcher = watcher;
        this.contributors = contributors;
    }

    String name;
    String description;
    String stars;
    String forks;
    String watcher;
    String contributors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getWatcher() {
        return watcher;
    }

    public void setWatcher(String watcher) {
        this.watcher = watcher;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }



}
