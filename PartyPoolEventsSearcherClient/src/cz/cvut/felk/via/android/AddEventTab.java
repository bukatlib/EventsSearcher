package cz.cvut.felk.via.android;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.cvut.felk.via.android.SearchTab.EventAdapter;
import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.resources.EventResource;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddEventTab extends Fragment implements OnClickListener, Runnable {

	private Event addEvent;
	private boolean addWithoutErrors;
	private Date fromDate, toDate;
	Button addFromDate, addFromTime, addToDate, addToTime;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.add_layout, container, false);
		
		Button addEvent = (Button) v.findViewById(R.id.add_button);
		addEvent.setOnClickListener(this);
		
		Spinner category = (Spinner) v.findViewById(R.id.category_spinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.category_select, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(adapter);
		
		addFromDate = (Button) v.findViewById(R.id.add_from_date);
		addFromTime = (Button) v.findViewById(R.id.add_from_time);
		addToDate = (Button) v.findViewById(R.id.add_to_date);
		addToTime = (Button) v.findViewById(R.id.add_to_time);
		
		addFromDate.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				final Calendar currentDate = Calendar.getInstance();
				new DatePickerDialog(AddEventTab.this.getActivity() , mDateSetListener1,
						currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		addFromTime.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				final Calendar currentDate = Calendar.getInstance();
				new TimePickerDialog(AddEventTab.this.getActivity(), mTimeSetListener1,
						currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
			}
		});
		
		addToDate.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				int year, month, day;
				if (fromDate != null)	{
					year = fromDate.getYear()+1900;
					month = fromDate.getMonth();
					day = fromDate.getDate();
				} else {
					final Calendar currentDate = Calendar.getInstance();
					year = currentDate.get(Calendar.YEAR);
					month = currentDate.get(Calendar.MONTH);
					day = currentDate.get(Calendar.DAY_OF_MONTH);
				}
					
				new DatePickerDialog(AddEventTab.this.getActivity() , mDateSetListener2, year, month, day).show();			
			}
		});
		
		addToTime.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				final Calendar currentDate = Calendar.getInstance();
				new TimePickerDialog(AddEventTab.this.getActivity(), mTimeSetListener2,
						currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
			}
		});
		
		return v;
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);	
    }
    
	private DatePickerDialog.OnDateSetListener mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			fromDate = new Date(year-1900, monthOfYear, dayOfMonth);
			addFromDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(fromDate));
		}
	};
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			if (fromDate != null)	{
				fromDate.setHours(hourOfDay);
				fromDate.setMinutes(minute);
				addFromTime.setText(new SimpleDateFormat("HH:mm").format(fromDate));
			}
		}
	};
	
	private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			toDate = new Date(year-1900, monthOfYear, dayOfMonth);
			addToDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(toDate));
		}
	};	
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener2= new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			if (toDate != null)	{
				toDate.setHours(hourOfDay);
				toDate.setMinutes(minute);
				addToTime.setText(new SimpleDateFormat("HH:mm").format(toDate));
			}
		}
	};	
    
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onClick(View v) {
	/*	
		String value = sp.getSelectedItem().toString();*/
		View form = this.getView();
		Spinner category = (Spinner) form.findViewById(R.id.category_spinner);
		EditText organiser = (EditText) form.findViewById(R.id.add_organiser_text);		
		EditText shortDescription = (EditText) form.findViewById(R.id.short_description_text);
		EditText longDescription = (EditText) form.findViewById(R.id.long_description_text);
		
		if (fromDate != null && toDate != null && shortDescription.length() != 0 && longDescription.length() != 0 && organiser.length() != 0)	{
			addEvent = new Event();
			addEvent.setCategory(category.getSelectedItem().toString());
			addEvent.setEventOrganiser(organiser.getText().toString());
			addEvent.setShortDescription(shortDescription.getText().toString());
			addEvent.setLongDescription(longDescription.getText().toString());
			addEvent.setStartEvent(fromDate);
			addEvent.setStopEvent(toDate);

			// Run new thread.
			Thread addEvent = new Thread(this);
	        addEvent.start();			
		} else {
			Toast.makeText(this.getActivity(), this.getActivity().getResources().getString(R.string.error_data), Toast.LENGTH_LONG).show();
		}
	}

	Handler handler = new Handler()	{
		@Override
		public void handleMessage(Message msg)	{
			Context context = AddEventTab.this.getActivity();
			if (addWithoutErrors)	{
				Toast.makeText(context, context.getResources().getString(R.string.add_event_ok), Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, context.getResources().getString(R.string.add_event_fail), Toast.LENGTH_LONG).show();
			}
		}
	};	
	
	@Override
	public void run() {
        RestConnection connection = new RestConnection(this.getActivity());
        connection.createClientResource();
        EventResource resource = connection.getEventResource();
        if (resource != null)	{
        	if (resource.addEvent(addEvent))	{
        		addWithoutErrors = true;
        	} else {
        		addWithoutErrors = false;
        	}
        	
            handler.sendMessage(handler.obtainMessage());
        }		
	}
}
