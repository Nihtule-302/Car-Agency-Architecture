package identification;

public class Customer extends Identification {
    private static int idCounter = 500;

    // No-arg constructor
    public Customer() {
    }

    // Constructor with name parameter
    public Customer(String name) {
        if(idCounter>=500 && idCounter<=999){
            setName(name);
            idCounter++;
            setId(idCounter);
        }   
    }


}
