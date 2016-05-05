package com.itheima.volley;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * �Ƶ��б�
 *
 */
public class HotelList extends HttpServlet {
	/**�������������Ϊ����ʾ��ҳ��������3ҳ����*/
	private static int DATA_MAX_SIZE = 30;
	/**ÿҳ����*/
	private static int PAGE_SIZE = 10;
	private String[] names;
	private String[] prices;
	private String[] imgs;
	private String hostAddress;

	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
			initData();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ҳ��
		String parameter = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(parameter);
		
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("ip=" + hostAddress);

		try {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			JSONArray jsonArray = new JSONArray();
			
			int startIndex = (pagenum - 1) * PAGE_SIZE;//��ʼλ��
			int endIndex = pagenum * PAGE_SIZE; //��ֹΪֹ
			if(endIndex > names.length) endIndex = names.length;
			
			for(int i = startIndex; i < endIndex; i++){
				JSONObject json = new JSONObject();
				json.put("name", names[i]);
				json.put("price", prices[i]);
				json.put("img", imgs[i]);
				
				
				jsonArray.put(json);
			}
			System.out.println("pagenum = " + pagenum);
			System.out.println("size = " + jsonArray.length());
			
			//Ϊ���ÿͻ���progressbar��ʾʱ�䳤һЩ������ǰ�����������ݻ��ǻ������ݣ�����sleep
			Thread.sleep(800);
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData(){
		//����
		names = new String[]{"��������ϵ�ǿմ�Ƶ�", "��������ʱ�оƵ������", "άҲ�ɾƵ����ڱ���ǰ��·��", "����ǰ�����ʾƵ�", "�������������Ƶ�",
				"���ڰ�˹���˾�Ʒ�Ƶ�", "���ڱ������ʾƵ�", "���ڱ�������Ƶ�", "���ڼ��ݽ�����Ƶ�", "�����������ݱ������ŵ�",
				"����Ȧ�������Ƶ�", "����ŵ����Ʒ�Ƶ�", "��ҿ�����ڱ����������վ��", "�����������ʾƵ�", "�����Ϲ�����Ƶ걦����¡�ǵ�",
				"��ͥ���ڱ������������Ƶ�", "���ڶ����ǳ�����վ�������վ��", "�����Ϻ�֮�ǾƵ걦������վ�����ͷ׳ǵ�", "����һ��õ����������Ƶ꽨����", "����һ��õ����������Ƶ꺣�ŵ�",
				"���ڹ�Ʒ���Ƶ�", "���ڻ�ͥ���ݾƵ�", "���ڹ�������Ƶ�", "���ڱ��þƵ�", "����Ҽ�Ź�Ԣ�Ƶ�", 
				"�����Ҹ���վ�Ƶ깫Ԣ����֮����", "�˺������Ƶ��������ʹ��", "���ڻ��е���һ�ξƵ깫Ԣ", "����yoyo�����ͥ��Ԣ", "���ڻ��ȳ���ϫ��Ʒ�Ƶ�",
				"��������˹�Ƶ�", "���ں�����˼͢�Ƶ�", "���ڿ���˹���Ƶ�", "���ڶ������������Ƶ�", "���б�ݾƵ������������վ��"};
		
		//�۸�
		prices = new String[]{"8888", "202", "271", "505", "291", 
				"192", "221", "372", "140", "138",
				"207", "220", "180", "389", "198",
				"208", "172", "138", "290", "350",
				"266", "204", "226", "262", "319",
				"448", "359", "398", "173", "1660",
				"1109", "568", "1817", "600", "179"};
		
		//ͼƬ·����ip��ַ+���·��
		imgs = new String[]{"http://himg.qunarzz.com/imgs/201212/06/nz078uvZz-60q8ylv120.jpg","http://himg.qunarzz.com/imgs/201506/03/JhS1_tJIavh3tOZAJ120.jpg","http://himg.qunarzz.com/imgs/201212/11/0x8gF00P-jWyPPtG0120.jpg", "http://himg.qunarzz.com/imgs/201212/06/nz078uvZswEwrW6pv120.jpg", "http://himg.qunarzz.com/imgs/201212/06/nz078uvZswEwrW6pv120.jpg",
				"http://himg.qunarzz.com/imgs/201108/10/Z7SfQZ9xCwKhGMUcZ120.jpg", "http://himg.qunarzz.com/imgs/201212/08/nz078uvZ5lytDaSuv120.jpg","http://himg.qunarzz.com/imgs/201602/29/C._M0DCiyXvCk6Mympi120.jpg","http://himg.qunarzz.com/imgs/201509/25/JhS1_thB9SVza42rJ120.jpg","http://himg.qunarzz.com/imgs/201502/27/cWOHdHHliwsu3RQaH120.jpg",
				"http://himg.qunarzz.com/imgs/201108/10/Z7SfHT9xCPbaqdJfZ120.jpg","http://himg.qunarzz.com/imgs/201304/11/JhS1_tJmaBWhoUnyJ120.jpg","http://himg.qunarzz.com/imgs/201308/26/Z7-ECT9-2j3DQYxsZ120.jpg","http://himg.qunarzz.com/imgs/201506/30/JhS1_th-lHjBcVrPJ120.jpg","http://himg.qunarzz.com/imgs/201212/05/nz078uvZkq1zkrfVv120.jpg",
				"http://himg.qunarzz.com/imgs/201203/07/Z7-ECTkr-9yCC6HtZ120.jpg", "http://himg.qunarzz.com/imgs/201311/08/JhS1_th_DvtOr9iIJ120.jpg", "http://himg.qunarzz.com/imgs/201212/07/nz078uvZy0Zqk2mWv120.jpg", "http://himg.qunarzz.com/imgs/201502/28/66I5P26ffvoN7l2v6120.jpg", "http://himg.qunarzz.com/imgs/201310/11/Z7-ECT9UFoG1q1KUZ120.jpg",
				"http://himg.qunarzz.com/imgs/201503/17/HJI_JODRu3_Ml8TGD120.jpg", "http://himg.qunarzz.com/imgs/201210/31/Z7-ECTZaSjnpZJTOZ120.jpg", "http://himg.qunarzz.com/imgs/201102/19/kkQTtUkmZTNLCEI2k120.jpg", "http://himg.qunarzz.com/imgs/201305/29/JhS1_tJFZ-FstAXVJ120.jpg", "http://himg.qunarzz.com/imgs/201504/28/JhS1_tJz_CQDC8vvJ120.jpg",
				"http://himg.qunarzz.com/imgs/201304/12/JhS1_tJmvgjzqpwMJ120.jpg", "http://himg.qunarzz.com/imgs/201404/03/Z7-ECTkmWzayTsNkZ120.jpg", "http://himg.qunarzz.com/imgs/201412/31/Z7-ECTZWfMvUQyQCZ120.jpg", "http://himg.qunarzz.com/imgs/201511/15/C._M0DCifhUj6miJzgi120.jpg", "http://himg.qunarzz.com/imgs/201412/25/JhS1_tJGjpyLZ_5NJ120.jpg",
				"http://himg.qunarzz.com/imgs/201402/13/JhS1_t5M4NKIiqJNJ120.jpg", "http://himg.qunarzz.com/imgs/201510/09/nYBVfHLlu4ull_sEr120.jpg", "http://himg.qunarzz.com/imgs/201507/07/UfH3z9uP9nh1N31d9120.jpg", "http://himg.qunarzz.com/imgs/201305/03/JhS1_tJUCBZCv0JMJ120.jpg", "http://himg.qunarzz.com/imgs/201511/27/JhS1_th3kDv2PwY5J120.jpg"};
		
		
	}

}
