package controller;

public enum StaticPage {
    HOME("/html/index.html"),
    SIGNUP("/html/signup.html"),
    LOGIN("/html/login.html"),
    PROFILE("/html/profile.html"),
    SEARCH("/html/search.html"),
    FLIGHT("/html/flight.html"),
    PASSENGERS("/html/passengers.html"),
    SEATS("/html/seats.html"),
    SUMMARY("/html/summary.html"),
    PAY("/html/pay.html");

    final String path;

    StaticPage(String path) {
        this.path = path;
    }
}
