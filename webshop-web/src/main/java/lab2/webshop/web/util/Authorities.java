package lab2.webshop.web.util;

public enum Authorities {
    GOOGLE("accounts.google.com");
    
    private final String name;
    
    Authorities(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
