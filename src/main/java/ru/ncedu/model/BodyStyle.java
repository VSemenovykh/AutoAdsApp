package ru.ncedu.model;

public enum BodyStyle {
    CARGOVAN,
    CONVERTIBLE,
    COUPE,
    CREWCABPICKUP,
    EXTENDEDCABPICKUP,
    HATCHBACK,
    MINIVAN,
    PASSANGERVAN,
    REGULARCABPICKUP,
    SUV,
    SEDAN,
    WAGON;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}