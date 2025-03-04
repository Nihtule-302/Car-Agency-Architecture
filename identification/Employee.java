package identification;

public class Employee extends Identification {
    private static int idCounter = 0;
    private double payCheck = 10000;

    public Employee(){
    }
    public Employee(String name){
        if(idCounter>=0 && idCounter<=499){
            setName(name);
            idCounter++;
            setId(idCounter);
        }   
    }

    public void increasePayCheck(double money){
        this.payCheck += money;
    }
    public double getPayCheck() {
        return payCheck;
    }
}
