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
public class UserError {
    private String userID;
    private String fullName;
    private String password;
    private String roleID;
    private String gmail;
    private String address;
    private Integer status;
    private String confirm;
    public UserError(String userID, String fullName, String password, String roleID, String gmail, String address, Integer status, String confirm) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.address = address;
        this.status = status;
        this.confirm=confirm;
    }

    
    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.password = "";
        this.roleID = "";
        this.gmail = "";
        this.address = "";
        this.status = 0;
        this.confirm="";
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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
    
}
