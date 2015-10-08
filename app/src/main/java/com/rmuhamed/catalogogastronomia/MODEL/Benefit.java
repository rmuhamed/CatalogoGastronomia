package com.rmuhamed.catalogogastronomia.MODEL;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Benefit implements Parcelable{
	private String Id;
	private String Description;
	private int Votes;
	private String Category;
	private String CategoryFriendly;
	private String SubCategory;
	private String Atribute;
	private String Type;
	private String Frecuency;
	private String FrecuencyFriendly;
	private boolean SpecialCases;
	private String BenefitName;
	private String BenefitState;
	private String ModifiedDate;
	private List<Card> Cards;
	private List<Aliance> Aliances;
	
	public static final Creator<Benefit> CREATOR = new Creator<Benefit>() {
		@Override
		public Benefit createFromParcel(Parcel in) {
			Benefit benefit = new Benefit(in);
			return benefit;
		}
		
		@Override
		public Benefit[] newArray(int size) {
			Benefit[] benefits = new Benefit[size];
			return benefits;
		}
	};
	
	@SuppressWarnings("unchecked")
	public Benefit(Parcel in) {
		this.Id = in.readString();
		this.Description = in.readString();
		this.Votes = in.readInt();
		this.Category = in.readString();
		this.CategoryFriendly = in.readString();
		this.SubCategory = in.readString();
		this.Atribute = in.readString();
		this.Type = in.readString();
		this.Frecuency = in.readString();
		this.FrecuencyFriendly = in.readString();
		
		boolean[] temp = new boolean[1];
		in.readBooleanArray(temp);
		this.SpecialCases = temp[0];
		
		this.BenefitName = in.readString();
		this.BenefitState = in.readString();
		this.ModifiedDate = in.readString();
		
		this.Cards = in.readArrayList(Card.class.getClassLoader());
	}
	
	public String getSubCategory() {
		return SubCategory;
	}

	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}

	public String getAtribute() {
		return Atribute;
	}

	public void setAtribute(String atribute) {
		Atribute = atribute;
	}

	public String getFrecuencyFriendly() {
		return FrecuencyFriendly;
	}

	public void setFrecuencyFriendly(String frecuencyFriendly) {
		FrecuencyFriendly = frecuencyFriendly;
	}

	public String getBenefitName() {
		return BenefitName;
	}

	public void setBenefitName(String benefitName) {
		BenefitName = benefitName;
	}

	public String getBenefitState() {
		return BenefitState;
	}

	public void setBenefitState(String benefitState) {
		BenefitState = benefitState;
	}

	public String getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public boolean isSpecialCases() {
		return SpecialCases;
	}

	public void setSpecialCases(boolean specialCases) {
		SpecialCases = specialCases;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getVotes() {
		return Votes;
	}

	public void setVotes(int votes) {
		Votes = votes;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCategoryFriendly() {
		return CategoryFriendly;
	}

	public void setCategoryFriendly(String categoryFriendly) {
		CategoryFriendly = categoryFriendly;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getFrecuency() {
		return Frecuency;
	}

	public void setFrequency(String frecuency) {
		Frecuency = frecuency;
	}

	public List<Card> getCards() {
		return Cards;
	}

	public void setCards(List<Card> cards) {
		Cards = cards;
	}

	public List<Aliance> getAliances() {
		return Aliances;
	}

	public void setAliances(List<Aliance> aliances) {
		Aliances = aliances;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeString(this.Id);
		dest.writeString(this.Description);
		dest.writeInt(this.Votes);
		dest.writeString(this.Category);
		dest.writeString(this.CategoryFriendly);
		dest.writeString(this.SubCategory);
		dest.writeString(this.Atribute);
		dest.writeString(this.Type);
		dest.writeString(this.Frecuency);
		dest.writeString(this.FrecuencyFriendly);
		dest.writeBooleanArray(new boolean[]{this.SpecialCases});
		dest.writeString(this.BenefitName);
		dest.writeString(this.BenefitState);
		dest.writeString(this.ModifiedDate);
		dest.writeList(this.Cards);
	}
}
