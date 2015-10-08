package com.rmuhamed.catalogogastronomia.MODEL;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable{
	private String Name;
	
	public static final Creator<Card> CREATOR = new Creator<Card>() {
		@Override
		public Card createFromParcel(Parcel in) {
			Card card = new Card(in);
			return card;
		}

		@Override
		public Card[] newArray(int size) {
			Card[] cards = new Card[size];
			return cards;
		}
	};

	public Card(Parcel in) {
		this.Name = in.readString();
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeString(this.Name);
	}
}
