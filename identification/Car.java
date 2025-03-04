package identification;

public class Car extends Identification {
    private static int idCounter = 1000;
    private double price;
    private double rent;

    // No-arg constructor
    public Car() {
    }

    // Constructor with model, price, and rent parameters
    public Car(String model, double price, double rent) {
        if(idCounter>=1000 && idCounter<=1499){
            setName(model);
            this.price = price;
            this.rent = rent;
            idCounter++;
            setId(idCounter);    
        }
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Getter for Rent
    public double getRent() {
        return rent;
    }
}
