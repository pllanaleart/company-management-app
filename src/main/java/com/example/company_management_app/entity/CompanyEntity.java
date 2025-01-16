package com.example.company_management_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "company")
public class CompanyEntity {

    @Id
    private Long bussinessNo;
    private String name;
    private String address;
    private Long phone;
    private Long tvshNo;
    private Long fiskalNo;
    private String email;
    private String logo;
    private Long bankNo;
    @OneToMany(mappedBy = "company")
    private List<ProductsEntity> productsEntity;
    @OneToMany(mappedBy = "company")
    private List<StockEntity> stockEntities;
    @OneToMany(mappedBy = "company")
    private List<BuyersEntity> buyerEntity;
    @OneToMany(mappedBy = "company")
    private List<EmployeesEntity> employees;
    @OneToMany(mappedBy = "company")
    private List<ExpensesEntity> expenses;
    @OneToMany(mappedBy = "company")
    private List<SellingsEntity> sellings;
    @OneToMany(mappedBy = "company")
    private List<UsersEntity> users;

    public List<BuyersEntity> getBuyerEntity() {
        return buyerEntity;
    }

    public void setBuyerEntity(List<BuyersEntity> buyerEntity) {
        this.buyerEntity = buyerEntity;
    }

    public List<EmployeesEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeesEntity> employees) {
        this.employees = employees;
    }

    public List<ExpensesEntity> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpensesEntity> expenses) {
        this.expenses = expenses;
    }

    public List<SellingsEntity> getSellings() {
        return sellings;
    }

    public void setSellings(List<SellingsEntity> sellings) {
        this.sellings = sellings;
    }

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

    public List<StockEntity> getStockEntities() {
        return stockEntities;
    }

    public void setStockEntities(List<StockEntity> stockEntities) {
        this.stockEntities = stockEntities;
    }

    public Long getBussinessNo() {
        return bussinessNo;
    }

    public void setBussinessNo(Long bussinessNo) {
        this.bussinessNo = bussinessNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getTvshNo() {
        return tvshNo;
    }

    public void setTvshNo(Long tvshNo) {
        this.tvshNo = tvshNo;
    }

    public Long getFiskalNo() {
        return fiskalNo;
    }

    public void setFiskalNo(Long fiskalNo) {
        this.fiskalNo = fiskalNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getBankNo() {
        return bankNo;
    }

    public void setBankNo(Long bankNo) {
        this.bankNo = bankNo;
    }

    public List<ProductsEntity> getProductsEntity() {
        return productsEntity;
    }

    public void setProductsEntity(List<ProductsEntity> productsEntity) {
        this.productsEntity = productsEntity;
    }
}
