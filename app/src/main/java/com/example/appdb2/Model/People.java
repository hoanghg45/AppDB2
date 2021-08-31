package com.example.appdb2.Model;

public class People {
    private int ID;
    private String Name;
    private String Add;
    private String Sex;
    private String Contract;
    private int Phone;

    public People(int ID, String name, String add, String sex, String contract, int phone) {
        this.ID = ID;
        Name = name;
        Add = add;
        Sex = sex;
        Contract = contract;
        Phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getContract() {
        return Contract;
    }

    public void setContract(String contract) {
        Contract = contract;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }
}
