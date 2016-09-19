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

public class WrkOrdSummaryFragement extends Fragment{
	
	Map<String,Integer> tsc =null;
	Map<String,Integer> tec =null;
	Map<String,Integer> ts =null;
	Map<String,Integer> tp =null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_wrkordsummary,
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
			tsc = parser.parseTMITrnxs(raw,"BACKLOG_POC");
			raw.close();
			
			InputStream raw1 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp = parser.parseTMITrnxs(raw1,"COMPLETED_1H_POC");
			raw1.close();
			tsc.put("1HCOUNT", temp.get("COUNT"));
			
			InputStream raw2 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp1 = parser.parseTMITrnxs(raw2,"COMPLETED_TODAY_POC");
			raw2.close();
			tsc.put("TCOUNT", temp1.get("COUNT"));
			
			/*InputStream raw3 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tec = parser.parseTMITrnxs(raw3,"JOB_PROCESSING_POC");
			raw3.close();*/
			
			InputStream raw11 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tec = parser.parseTMITrnxs(raw11,"JOBCREATION_30MIN_COUNT");
			raw11.close();
			
			InputStream raw12 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp12 = parser.parseTMITrnxs(raw12,"JOBCREATION_1H_COUNT");
			raw12.close();
			tec.put("60MCOUNT", temp12.get("COUNT"));
			
			InputStream raw13 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp13 = parser.parseTMITrnxs(raw13,"JOBCREATION_6H_COUNT");
			raw13.close();
			tec.put("6HCOUNT", temp13.get("COUNT"));
			
			InputStream raw14 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp14 = parser.parseTMITrnxs(raw14,"JOBCREATION_24H_COUNT");
			raw14.close();
			tec.put("24HCOUNT", temp14.get("COUNT"));
			
			InputStream raw15 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			Map<String,Integer> temp15 = parser.parseTMITrnxs(raw15,"JOBCREATION_TODAY_COUNT");
			raw14.close();
			tec.put("TODAYCOUNT", temp15.get("COUNT"));
			
			/*InputStream raw4 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			ts = parser.parseTMITrnxs(raw4,"GET_LATEST_JOB_AND_TIME");
			raw4.close();*/
			
			/*InputStream raw5 = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			tp = parser.parseTMITrnxs(raw3,"JOBCREATION_24H_COUNT");
			raw3.close();*/
			
						
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
		textview.setText("LAST ONE HOUR");
		textview1 = (TextView) getView().findViewById(R.id.textView112);
		textview1.setText(String.valueOf(tsc.get("COUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView121);
		textview.setText("TODAY");
		textview1 = (TextView) getView().findViewById(R.id.textView122);
		textview1.setText(String.valueOf(tsc.get("1HCOUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView131);
		textview.setText("Backlog For Today");
		textview1 = (TextView) getView().findViewById(R.id.textView132);
		textview1.setText(String.valueOf(tsc.get("TCOUNT")));
		
/*		String s = ts.keySet().toArray()[0].toString();
		textview = (TextView) getView().findViewById(R.id.textView211);
		textview.setText(s);
		textview1 = (TextView) getView().findViewById(R.id.textView212);
		textview1.setText(String.valueOf(ts.get(s)));*/
		
/*		textview = (TextView) getView().findViewById(R.id.textView221);
		textview.setText("PROXY");
		textview1 = (TextView) getView().findViewById(R.id.textView222);
		textview1.setText(String.valueOf(tp.get("COUNT")));*/
		
		
		
		textview = (TextView) getView().findViewById(R.id.textView311);
		textview.setText("30 MIN");
		textview1 = (TextView) getView().findViewById(R.id.textView312);
		textview1.setText(String.valueOf(tec.get("COUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView321);
		textview.setText("60 MIN");
		textview1 = (TextView) getView().findViewById(R.id.textView322);
		textview1.setText(String.valueOf(tec.get("60MCOUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView331);
		textview.setText("6 HR");
		textview1 = (TextView) getView().findViewById(R.id.textView332);
		textview1.setText(String.valueOf(tec.get("6HCOUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView341);
		textview.setText("24 HR");
		textview1 = (TextView) getView().findViewById(R.id.textView342);
		textview1.setText(String.valueOf(tec.get("24HCOUNT")));
		
		textview = (TextView) getView().findViewById(R.id.textView351);
		textview.setText("TODAY");
		textview1 = (TextView) getView().findViewById(R.id.textView352);
		textview1.setText(String.valueOf(tec.get("TODAYCOUNT")));
		

		
	}

}
