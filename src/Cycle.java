import java.io.Serializable;
import java.util.Objects;

public abstract class Cycle implements Serializable {
    protected String manufacturer;
    protected double price;
    // Cycle()
    protected Cycle(String manufacturer, double price) {
        this.manufacturer = manufacturer;
        this.price = price;
    }
    // Getters and Setters
    public String getManufacturer() {return manufacturer;}
    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cycle cycle = (Cycle) o;
        return Double.compare(price, cycle.price) == 0 && Objects.equals(manufacturer, cycle.manufacturer);
    }
    @Override
    public int hashCode() {return Objects.hash(manufacturer, price);}
}
