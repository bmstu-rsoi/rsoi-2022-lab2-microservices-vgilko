package ru.gilko.carsapi.constants;

public class ControllerUrl {
    private ControllerUrl(){}

    public static final String CARS_URL = "/cars";
    public static final String CONCRETE_CAR_URL = CARS_URL + "/{carId}";
}
