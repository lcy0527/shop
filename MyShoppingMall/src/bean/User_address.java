package bean;import java.io.Serializable;



import java.util.Date;
import java.util.Date;
import java.util.Date;
public class User_address implements Serializable{
private int id;
public int getId() {return id;}public void setId(int id) {this.id = id;}
private int userId;
public int getUserId() {return userId;}public void setUserId(int userId) {this.userId = userId;}
private String address;
public String getAddress() {return address;}public void setAddress(String address) {this.address = address;}
private Date createTime;
public Date getCreateTime() {return createTime;}public void setCreateTime(Date createTime) {this.createTime = createTime;}
private int isDefault;
public int getIsDefault() {return isDefault;}public void setIsDefault(int isDefault) {this.isDefault = isDefault;}
private String remark;
public String getRemark() {return remark;}public void setRemark(String remark) {this.remark = remark;}
}
