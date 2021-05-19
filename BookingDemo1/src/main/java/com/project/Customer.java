package com.project;

public class Customer {

private String username;
private String password;
private String firstname;
private String lastname;
private String address;
private String phone;
private String email;
private int customer_id;
@Override
public String toString() {
return "Customer [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
+ lastname + ", address=" + address + ", phone=" + phone + ", email=" + email + ", customer_id="
+ customer_id + "]";
}
public int getCustomer_id() {
return customer_id;
}
public void setCustomer_id(int customer_id) {
this.customer_id = customer_id;
}
public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}
public String getUsername() {
return username;
}
public void setUsername(String username) {
this.username = username;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public String getFirstname() {
return firstname;
}
public void setFirstname(String firstname) {
this.firstname = firstname;
}
public String getLastname() {
return lastname;
}
public void setLastname(String lastname) {
this.lastname = lastname;
}
public String getAddress() {
return address;
}
public void setAddress(String address) {
this.address = address;
}
public String getPhone() {
return phone;
}
public void setPhone(String phone) {
this.phone = phone;
}







}