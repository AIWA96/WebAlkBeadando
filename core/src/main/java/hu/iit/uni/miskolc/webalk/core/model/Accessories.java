package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoAppellationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import org.jetbrains.annotations.Contract;

public class Accessories {
    private String appellation;
    private String brand;
    private float price;

    public Accessories(String appellation, String brand, float price) throws NoAppellationException, NoNameException, InvalidPriceException {
        setAppellation(appellation);
        setBrand(brand);
        setPrice(price);
    }

    public String getAppellation() {
        return appellation;
    }

    @Contract("null -> fail")
    private void setAppellation(String appellation) throws NoAppellationException {
        if (appellation == null || appellation.equals("")) {
            throw new NoAppellationException("Appellation must be set!");
        }
        this.appellation = appellation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws NoNameException {
        if (brand == null || brand.equals("")) {
            throw new NoNameException("Accessory must have a brand!");
        }
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    private void setPrice(float price) throws InvalidPriceException {
        if (price < 1) {
            throw new InvalidPriceException("The price cannot be negative!");
        }
        this.price = price;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accessories that = (Accessories) o;

        return Float.compare(that.price, price) == 0 &&
                appellation.equals(that.appellation) && brand.equals(that.brand);
    }

    @Override
    public int hashCode() {
        int result = appellation.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "appellation='" + appellation + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                "}\n";
    }
}
