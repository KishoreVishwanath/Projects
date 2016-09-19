package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.ChartViews.TechTrnxsBarChartView;
import info.androidhive.slidingmenu.ChartViews.WrkOrdJobCrtHistoryBarChartView;
import info.androidhive.slidingmenu.ChartViews.WrkOrdJobIncompletionBarChartView;
import info.androidhive.slidingmenu.Data.MonitoringDataParser;
import info.androidhive.slidingmenu.model.TechnicianData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WrkOrdJobCrtHistoryFragement extends Fragment{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_wrkordjobcrthistory,
				container, false);
		
		MonitoringDataParser parser = new MonitoringDataParser();
		TechnicianData td = null;
		Map<String,Integer> succ =null;
		Map<String,Integer> fail =null;

		try {
			InputStream raw = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			succ = parser.parseTMITrnxs(raw,"JOB_CREATION_HISTORY_POC");
			raw.close();
			
					
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WrkOrdJobCrtHistoryBarChartView mView = new WrkOrdJobCrtHistoryBarChartView(getActivity()
				.getBaseContext(), succ);
		container.addView(mView);
		
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
	}
}
