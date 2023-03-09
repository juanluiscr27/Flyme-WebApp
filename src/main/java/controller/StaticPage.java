package controller;

public enum StaticPage {
    HOME("/html/search.html"),
    SIGNUP("/html/signup.html"),
    LOGIN("/html/login.html"),
    PROFILE("/html/profile.html"),
    SEARCH("/html/search.html");

    final String path;

    StaticPage(String path) {
        this.path = path;
    }
}
