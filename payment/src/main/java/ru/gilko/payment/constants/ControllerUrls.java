package ru.gilko.payment.constants;

public class ControllerUrls {

    private ControllerUrls() {
    }

    public static final String PAYMENT_BASE_URL = "/payment";
    public static final String PAYMENT_WITH_ID_URL = PAYMENT_BASE_URL + "/{paymentUid}";

}
