package com.rmuhamed.catalogogastronomia.MODEL;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageBranch implements Parcelable{
	public static Creator<ImageBranch> CREATOR = new Creator<ImageBranch>() {
		public ImageBranch createFromParcel(Parcel in) {
			return new ImageBranch(in);
		}

		public ImageBranch[] newArray(int size) {
			return new ImageBranch[size];
		}
	};
	
	public ImageBranch(Parcel in) {
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
	}

}
