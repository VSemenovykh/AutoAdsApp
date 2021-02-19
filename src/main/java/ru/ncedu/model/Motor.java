package ru.ncedu.model;

public class Motor {
    private int id;
    private Fuel motorType;
    private double volume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fuel getMotorType() {
        return motorType;
    }

    public void setMotorType(Fuel motorType) {
        this.motorType = motorType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
