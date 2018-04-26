package bean;import java.io.Serializable;





public class Product_category implements Serializable{
private int id;
public int getId() {return id;}public void setId(int id) {this.id = id;}
private String name;
public String getName() {return name;}public void setName(String name) {this.name = name;}
private int parentId;
public int getParentId() {return parentId;}public void setParentId(int parentId) {this.parentId = parentId;}
private int type;
public int getType() {return type;}public void setType(int type) {this.type = type;}
private String iconClass;
public String getIconClass() {return iconClass;}public void setIconClass(String iconClass) {this.iconClass = iconClass;}
}
