package bean;import java.io.Serializable;





public class Order_detail implements Serializable{
private int id;
public int getId() {return id;}public void setId(int id) {this.id = id;}
private int orderId;
public int getOrderId() {return orderId;}public void setOrderId(int orderId) {this.orderId = orderId;}
private int productId;
public int getProductId() {return productId;}public void setProductId(int productId) {this.productId = productId;}
private int quantity;
public int getQuantity() {return quantity;}public void setQuantity(int quantity) {this.quantity = quantity;}
private float cost;
public float getCost() {return cost;}public void setCost(float cost) {this.cost = cost;}
}
