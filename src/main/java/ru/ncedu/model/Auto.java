package ru.ncedu.model;

public class Auto {
    private int id;
    private Brand brand;
    private String color;
    private double price;
    private Motor motor;
    private Transmission transmissionType;
    private Drive driveType;
    private BodyStyle bodyStyleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Transmission getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(Transmission transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Drive getDriveType() {
        return driveType;
    }

    public void setDriveType(Drive driveType) {
        this.driveType = driveType;
    }

    public BodyStyle getBodyStyleType() {
        return bodyStyleType;
    }

    public void setBodyStyleType(BodyStyle bodyStyleType) {
        this.bodyStyleType = bodyStyleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
