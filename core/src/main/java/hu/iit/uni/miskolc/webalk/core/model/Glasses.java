package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;

public class Glasses {
    private String brand;
    private String model;
    private float price;
    private String availableAt;
    private Gender gender;
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
    public Glasses(String brand, String model, float price, String availableAt, Gender gender, boolean sunglasses) throws NoLocationSetException, InvalidPriceException, NoNameException, NoGenderException, InvalidGenderTypeException {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setAvailableAt(availableAt);
        setGender(gender);
        this.sunglasses = sunglasses;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws NoNameException {
        if (brand == null){
            throw new NoNameException("A sunglass must have a brand name!");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws NoNameException {
        if (model == null){
            throw new NoNameException("A sunglass must have a model name!");
        }
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws InvalidPriceException {
        if (price < 1){
            throw new InvalidPriceException("A price cannot be negative!");
        }
        this.price = price;
    }

    public String getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(String availableAt) throws NoLocationSetException {
        if (availableAt == null || availableAt == ""){
            throw new NoLocationSetException("The shop's location must be set!");
        }
        this.availableAt = availableAt;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) throws NoGenderException, InvalidGenderTypeException {
        if (gender == null){
            throw new NoGenderException("A gender must be set!");
        }else if (gender == Gender.OTHER){
            throw new InvalidGenderTypeException("A glass cannot an \"other\" gender type!");
        }
        this.gender = gender;
    }

    public boolean isSunglasses() {
        return sunglasses;
    }

    public void setSunglasses(boolean sunglass) {
        this.sunglasses = sunglasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Glasses that = (Glasses) o;

        if (Float.compare(that.price, price) != 0) return false;
        if (!brand.equals(that.brand)) return false;
        if (!model.equals(that.model)) return false;
        return availableAt != null ? availableAt.equals(that.availableAt) : that.availableAt == null;
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (availableAt != null ? availableAt.hashCode() : 0);
        return result;
    }
}
