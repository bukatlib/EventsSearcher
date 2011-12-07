package cz.cvut.felk.via.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.resources.EventResource;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class SearchTab extends Fragment implements OnClickListener, Runnable {

	private Date fromDate, toDate;
	private ArrayList<Event> foundEvents = new ArrayList<Event>();	
	private Map<String, String> queryParameters = null;
	
	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("SearchTab","onCreate");
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
        View v = inflater.inflate(R.layout.search_layout, container, false);
        
        Button pickFromDate = (Button) v.findViewById(R.id.pick_from_date);
        Button pickToDate = (Button) v.findViewById(R.id.pick_to_date);
        Button findButton = (Button) v.findViewById(R.id.find_button);

        pickFromDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar currentDate = Calendar.getInstance();	
				new DatePickerDialog(SearchTab.this.getActivity() , mDateSetListener1,
						currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show();			
			}
		});
        
        pickToDate.setOnClickListener(new View.OnClickListener() {
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
					
				new DatePickerDialog(SearchTab.this.getActivity() , mDateSetListener2, year, month, day).show();			
			}
		});
        
        findButton.setOnClickListener(this);      
        
        listView = (ListView) v.findViewById(R.id.finded_events_list);
        Log.i("SearchTab", "onCreateView");
        
        return v;
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Restore fragment setting.
		if (savedInstanceState != null)	{
			if (savedInstanceState.containsKey("SeachTabState"))	{
				SearchTabSaveState state = savedInstanceState.getParcelable("SeachTabState");
				fromDate = state.getFromDate();
				toDate = state.getToDate();
				((EditText) getView().findViewById(R.id.organiser_text)).setText(state.getOrganiser());
				((EditText) getView().findViewById(R.id.description_text)).setText(state.getEventDescription());
				Log.i("SearchTab", "state restored");
			}
		}  		
		if (fromDate != null)	{
			updateDateText(fromDate, 1);
		}
		if (toDate != null)	{
			updateDateText(toDate, 2);
		}
		Log.i("SearchTab", "onActivityCreated");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String organiser = ((EditText) getView().findViewById(R.id.organiser_text)).getText().toString();
		String description = ((EditText) getView().findViewById(R.id.description_text)).getText().toString();
		SearchTabSaveState tabData = new SearchTabSaveState(fromDate, toDate, organiser, description);
		outState.putParcelable("SeachTabState", tabData);
		Log.i("SearchTab", "onSaveInstaceState");
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			fromDate = new Date(year-1900, monthOfYear, dayOfMonth);
			updateDateText(fromDate,1);
		}
	};

	private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			toDate = new Date(year-1900, monthOfYear, dayOfMonth);
			updateDateText(toDate,2);			
		}
	};

	private void updateDateText(Date date, int idTextView) {
		TextView dateLabel = null;
		switch (idTextView)	{
		case 1:
			dateLabel = (TextView) getView().findViewById(R.id.from_date);
			break;
		case 2:
			dateLabel = (TextView) getView().findViewById(R.id.to_date);
			break;
		}
		
		dateLabel.setText(new SimpleDateFormat("dd.MM.yyyy").format(date));
	}

	@Override
	public void onClick(View v) {
		// Get all query parameters.
		foundEvents = new ArrayList<Event>();
		queryParameters = new HashMap<String,String>();
		View qp = this.getView();
		if (fromDate != null)
			queryParameters.put("startEvent", new SimpleDateFormat("dd.MM.yyyy").format(fromDate));
		if (toDate != null)
			queryParameters.put("stopEvent", new SimpleDateFormat("dd.MM.yyyy").format(toDate));
		EditText organiser = (EditText) qp.findViewById(R.id.organiser_text);
		EditText description = (EditText) qp.findViewById(R.id.description_text);
		if (organiser.getText().length() > 0)
			queryParameters.put("eventOrganiser", organiser.getText().toString());
		if (description.getText().length() > 0)
			queryParameters.put("description", description.getText().toString());
		TextView fromDateView = (TextView) qp.findViewById(R.id.from_date);
		TextView toDateView = (TextView) qp.findViewById(R.id.to_date);
		
		// Clear previous content.
		fromDate = toDate = null;
		organiser.getText().clear();
		description.getText().clear();
		fromDateView.setText(""); toDateView.setText("");
		listView.setAdapter(null);
		
		// Run new query thread.
		Thread findEvents = new Thread(this);
        findEvents.start();
	}	
	
	class EventAdapter extends ArrayAdapter<Event> {
		
		private Context context;

		public EventAdapter(Context context) {
			super(context, R.layout.event_row, foundEvents);
			this.context = context;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.event_row, null);
			
			Button btn = (Button) row.findViewById(R.id.row_show_detail);
			btn.setId(position);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "Hello " + v.getId() + "!", Toast.LENGTH_SHORT).show();
				}
			});
			
			if (position < foundEvents.size()) {
				TextView organiser = (TextView) row.findViewById(R.id.row_organiser);
				organiser.setText(foundEvents.get(position).getEventOrganiser());
				TextView category = (TextView) row.findViewById(R.id.row_category);
				category.setText(foundEvents.get(position).getCategory());
				TextView startDate = (TextView) row.findViewById(R.id.row_start_date);
				startDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(foundEvents.get(position).getStartEvent()));
				TextView shortDescription = (TextView) row.findViewById(R.id.row_short_desc);
				shortDescription.setText(foundEvents.get(position).getShortDescription());
			}
			
			return row;
		}
	}
	
	Handler handler = new Handler()	{
		@Override
		public void handleMessage(Message msg)	{
			listView.setAdapter(new EventAdapter(SearchTab.this.getActivity()));
		}
	};

	@Override
	public void run() {
        RestConnection connection = new RestConnection(this.getActivity());
        connection.createClientResource();
        connection.addQueries(queryParameters);
        EventResource resource = connection.getEventResource();
        if (resource != null)	{
        	foundEvents = resource.findEvents();
            if (foundEvents != null)	{
            	handler.sendMessage(handler.obtainMessage());
            }
        }
	}
}
