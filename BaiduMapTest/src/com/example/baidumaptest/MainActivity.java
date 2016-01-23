package com.example.baidumaptest;

import baidumapsdk.demo.LocationDemo.MyLocationListenner;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
	
	private MapView mapView;
	BaiduMap mBaiduMap;
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView=(MapView)findViewById(R.id.map_view);
        mBaiduMap=mapView.getMap();
        
    }

    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	mapView.onDestroy();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	mapView.onResume();
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	mapView.onPause();
    }
    
}
