
package WS;
import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String studentCode;
    private String name;
    private float importPrice;
    private float exportPrice;
    private String createdUser;
    private static final long serialVersionUID = 20151107;
    
    public Product() {
    }

    public Product(int id, String studentCode, String name, float importPrice, float exportPrice, String createdUser) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.createdUser = createdUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", studentCode=" + studentCode + ", name=" + name + ", importPrice=" + importPrice + ", exportPrice=" + exportPrice + ", createdUser=" + createdUser + '}';
    }
    
    
    
}
