package org.crazyit.auction.client;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.auction.client.util.DialogUtil;
import org.crazyit.auction.client.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class AddItem extends Activity
{
	EditText itemName, itemDesc,itemRemark,initPrice;
	Spinner itemKind , availTime;
	Button bnAdd, bnCancel;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item);
		itemName = (EditText) findViewById(R.id.itemName);
		itemDesc = (EditText) findViewById(R.id.itemDesc);
		itemRemark = (EditText) findViewById(R.id.itemRemark);
		initPrice = (EditText) findViewById(R.id.initPrice);
		itemKind = (Spinner) findViewById(R.id.itemKind);
		availTime = (Spinner) findViewById(R.id.availTime);
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		JSONArray jsonArray = null;
		try
		{
			jsonArray = new JSONArray(HttpUtil.getRequest(url));
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		JSONArrayAdapter adapter = new JSONArrayAdapter(this , jsonArray , "kindName" , false);
		itemKind.setAdapter(adapter);
		bnAdd = (Button) findViewById(R.id.bnAdd);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		bnCancel.setOnClickListener(new FinishListener(this));
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (validate())
				{
					String name = itemName.getText().toString();
					String desc = itemDesc.getText().toString();
					String remark = itemRemark.getText().toString();
					String price = initPrice.getText().toString();
					JSONObject kind = (JSONObject) itemKind.getSelectedItem();
					int avail = availTime.getSelectedItemPosition();
					switch(avail)
					{
						case 5 :
							avail = 7;
							break;
						case 6 :
							avail = 30;
							break;
						default :
							avail += 1;
							break;
					}
					try
					{
						String result = addItem(name, desc
							, remark , price , kind.getInt("id") , avail);
						DialogUtil.showDialog(AddItem.this
							, result , true);
					}
					catch (Exception e)
					{
						DialogUtil.showDialog(AddItem.this
							, "。。。。。" , false);
						e.printStackTrace();
					}
				}
			}
		});
	}
	private boolean validate()
	{
		String name = itemName.getText().toString().trim();
		if (name.equals(""))
		{
			DialogUtil.showDialog(this , "��Ʒ�����Ǳ����" , false);
			return false;
		}
		String price = initPrice.getText().toString().trim();
		if (price.equals(""))
		{
			DialogUtil.showDialog(this , "���ļ۸��Ǳ����" , false);
			return false;
		}
		try 
		{
			Double.parseDouble(price);
		}
		catch(NumberFormatException e)
		{
			DialogUtil.showDialog(this , "���ļ۸��������ֵ��" , false);
			return false;
		}
		return true;
	}

	private String addItem(String name, String desc
		, String remark , String initPrice , int kindId , int availTime)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String , String> map = new HashMap<String, String>();
		map.put("itemName" , name);
		map.put("itemDesc" , desc);
		map.put("itemRemark" , remark);
		map.put("initPrice" , initPrice);
		map.put("kindId" , kindId + "");
		map.put("availTime" , availTime + "");
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "addItem.jsp";
		// ��������
		return HttpUtil.postRequest(url , map);
	}
}