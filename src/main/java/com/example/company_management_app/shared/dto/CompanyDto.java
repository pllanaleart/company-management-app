package com.example.company_management_app.shared.dto;

public class CompanyDto {

    private Long bussinessNo;
    private String name;
    private String address;
    private Long phone;
    private Long tvshNo;
    private Long fiskalNo;
    private String email;
    private String logo;
    private Long bankNo;

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
}
