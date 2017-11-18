package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoAppelationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;

public class Accessories {
    private String appellation;
    private String name;
    private float price;

    public Accessories(String appellation, String name, float price) throws NoAppelationException, NoNameException, InvalidPriceException {
        setAppellation(appellation);
        setName(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws NoNameException {
        if (name == null || name == ""){
            throw new NoNameException("Accessori must have a name!");
        }
        this.name = name;
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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = appellation.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}
