package com.ibm.project.response;

import com.ibm.project.entity.Address;

public class AddressResponse {

	private long addressId;
	private String street;
	private String city;
	
	public AddressResponse(Address address) {
		this.addressId = address.getAddressId();
		this.city = address.getCity();
		this.street = address.getStreet();
		
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
