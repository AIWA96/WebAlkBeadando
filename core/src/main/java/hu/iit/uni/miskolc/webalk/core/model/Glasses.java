package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationSetException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import org.jetbrains.annotations.Contract;

public class Glasses {
    private String brand;
    private String model;
    private float price;
    private String availableAt;
    private String gender;
    private boolean sunglasses;

    /**
     * @param brand
     * @param model
     * @param price
     * @param availableAt
     * @param gender
     * @throws NoLocationSetException
     * @throws InvalidPriceException
     * @throws NoNameException
     * @throws NoGenderException
     */
    public Glasses(String brand, String model, float price, String availableAt, String gender, boolean sunglasses) throws NoLocationSetException, InvalidPriceException, NoNameException, NoGenderException {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setAvailableAt(availableAt);
        setGender(gender);
        setSunglasses(sunglasses);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws NoNameException {
        if (brand == null) {
            throw new NoNameException("A sunglass must have a brand name!");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws NoNameException {
        if (model == null) {
            throw new NoNameException("A sunglass must have a model name!");
        }
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    private void setPrice(float price) throws InvalidPriceException {
        if (price < 1) {
            throw new InvalidPriceException("A price cannot be negative!");
        }
        this.price = price;
    }

    public String getAvailableAt() {
        return availableAt;
    }

    @Contract("null -> fail")
    private void setAvailableAt(String availableAt) throws NoLocationSetException {
        if (availableAt == null || availableAt.equals("")) {
            throw new NoLocationSetException("The shop's location must be set!");
        }
        this.availableAt = availableAt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws NoGenderException {
        if (gender == null) {
            throw new NoGenderException("A gender must be set!");
        }
        this.gender = gender;
    }

    public boolean isSunglasses() {
        return sunglasses;
    }

    private void setSunglasses(boolean sunglasses) {
        this.sunglasses = sunglasses;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Glasses that = (Glasses) o;

        return Float.compare(that.price, price) == 0 &&
                brand.equals(that.brand) && model.equals(that.model) &&
                (availableAt != null ? availableAt.equals(that.availableAt) :
                        that.availableAt == null);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (availableAt != null ? availableAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Glasses{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", availableAt='" + availableAt + '\'' +
                ", gender='" + gender + '\'' +
                ", sunglasses=" + sunglasses +
                "}\n";
    }
}
