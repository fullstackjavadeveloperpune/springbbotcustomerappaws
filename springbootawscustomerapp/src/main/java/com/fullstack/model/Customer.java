package com.fullstack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custId;

    private String custName;

    private String custAddress;

    private long custContactNumber;

    private double custAccBalance;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    private String custEmailId;

    private String custPassword;

    public Customer() {

    }

    public Customer(int custId, String custName, String custAddress, long custContactNumber, double custAccBalance, Date custDOB, String custEmailId, String custPassword) {
        this.custId = custId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custContactNumber = custContactNumber;
        this.custAccBalance = custAccBalance;
        this.custDOB = custDOB;
        this.custEmailId = custEmailId;
        this.custPassword = custPassword;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public long getCustContactNumber() {
        return custContactNumber;
    }

    public void setCustContactNumber(long custContactNumber) {
        this.custContactNumber = custContactNumber;
    }

    public double getCustAccBalance() {
        return custAccBalance;
    }

    public void setCustAccBalance(double custAccBalance) {
        this.custAccBalance = custAccBalance;
    }

    public Date getCustDOB() {
        return custDOB;
    }

    public void setCustDOB(Date custDOB) {
        this.custDOB = custDOB;
    }

    public String getCustEmailId() {
        return custEmailId;
    }

    public void setCustEmailId(String custEmailId) {
        this.custEmailId = custEmailId;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custContactNumber=" + custContactNumber +
                ", custAccBalance=" + custAccBalance +
                ", custDOB=" + custDOB +
                ", custEmailId='" + custEmailId + '\'' +
                ", custPassword='" + custPassword + '\'' +
                '}';
    }
}
