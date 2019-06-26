package org.crazyit.auction.client;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
public class Main extends Activity
{
	ListView mainMenu;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainMenu = (ListView) findViewById(R.id.mainMenu);
		mainMenu.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			{
				Intent intent = null;
				switch ((int) id)
				{
					case 0:
						intent = new Intent(Main.this, ViewItem.class);
						intent.putExtra("action", "viewSucc.jsp");
						startActivity(intent);
						break;
					// ���������Ʒ
					case 1:
						// ����ViewItem Activity
						intent = new Intent(Main.this, ViewItem.class);
						// action����Ϊ�����Servlet��URL��
						intent.putExtra("action", "viewFail.jsp");
						startActivity(intent);
						break;
					// ������Ʒ����
					case 2:
						// ����ManageKind Activity
						intent = new Intent(Main.this, ManageKind.class);
						startActivity(intent);
						break;
					// ������Ʒ
					case 3:
						// ����ManageItem Activity
						intent = new Intent(Main.this, ManageItem.class);
						startActivity(intent);
						break;
					// ���������Ʒ��ѡ����Ʒ���ࣩ
					case 4:
						// ����ChooseKind Activity
						intent = new Intent(Main.this, ChooseKind.class);
						startActivity(intent);
						break;
					// �鿴�Լ��ľ���
					case 5:
						// ����ViewBid Activity
						intent = new Intent(Main.this, ViewBid.class);
						startActivity(intent);
						break;
				}
			}
		});
	}
}