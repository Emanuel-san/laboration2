package lab2.webshop.util;

public enum BackofficeFragments {
    DELETE_USER("bo/fragments/user_fragments", "deleteUser"),
    USER_ROLE("bo/fragments/user_fragments", "userRole");

    private final String templateName;
    private final String selector;

    BackofficeFragments(String templateName, String selector){
        this.templateName = templateName;
        this.selector = selector;
    }

    public String getTemplateName(){
        return this.templateName;
    }

    public String getSelector() {
        return this.selector;
    }
}
