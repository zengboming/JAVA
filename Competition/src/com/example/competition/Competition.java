package com.example.competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Competition extends Activity {
	
	private List<Integer> id_list=new ArrayList<Integer>();//随机数列
	private int MAXROUND=0;
	private List<String> list1=new ArrayList<String>();//随机名单
	private ListView list_comp;
	private Button but_choose;
	private TextView text_win;
	private ArrayAdapter<String> adapter;
	private String win="获胜名单:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.competition_layout);
		list_comp=(ListView)findViewById(R.id.list_comp);
		but_choose=(Button)findViewById(R.id.button_choose);
		text_win=(TextView)findViewById(R.id.text_win);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		ArrayList data1=bundle.getParcelableArrayList("list");
		List<String> list=data1;
		int num_1=intent.getIntExtra("number", 0);
		if(num_1%2==1){
			num_1++;
			list.add("无");
		}
		/*for(int j=0;j<list.size();j++){
			Log.e("TAG",list.get(j)+"");
		}*/
		
		MAXROUND=num_1;
		Log.e("TAG",MAXROUND+"");
		id_list=getRandom();
		Log.e("TAG","will foreach");
		int j=0;
		for (int e : id_list) {
			String s="";
			if(j%2==0){
				String n="第"+(j/2+1)+"组:";
				s+=n;
			}else{
				s+="          :";
			}
			j++;
			s+=list.get(e);
			list1.add(s);
		}
		
		adapter=new ArrayAdapter<String>(Competition.this,
				android.R.layout.simple_list_item_1,list1);
		//adapter=new ArrayAdapter<String>(Competition.this,
		//		R.layout.list_item,list1);
		list_comp.setAdapter(adapter);
		
		list_comp.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name=list1.get(position);
				String[] temp=name.split(":");
				win=win+temp[1]+". ";
			}
			
		});
		
		but_choose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text_win.setText(win);
			}
		});
	}
	
	private List<Integer> getRandom() {
		// TODO Auto-generated method stub
		List<Integer> list1=new ArrayList<Integer>();
		Random random=new Random();
		for(int i=0;i<100;i++){
			if(list1.size()<=MAXROUND){
				int randomInt=random.nextInt(MAXROUND);
				if(!list1.contains(randomInt)){
					list1.add(randomInt);
				}
			}
		}
		return list1;
	}
	
}