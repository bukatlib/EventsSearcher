package cz.cvut.felk.via.android;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchTabSaveState implements Parcelable {
	
	private Date fromDate, toDate;
	private String organiser, eventDescription;

	public static final Parcelable.Creator<SearchTabSaveState> CREATOR = new Parcelable.Creator<SearchTabSaveState>() {

        public SearchTabSaveState createFromParcel(Parcel source) {
            return new SearchTabSaveState(source);
        }

        public SearchTabSaveState[] newArray(int size) {
            return new SearchTabSaveState[size];
        }

    };	
    
    public SearchTabSaveState(Date from, Date to, String org, String eventDesc)	{
    	fromDate = from;
    	toDate = to;
    	organiser = org;
    	eventDescription = eventDesc;
    }
    
    public SearchTabSaveState(Parcel source){
        // Retrieve saved data. (what if Date is null?)
    	fromDate = (Date) source.readValue(Date.class.getClassLoader());
    	toDate = (Date) source.readValue(Date.class.getClassLoader());
    	organiser = source.readString();
    	eventDescription = source.readString();
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
		dest.writeString(organiser);
		dest.writeString(eventDescription);
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

	public void setOrganiser(String organiser) {
		this.organiser = organiser;
	}

	public String getOrganiser() {
		return organiser;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDescription() {
		return eventDescription;
	}	
}
