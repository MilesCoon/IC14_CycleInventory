import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class Bicycle extends Cycle implements Serializable {
    private int frameSize;
    private int cranks;
    // Bicycle()
    Bicycle(String manufacturer, double price, int frameSize, int cranks) {
        super(manufacturer, price);
        this.frameSize = frameSize;
        if (frameSize != 15 && frameSize != 17 && frameSize != 19) {
            try {
                throw new InvalidFrameSizeException();
            } catch (InvalidFrameSizeException e) {
                System.err.println(e.getMessage());
            }
        }
        this.cranks = cranks;
    }
    // Getters and Setters
    public int getFrameSize() {return frameSize;}
    public void setFrameSize(int frameSize) {this.frameSize = frameSize;}
    public int getCranks() {return cranks;}
    public void setCranks(int cranks) {this.cranks = cranks;}
    // toString()
    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Bicycle [" + manufacturer +
                ", " + currency.format(price) +
                ", " + frameSize + "\"" +
                ", " + cranks + " gears]";
    }
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bicycle bicycle = (Bicycle) o;
        return frameSize == bicycle.frameSize && cranks == bicycle.cranks;
    }
    @Override
    public int hashCode() {return Objects.hash(super.hashCode(), frameSize, cranks);}
}
