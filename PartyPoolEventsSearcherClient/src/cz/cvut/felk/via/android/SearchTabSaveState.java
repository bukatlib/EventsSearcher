package cz.cvut.felk.via.android;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchTabSaveState implements Parcelable {
	
	private Date fromDate, toDate;

	public static final Parcelable.Creator<SearchTabSaveState> CREATOR = new Parcelable.Creator<SearchTabSaveState>() {

        public SearchTabSaveState createFromParcel(Parcel source) {
            return new SearchTabSaveState(source);
        }

        public SearchTabSaveState[] newArray(int size) {
            return new SearchTabSaveState[size];
        }

    };	
    
    public SearchTabSaveState(Date from, Date to)	{
    	fromDate = from;
    	toDate = to;
    }
    
    public SearchTabSaveState(Parcel source){
        // Retrieve saved data.
    	fromDate = (Date) source.readValue(Date.class.getClassLoader());
    	toDate = (Date) source.readValue(Date.class.getClassLoader());
    }
	
	@Override
	public int describeContents() {
		// Only adress based -> not usable for hash table.
		return this.hashCode();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// Save data.
		dest.writeValue(fromDate);
		dest.writeValue(toDate);
	}
	
    public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}	
}
