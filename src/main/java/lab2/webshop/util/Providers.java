package lab2.webshop.util;

public enum Providers {
    GOOGLE("accounts.google.com");

    private final String authority;

    Providers(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
