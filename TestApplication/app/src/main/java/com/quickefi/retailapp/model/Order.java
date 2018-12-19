package com.quickefi.retailapp.model;

/*
    Order Status values:
1=Justin submitted the rental request
2=Elly accepted the request
3=Elly declined the request
4=Justin picked up the item
5=Justin returned the item
6=Complaint filed by Elly

     */
public class Order
{
    String remoteid;
    String name;
    String ownerid;
    String productid;
    String createDate;
    String status;
    int price;
    String pickupaddress;
    String dropoffaddress;
    String dropofftime;
    String pickuptime;

    String description;
    String renterid;



    public String getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(String pickuptime) {
        this.pickuptime = pickuptime;
    }



    public String getRemoteid() {
        return remoteid;
    }

    public void setRemoteid(String remoteid) {
        this.remoteid = remoteid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getDateCreated() {
        return createDate;
    }

    public void setDateCreated(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPickupaddress() {
        return pickupaddress;
    }

    public void setPickupaddress(String pickupaddress) {
        this.pickupaddress = pickupaddress;
    }

    public String getDropoffaddress() {
        return dropoffaddress;
    }

    public void setDropoffaddress(String dropoffaddress) {
        this.dropoffaddress = dropoffaddress;
    }

    public String getDropofftime() {
        return dropofftime;
    }

    public void setDropofftime(String dropofftime) {
        this.dropofftime = dropofftime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRenterid() {
        return renterid;
    }

    public void setRenterid(String renterid) {
        this.renterid = renterid;
    }
}

