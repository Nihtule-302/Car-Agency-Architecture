package identification;

public class Identification {
    private String name;
    private int id;

    // Void constructor
    public Identification() {}

    // Constructor with name parameter
    public Identification(String name) {
        this.name = name;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
}
