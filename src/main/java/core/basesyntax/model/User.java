package core.basesyntax.model;

public class User {
    private String name;

    User(String name) {
        this.name = checkName(name);
    }

    public String getName() {
        return name;
    }

    private String checkName(String name) {
        if (name == null || name.endsWith("")) {
            throw new RuntimeException("Wrong name");
        }
        return name;
    }
}
