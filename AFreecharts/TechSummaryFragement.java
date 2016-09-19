package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.Data.MonitoringDataParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TechSummaryFragement extends Fragment{
	
	Map<String,Integer> tsc =null;
	Map<String,Integer> tec =null;
	Map<String,Integer> ts =null;
	Map<String,Integer> tp =null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_techsummary,
				container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		MonitoringDataParser parser = new MonitoringDataParser();


		try {
			InputStream raw = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tsc = parser.parseTMITrnxs(raw,"TMI_TECH_STATUS_COUNT");
			raw.close();
			
			InputStream raw1 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tec = parser.parseTMITrnxs(raw1,"TMI_TECH_ESPP_STATUS");
			raw1.close();
			
			InputStream raw2 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			ts = parser.parseTMITrnxs(raw2,"TECH_SELF");
			raw2.close();
			
			InputStream raw3 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tp = parser.parseTMITrnxs(raw3,"TECH_PROXY");
			raw3.close();
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showData();
	}
	
	
	private void showData() {
		TextView textview;
		TextView textview1;
		
		textview = (TextView) getView().findViewById(R.id.textView111);
		textview.setText("AVAILABLE");
		textview1 = (TextView) getView().findViewById(R.id.textView112);
		textview1.setText(String.valueOf(tsc.get("AVAILABLE")));
		
		textview = (TextView) getView().findViewById(R.id.textView121);
		textview.setText("DISPATCHED");
		textview1 = (TextView) getView().findViewById(R.id.textView122);
		textview1.setText(String.valueOf(tsc.get("DISPATCHED")));
		
		textview = (TextView) getView().findViewById(R.id.textView131);
		textview.setText("END_OF_SHIFT");
		textview1 = (TextView) getView().findViewById(R.id.textView132);
		textview1.setText(String.valueOf(tsc.get("END_OF_SHIFT")));
		
		
		textview = (TextView) getView().findViewById(R.id.textView211);
		textview.setText("SELF");
		textview1 = (TextView) getView().findViewById(R.id.textView212);
		textview1.setText(String.valueOf(ts.get("COUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView221);
		textview.setText("PROXY");
		textview1 = (TextView) getView().findViewById(R.id.textView222);
		textview1.setText(String.valueOf(tp.get("COUNT")));
		
		
		
		textview = (TextView) getView().findViewById(R.id.textView311);
		textview.setText("OK");
		textview1 = (TextView) getView().findViewById(R.id.textView312);
		textview1.setText(String.valueOf(tec.get("OK")));
		
		textview = (TextView) getView().findViewById(R.id.textView321);
		textview.setText("LATE");
		textview1 = (TextView) getView().findViewById(R.id.textView322);
		textview1.setText(String.valueOf(tec.get("LATE")));
		
		textview = (TextView) getView().findViewById(R.id.textView331);
		textview.setText("MISSING");
		textview1 = (TextView) getView().findViewById(R.id.textView332);
		textview1.setText(String.valueOf(tec.get("MISSING")));
		

		
	}

}
