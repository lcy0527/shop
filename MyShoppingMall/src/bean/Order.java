package bean;import java.io.Serializable;




import java.util.Date;
import java.util.Date;
import java.util.Date;
public class Order implements Serializable{
private int id;
public int getId() {return id;}public void setId(int id) {this.id = id;}
private int userId;
public int getUserId() {return userId;}public void setUserId(int userId) {this.userId = userId;}
private String loginName;
public String getLoginName() {return loginName;}public void setLoginName(String loginName) {this.loginName = loginName;}
private String userAddress;
public String getUserAddress() {return userAddress;}public void setUserAddress(String userAddress) {this.userAddress = userAddress;}
private Date createTime;
public Date getCreateTime() {return createTime;}public void setCreateTime(Date createTime) {this.createTime = createTime;}
private float cost;
public float getCost() {return cost;}public void setCost(float cost) {this.cost = cost;}
private String serialNumber;
public String getSerialNumber() {return serialNumber;}public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
}
