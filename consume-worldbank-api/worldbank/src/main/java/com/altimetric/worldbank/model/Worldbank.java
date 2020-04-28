package com.altimetric.worldbank.model;

public class Worldbank extends BaseEntity{

	private static final long serialVersionUID = -7970898796914773282L;
	
	private String name;
	private Region region;
	private Adminregion adminregion;
	private IncomeLevel incomeLevel;
	private LendingType lendingType;
	private String capitalCity;
	private String longitude;
	private String latitude;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Adminregion getAdminregion() {
		return adminregion;
	}
	public void setAdminregion(Adminregion adminregion) {
		this.adminregion = adminregion;
	}
	public IncomeLevel getIncomeLevel() {
		return incomeLevel;
	}
	public void setIncomeLevel(IncomeLevel incomeLevel) {
		this.incomeLevel = incomeLevel;
	}
	public LendingType getLendingType() {
		return lendingType;
	}
	public void setLendingType(LendingType lendingType) {
		this.lendingType = lendingType;
	}
	public String getCapitalCity() {
		return capitalCity;
	}
	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
