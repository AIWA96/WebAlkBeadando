package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoAppelationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;

public class Accessories {
    private String appellation;
    private String brand;
    private float price;

    public Accessories(String appellation, String brand, float price) throws NoAppelationException, NoNameException, InvalidPriceException {
        setAppellation(appellation);
        setBrand(brand);
        setPrice(price);
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) throws NoAppelationException {
        if (appellation == null || appellation == ""){
            throw new NoAppelationException("Appelation must be set!");
        }
        this.appellation = appellation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws NoNameException {
        if (brand == null || brand == ""){
            throw new NoNameException("Accessori must have a brand!");
        }
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws InvalidPriceException {
        if (price < 1){
            throw new InvalidPriceException("The price cannot be negative!");
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accessories that = (Accessories) o;

        if (Float.compare(that.price, price) != 0) return false;
        if (!appellation.equals(that.appellation)) return false;
        return brand.equals(that.brand);
    }

    @Override
    public int hashCode() {
        int result = appellation.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}
