package com.rmuhamed.catalogogastronomia.MODEL;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Branch implements Parcelable{
	private String FatherId;
	private String FatherName;
	private String BranchId;
	
	private String Name;
	private String Street;
	private String StreetNumber;
	private String Floor;
	private String Phone;
	private String WebPage;
	private String Description;
	private String Location;
	private String Provincia;
	private String Partido;
	private String Ciudad;
	
	private double Latitude;
	private double Longitude;

	private int Votes;
	
	private List<Benefit> Benefits;
	private List<ImageBranch> ImagesBranch;
	private List<ImageFather> ImagesFather;
	
	public static Creator<Branch> CREATOR = new Creator<Branch>() {
		public Branch createFromParcel(Parcel in) {
			return new Branch(in);
		}

		public Branch[] newArray(int size) {
			return new Branch[size];
		}
	};

	public Branch(){
	}
	
	@SuppressWarnings("unchecked")
		public Branch(Parcel in) {
		this.FatherId = in.readString();
		this.FatherName = in.readString();
		this.BranchId = in.readString();
		this.Name = in.readString();
		this.Street = in.readString();
		this.StreetNumber = in.readString();
		this.Floor = in.readString();
		this.Phone = in.readString();
		this.WebPage = in.readString();
		this.Description = in.readString();
		this.Location = in.readString();
		this.Provincia = in.readString();
		this.Partido = in.readString();
		this.Ciudad = in.readString();
		this.Latitude = in.readDouble();
		this.Longitude = in.readDouble();
		this.Votes = in.readInt();
		
		this.Benefits = in.readArrayList(Benefit.class.getClassLoader());
		this.ImagesFather = in.readArrayList(ImageFather.class.getClassLoader());
		this.ImagesBranch = in.readArrayList(ImageBranch.class.getClassLoader());
	}

	public String getFatherId() {
		return FatherId;
	}

	public void setFatherId(String fatherId) {
		FatherId = fatherId;
	}

	public String getFatherName() {
		return FatherName;
	}

	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getStreetNumber() {
		return StreetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}

	public String getFloor() {
		return Floor;
	}

	public void setFloor(String floor) {
		Floor = floor;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getWebPage() {
		return WebPage;
	}

	public void setWebPage(String webPage) {
		WebPage = webPage;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public String getPartido() {
		return Partido;
	}

	public void setPartido(String partido) {
		Partido = partido;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public int getVotes() {
		return Votes;
	}

	public void setVotes(int votes) {
		Votes = votes;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public List<Benefit> getBenefits() {
		return Benefits;
	}

	public void setBenefits(List<Benefit> benefits) {
		Benefits = benefits;
	}

	public List<ImageBranch> getImagesBranch() {
		return ImagesBranch;
	}

	public void setImagesBranch(List<ImageBranch> imagesBranch) {
		ImagesBranch = imagesBranch;
	}

	public List<ImageFather> getImagesFather() {
		return ImagesFather;
	}

	public void setImagesFather(List<ImageFather> imagesFather) {
		ImagesFather = imagesFather;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destiny, int flag) {
		destiny.writeString(this.FatherId);
		destiny.writeString(this.FatherName);
		destiny.writeString(this.BranchId);
		destiny.writeString(this.Name);
		destiny.writeString(this.Street);
		destiny.writeString(this.StreetNumber);
		destiny.writeString(this.Floor);
		destiny.writeString(this.Phone);
		destiny.writeString(this.WebPage);
		destiny.writeString(this.Description);
		destiny.writeString(this.Location);
		destiny.writeString(this.Provincia);
		destiny.writeString(this.Partido);
		destiny.writeString(this.Ciudad);
		destiny.writeDouble(this.Latitude);
		destiny.writeDouble(this.Longitude);
		destiny.writeInt(this.Votes);
		destiny.writeList(this.Benefits);
		destiny.writeList(this.ImagesFather);
		destiny.writeList(this.ImagesBranch);
	}
	
	@Override
	public boolean equals(Object obj) {
		Branch aBranch = (Branch) obj;

		boolean areTheSame = aBranch.getName().equalsIgnoreCase(this.getName());

		return areTheSame;
	}
}
