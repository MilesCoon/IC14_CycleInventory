import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class Motorcycle extends Cycle implements Serializable {
    private int displacement;
    private double fuelCapacity;
    // Motorcycle()
    Motorcycle(String manufacturer, double price, int displacement, double fuelCapacity) {
        super(manufacturer, price);
        this.displacement = displacement;
        this.fuelCapacity = fuelCapacity;
    }
    // Getters and Setters
    public int getDisplacement() {return displacement;}
    public void setDisplacement(int displacement) {this.displacement = displacement;}
    public double getFuelCapacity() {return fuelCapacity;}
    public void setFuelCapacity(double fuelCapacity) {this.fuelCapacity = fuelCapacity;}
    // toString()
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Motorcycle [" + manufacturer +
                ", " + currency.format(price) +
                ", " + displacement + " cc" +
                ", " + fuelCapacity + " gallons]";
    }
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return displacement == that.displacement && Double.compare(fuelCapacity, that.fuelCapacity) == 0;
    }
    @Override
    public int hashCode() {return Objects.hash(super.hashCode(), displacement, fuelCapacity);}
}
