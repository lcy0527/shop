package bean;import java.io.Serializable;










public class Product implements Serializable{
private int id;
public int getId() {return id;}public void setId(int id) {this.id = id;}
private String name;
public String getName() {return name;}public void setName(String name) {this.name = name;}
private String description;
public String getDescription() {return description;}public void setDescription(String description) {this.description = description;}
private float price;
public float getPrice() {return price;}public void setPrice(float price) {this.price = price;}
private int stock;
public int getStock() {return stock;}public void setStock(int stock) {this.stock = stock;}
private int categoryLevel1Id;
public int getCategoryLevel1Id() {return categoryLevel1Id;}public void setCategoryLevel1Id(int categoryLevel1Id) {this.categoryLevel1Id = categoryLevel1Id;}
private int categoryLevel2Id;
public int getCategoryLevel2Id() {return categoryLevel2Id;}public void setCategoryLevel2Id(int categoryLevel2Id) {this.categoryLevel2Id = categoryLevel2Id;}
private int categoryLevel3Id;
public int getCategoryLevel3Id() {return categoryLevel3Id;}public void setCategoryLevel3Id(int categoryLevel3Id) {this.categoryLevel3Id = categoryLevel3Id;}
private String fileName;
public String getFileName() {return fileName;}public void setFileName(String fileName) {this.fileName = fileName;}
private int isDelete;
public int getIsDelete() {return isDelete;}public void setIsDelete(int isDelete) {this.isDelete = isDelete;}
}
