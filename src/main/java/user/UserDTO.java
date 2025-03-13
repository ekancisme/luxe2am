/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Admin
 * 
 */
public class UserDTO {
    private String userID;
    private String fullName;
    private String password;
    private String roleID;
    private String gmail;
    private String address;
    private Integer status;

    public UserDTO(String userID, String fullName, String password, String roleID, String gmail, String address, Integer status) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.address = address;
        this.status = status;
    }

    
    public UserDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", fullName=" + fullName + ", password=" + password + ", roleID=" + roleID + ", gmail=" + gmail + ", address=" + address + ", status=" + status + '}';
    }
    
}
