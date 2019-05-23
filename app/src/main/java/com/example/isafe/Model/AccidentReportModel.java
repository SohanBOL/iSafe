package com.example.isafe.Model;

public class AccidentReportModel {

    private Images images;
    private AddressAndLatLong addressAndLatLong;
    private ContactDetails contactDetails;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public AddressAndLatLong getAddressAndLatLong() {
        return addressAndLatLong;
    }

    public void setAddressAndLatLong(AddressAndLatLong addressAndLatLong) {
        this.addressAndLatLong = addressAndLatLong;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public AccidentReportModel() {
    }

    public AccidentReportModel(Images images, AddressAndLatLong addressAndLatLong, ContactDetails contactDetails) {
        this.images = images;
        this.addressAndLatLong = addressAndLatLong;
        this.contactDetails = contactDetails;
    }
}
