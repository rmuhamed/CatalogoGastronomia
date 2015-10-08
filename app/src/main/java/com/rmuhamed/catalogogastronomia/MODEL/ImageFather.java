package com.rmuhamed.catalogogastronomia.MODEL;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageFather implements Parcelable{
	public static int IMAGE_TYPE_LOGO = 7;
	public static int IMAGE_TYPE_SLIDER = 13;
	
	private String URL;
	private int Type;
	private int Great;
	
	public static Creator<ImageFather> CREATOR = new Creator<ImageFather>() {
		public ImageFather createFromParcel(Parcel in) {
			return new ImageFather(in);
		}

		public ImageFather[] newArray(int size) {
			return new ImageFather[size];
		}
	};

	public ImageFather(Parcel in) {
		this.URL = in.readString();
		this.Type = in.readInt();
		this.Great = in.readInt();
	}
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getGreat() {
		return Great;
	}

	public void setGreat(int great) {
		Great = great;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destiny, int flag) {
		destiny.writeString(this.URL);
		destiny.writeInt(this.Type);
		destiny.writeInt(this.Great);
	}
}
