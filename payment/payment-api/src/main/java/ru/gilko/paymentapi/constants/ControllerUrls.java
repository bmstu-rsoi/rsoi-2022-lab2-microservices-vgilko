package ru.gilko.paymentapi.constants;

public class ControllerUrls {

    private ControllerUrls() {
    }

    public static final String PAYMENT_BASE_URL = "/payment";
    public static final String PAYMENT_WITH_ID_URL = PAYMENT_BASE_URL + "/{paymentUid}";

}
