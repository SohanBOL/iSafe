package com.example.isafe.Model;

public class AccidentReport {
    private Images images;
    private AddressAndLatLong addressAndLatLong;
    private ContactDetails contactDetails;
    public  static AccidentReport accidentReport=null;

    public static AccidentReport getAccidentReport() {

        if(accidentReport!=null)
            return accidentReport;
        else
            return new AccidentReport();

    }

    private AccidentReport() {
    }

    public void setAccidentReport(Images images, AddressAndLatLong addressAndLatLong, ContactDetails contactDetails)
    {

        accidentReport=new AccidentReport(images,addressAndLatLong,contactDetails);

    }

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

    public AccidentReport(Images images, AddressAndLatLong addressAndLatLong, ContactDetails contactDetails) {
        this.images = images;
        this.addressAndLatLong = addressAndLatLong;
        this.contactDetails = contactDetails;
    }
}
