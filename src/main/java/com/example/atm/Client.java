package com.example.atm;

public class Client {
    private String name;
    private long ssn;
    private int order;

    public Client() {
        this.name = "";
        this.ssn = 0;
        this.order = 0;
    }

    public Client(String name, long ssn, int order) {
        this.name = name;
        this.ssn = ssn;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}