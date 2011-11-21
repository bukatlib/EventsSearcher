package cz.cvut.felk.via.android;

import android.os.Parcel;
import android.os.Parcelable;

public class AddEventTabSaveState implements Parcelable {
	
	// TODO - add private variables.

	public static final Parcelable.Creator<AddEventTabSaveState> CREATOR = new Parcelable.Creator<AddEventTabSaveState>() {

        public AddEventTabSaveState createFromParcel(Parcel source) {
            return new AddEventTabSaveState(source);
        }

        public AddEventTabSaveState[] newArray(int size) {
            return new AddEventTabSaveState[size];
        }

    };	
    
    public AddEventTabSaveState(/* */)	{
    	// TODO
    }
    
    public AddEventTabSaveState(Parcel source){
        // Retrieve saved data. TODO
    }
	
	@Override
	public int describeContents() {
		// Only adress based -> not usable for hash table.
		return this.hashCode();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// Save data. TODO
	}
}
