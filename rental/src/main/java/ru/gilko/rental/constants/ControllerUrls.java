package ru.gilko.rental.constants;

public class ControllerUrls {
    private ControllerUrls() {}

    public static final String RENTAL_BASE_URL = "/rental";
    public static final String CONCRETE_RENTAL_URL = RENTAL_BASE_URL + "/{rentalUid}";
    public static final String FINISH_RENTAL_URL = CONCRETE_RENTAL_URL + "/finish k";
}
