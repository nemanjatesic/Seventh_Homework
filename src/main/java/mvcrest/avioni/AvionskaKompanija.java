package mvcrest.avioni;

import java.io.Serializable;

public class AvionskaKompanija implements Serializable {
    int id;
    private String name;
    int version;

    public AvionskaKompanija(int id, String name, int version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public AvionskaKompanija() {
    }

    public AvionskaKompanija id(int id) {
        this.id = id;
        return this;
    }

    public AvionskaKompanija name(String name) {
        this.name = name;
        return this;
    }

    public AvionskaKompanija version(int version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AvionskaKompanija{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
