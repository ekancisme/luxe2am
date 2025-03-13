/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuKien;

public class phuKienDTO {
    private String pkID;
    private String name;
    private String description;
    private String type;
    private String brand;
    private String image;
    private double price;
    private int status;

    // Constructor đầy đủ tham số
    public phuKienDTO(String pkID, String name, String description, String type, String brand, String image, double price, int status) {
        this.pkID = pkID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.image = image;
        this.price = price;
        this.status = status;
    }

    // Getter và Setter
    public String getPkID() {
        return pkID;
    }

    public void setPkID(String pkID) {
        this.pkID = pkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    // Phương thức toString để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "PhuKienDTO{" +
                "pkID='" + pkID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
