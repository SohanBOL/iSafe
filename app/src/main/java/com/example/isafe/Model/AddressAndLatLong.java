package com.example.isafe.Model;

public class AddressAndLatLong {
    private String address;
    private double lattitude,longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public AddressAndLatLong(String address, double lattitude, double longitude) {
        this.address = address;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }
}
