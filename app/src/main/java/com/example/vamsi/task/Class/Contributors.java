package com.example.vamsi.task.Class;

/**
 * Created by Vamsi on 08-01-2018.
 */

public class Contributors {

    public Contributors(String name, String image, String repo) {
        this.name = name;
        this.image = image;
        this.repo = repo;
    }

    public Contributors() {
    }

    String name;
    String image;
    String repo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }


}
