package com.indium.domain;

public class Address {

    private final String zip;

    private final String town;

    public Address() {
    	zip ="00";
    	town="NY";
    }
    
    public Address(final String zip, final String town) {
        this.zip = zip;
        this.town = town;
    }

    public String getZip() {
        return zip;
    }

    public String getTown() {
        return town;
    }
}
