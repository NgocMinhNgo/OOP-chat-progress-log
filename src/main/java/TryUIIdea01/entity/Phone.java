package TryUIIdea01.entity;


import TryUIIdea01.trait.common.Priced;
import com.google.gson.annotations.SerializedName;

public class Phone extends Product implements Priced {

    @SerializedName("BatteryCapacity")
    //private int batteryCapacity;
    private int batteryCapacity;  // Thay vì String "5000 mAh"

    @SerializedName("FrontCamera")
    private String frontCamera;

    @SerializedName("BackCamera")
    private String backCamera;

    @SerializedName("GPU")
    private String gpu;

    @SerializedName("Charging Port")
    private String chargingPort;

    @SerializedName("RAM")
    //private String ram;
    private int ram;              // Thay vì String "4GB"

    @SerializedName("Resolution")
    private String resolution;

    @SerializedName("ROM")
    //private String rom;
    private int rom;              // Thay vì String "128GB"

    @SerializedName("Screen Size")
    //private Double screenSize;
    private double screenSize;    // Thay vì String "6.7 inch"

    //@SerializedName("Discounted Price")
    //private String discountedPrice;
    //private double discountedPrice;  // Thay vì String "3.290.000₫"

    //@SerializedName("Discount Precent")
    //private String discountPercent;
    //private double discountPercent;  // Thay vì String "-35%"

    //@SerializedName("Sold Quanity")
    //private double soldQuantity;

    //@SerializedName("Origin")
    //private String origin;

    //@SerializedName("Warranty Duration")
    //private String warrantyDuration;


    //@SerializedName("Number Review")
    //private int numberReview;

    //private ArrayList<entity.Comment> comments;  // Thay List bằng ArrayList

    // Thêm getter và setter


    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getBackCamera() {
        return backCamera;
    }

    public void setBackCamera(String backCamera) {
        this.backCamera = backCamera;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getChargingPort() {
        return chargingPort;
    }

    public void setChargingPort(String chargingPort) {
        this.chargingPort = chargingPort;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "entity.Phone {" +
                "\n  ID='" + getId() + '\'' +
                "\n  Name='" + getName() + '\'' +
                "\n  Brand='" + getBrand() + '\'' +
                "\n  Price=" + getPrice() +
                "\n  Sold Quantity=" + getSoldQuantity() +
                "\n  Rating=" + getRating() +
                "\n  Number of Reviews=" + getNumberReview() +
                "\n  Battery Capacity=" + batteryCapacity + " mAh" +
                "\n  Front Camera='" + frontCamera + '\'' +
                "\n  Back Camera='" + backCamera + '\'' +
                "\n  GPU='" + gpu + '\'' +
                "\n  Origin='" + getOrigin() + '\'' +
                "\n  Charging Port='" + chargingPort + '\'' +
                "\n  RAM=" + ram + "GB" +
                "\n  ROM=" + rom + "GB" +
                "\n  Resolution='" + resolution + '\'' +
                "\n  Screen Size=" + screenSize + " inch" +
                "\n  Warranty Duration='" + getWarrantyDuration() + '\'' +
                "\n  Description='" + getDescription() + '\'' +
                "\n  Link='" + getLink() + '\'' +
                "\n}";
    }
}