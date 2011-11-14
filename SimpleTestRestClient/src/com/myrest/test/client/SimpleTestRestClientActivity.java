package com.myrest.test.client;

import org.restlet.resource.ClientResource;

import com.myrestlet.test.data.StringData;
import com.myrestlet.test.data.StringResource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.Color;

public class SimpleTestRestClientActivity extends Activity implements OnClickListener {
	
	private ClientResource clientResource = null;
	private StringResource stringResource = null;
	private String urlGAE = "http://0a.events-searcher.appspot.com/stringresource";	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button readBtn = (Button) findViewById(R.id.readBtn);
        readBtn.setId(0);
        readBtn.setOnClickListener(this);    
        Button writeBtn = (Button) findViewById(R.id.writeBtn);
        writeBtn.setId(1);
        writeBtn.setOnClickListener(this);    
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setId(2);
        deleteBtn.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		createClientResource(urlGAE);
		EditText editText = (EditText) findViewById(R.id.textData);
		EditText editTextStringId = (EditText) findViewById(R.id.stringId);
		if (stringResource != null)	{
			try {	
				String text = editText.getText().toString();
				String stringId = editTextStringId.getText().toString();
				switch (v.getId())	{
				case 0:
					// Read from GAE.
					clientResource.addQueryParameter("stringId", stringId);
					editText.setText(stringResource.getString());
					break;
				case 1:
					// Write to GAE.
					stringResource.setString(new StringData(Long.parseLong(stringId),text));
					break;
				case 2:
					// Remove from GAE.
					clientResource.addQueryParameter("stringId", stringId);
					stringResource.removeString();
					break;
				}
				editText.setTextColor(Color.GREEN);
			} catch (Exception e)	{
				editText.setText(e.getMessage());
				editText.setTextColor(Color.RED);
			}
		} else {
			editText.setTextColor(Color.YELLOW);
			editText.setText("Connection unavailable!");
		}
	}
	
	public void createClientResource(String url)	{
    	System.setProperty("java.net.preferIPv6Addresses", "false");
		try {
			clientResource = new ClientResource(url);
			clientResource.setRequestEntityBuffering(true);	// Workaround GAE bug.
			stringResource = clientResource.wrap(StringResource.class);
		} catch (Exception e) {
			clientResource = null; stringResource = null;
        	Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }       			
	}
}
