package controller;

public enum StaticPage {
    HOME("/html/index.jsp"),
    SIGNUP("/html/signup.jsp"),
    LOGIN("/html/login.jsp"),
    PROFILE("/html/profile.jsp"),
    SEARCH("/html/search.jsp"),
    FLIGHT("/html/flight.jsp"),
    PASSENGERS("/html/passengers.jsp"),
    SEATS("/html/seats.jsp"),
    SUMMARY("/html/summary.jsp"),
    PAY("/html/pay.jsp");

    final String path;

    StaticPage(String path) {
        this.path = path;
    }
}
