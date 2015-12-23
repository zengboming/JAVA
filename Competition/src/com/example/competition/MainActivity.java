package com.example.competition;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static int i=0; 
	public static int number=0;
	public List<String> data=new ArrayList<String>();
	
	//MyAdapter adapter=null;
	ArrayAdapter<String> adapter;
	
	private TextView text_id;
	private EditText edit_number;
	private EditText edit_name;
	private ListView list_member;
	private Button but_ok;
	private Button but_add;
	private Button but_generate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		text_id=(TextView)findViewById(R.id.text_id);
		edit_number=(EditText)findViewById(R.id.edit_number);
		edit_name=(EditText)findViewById(R.id.edit_name);
		but_ok=(Button)findViewById(R.id.button_ok);
		but_add=(Button)findViewById(R.id.button_add);
		but_generate=(Button)findViewById(R.id.button_generate);
		list_member=(ListView)findViewById(R.id.list_member);
		
		adapter=new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1,data);
		//adapter=new MyAdapter(MainActivity.this,R.layout.view_item, data);
		list_member.setAdapter(adapter);
		
		but_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String n=edit_number.getText().toString();
				if(n==null||n.equals("")||Integer.parseInt(n)<=0||Integer.parseInt(n)>100){
					Dialog2();
				}else{
					number=Integer.parseInt(n);
					//data=new String[number];
					Toast.makeText(MainActivity.this, "请输入"+number+"组成员名!",
							Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		
		but_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String n=edit_name.getText().toString();
				if(i!=0&&i>=number){
					Dialog();
				}else if(number<=0){
					Dialog2();
				}
				else{
					if(n==null||n.equals("")){
						Dialog1();
					}else{
						data.add(n);//用string[]就是不行。。。！！！！
						adapter.notifyDataSetChanged();
						//list_member.setSelection(data.size());
						i++;
						edit_name.setText("");
						text_id.setText(" "+(i+1)+" 号:");
						Toast.makeText(MainActivity.this,n+",添加成功！",
								Toast.LENGTH_SHORT).show();
						Log.e("TAG", "adapter notify ok ");
					}
				}
			}
		});
		
		but_generate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(number!=0&&data!=null&&i==number){
					Bundle bundle=new Bundle();
					bundle.putParcelableArrayList("list", (ArrayList)data);
					Intent intent=new Intent(MainActivity.this,Competition.class);
					intent.putExtras(bundle);
					intent.putExtra("number", number);
					startActivity(intent);
				}else{
					Dialog1();
				}
			}
		});	
	}


	protected void Dialog2() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("WARN!");
		dialog.setMessage("请输入参赛组数(0-100)!");
		dialog.setCancelable(false);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}


	protected void Dialog1() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("WARN!");
		dialog.setMessage("输入成员名不能为空，如果没有成员，请输入‘无'");
		dialog.setCancelable(false);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}


	public void Dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("WARN!");
		dialog.setMessage("人数超过"+number+"人!");
		dialog.setCancelable(false);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}
}
