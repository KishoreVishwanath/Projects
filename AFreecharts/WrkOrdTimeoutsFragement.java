package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.ChartViews.TechLogInOutsLineChartView;
import info.androidhive.slidingmenu.ChartViews.WrkOrdTimeOutLineChartView;
import info.androidhive.slidingmenu.Data.MonitoringDataParser;
import info.androidhive.slidingmenu.model.TechnicianData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WrkOrdTimeoutsFragement extends Fragment{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_wrkordtimeouts,
				container, false);
		
		MonitoringDataParser parser = new MonitoringDataParser();
		TechnicianData td = null;
		Map<String,Integer> cto =null;
	

		try {
			InputStream raw = getActivity().getApplicationContext().getAssets()
					.open("Monitoring.XML");
			cto = parser.parseTMITrnxs(raw,"CLICK_TIME_OUT_RATE");
			raw.close();
			
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WrkOrdTimeOutLineChartView mView = new WrkOrdTimeOutLineChartView(getActivity()
				.getBaseContext(), cto);
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
