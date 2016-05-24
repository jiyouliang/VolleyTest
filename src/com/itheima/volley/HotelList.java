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
 * 酒店列表
 * 
 */
public class HotelList extends HttpServlet {
	/** 数据最大条数：为了演示分页，这里用3页数据 */
	private static int DATA_MAX_SIZE = 30;
	/** 每页条数 */
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
		// 页码
		String parameter = request.getParameter("pagenum");
		int pagenum = Integer.parseInt(parameter);

		response.setCharacterEncoding("UTF-8");

		System.out.println("ip=" + hostAddress);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < imgs.length; i++) {
			sb.append(names[i]);
		}
		System.out.println(sb.toString().getBytes().length);
		System.out.println("name size=" + names.length);
		System.out.println("imgs size=" + imgs.length);
		System.out.println("price size=" + prices.length);

		try {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();

			JSONArray jsonArray = new JSONArray();

			int startIndex = (pagenum - 1) * PAGE_SIZE;// 起始位置
			int endIndex = pagenum * PAGE_SIZE; // 终止为止
			if (endIndex > names.length)
				endIndex = names.length;

			for (int i = startIndex; i < endIndex; i++) {
				JSONObject json = new JSONObject();
				json.put("name", names[i]);
				json.put("price", prices[i]);
				json.put("img", imgs[i]);

				jsonArray.put(json);
			}
			System.out.println("pagenum = " + pagenum);
			System.out.println("size = " + jsonArray.length());

			// 为了让客户端progressbar显示时间长一些，区别当前加载网络数据还是缓存数据，这里sleep
			Thread.sleep(800);
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 名称
		names = new String[] { "三亚薇薇海岸精品客栈三亚湾店", "三亚阳光雨露风海景度假公寓",
				"三亚大好河山主题酒店大东海店", "三亚南田温泉好汉坡国际度假酒店", "三亚御海棠豪华精选度假酒店",
				"三亚香格里拉度假酒店", "三亚万丽度假酒店", "陵水清水湾哈利路亚海景度假酒店", "海南绿城蓝湾度假酒店",
				"三亚亚龙湾爱琴海建国度假酒店", "深圳银河系星空大酒店", "深圳米兰时尚酒店西乡店", "维也纳酒店深圳宝安前进路店",
				"深圳前岸国际酒店", "深圳中天美景酒店", "深圳艾斯威克精品酒店", "深圳本京国际酒店", "深圳宝晖商务酒店",
				"深圳佳逸捷商务酒店", "深圳翔联宾馆宝安海雅店", "深圳圈子艺术酒店", "深圳诺兰精品酒店",
				"如家快捷深圳宝安西乡地铁站店", "深圳御景国际酒店", "深圳南国明珠酒店宝安港隆城店", "汉庭深圳宝安西乡流塘酒店",
				"深圳叮当糖城市驿站宝体地铁站店", "深圳南海之星酒店宝安汽车站海雅缤纷城店", "深圳一誓玫瑰情侣主题酒店建安店",
				"深圳一誓玫瑰情侣主题酒店海雅店", "深圳桂品轩酒店", "深圳皇庭公馆酒店", "深圳国宾商务酒店", "深圳宝悦酒店",
				"深圳壹号公寓酒店", "深圳幸福驿站酒店公寓世界之窗店", "八号连锁酒店深圳世纪村店", "深圳黄尚到此一游酒店公寓",
				"深圳yoyo短租家庭公寓", "深圳华侨城蓝汐精品酒店", "深圳威尼斯酒店", "深圳海景奥思廷酒店",
				"深圳凯宾斯基酒店", "深圳东方银座美爵酒店", "城市便捷酒店深圳西乡地铁站店", "三亚金凤凰海景酒店",
				"三亚山海天万豪酒店", "三亚凤凰岛度假酒店", "三亚京海国际假日酒店三亚湾店",
				"三亚三亚湾银韵海景度假酒店凤凰机场店", "三亚湾留一间精品酒店凤凰机场店", "三亚银泰阳光度假酒店",
				"三亚亚龙湾华宇度假酒店", "金茂三亚亚龙湾希尔顿大酒店", "三亚海天大酒店大东海店", "三亚国光豪生度假酒店",
				"三亚文华东方酒店", "三亚鸿洲埃德瑞度假酒店", "三亚亚龙湾人间天堂鸟巢度假村", "三亚维景国际度假酒店",
				"三亚喜来登度假酒店", "三亚海韵度假酒店", "三亚黎客国际酒店", "三亚蜈支洲珊瑚酒店木屋",
				"三亚半山半岛洲际度假酒店", "三亚亚龙湾假日度假酒店", "三亚湾海居铂尔曼度假酒店", "三亚亚龙湾瑞吉度假酒店",
				"三亚湾皇冠假日度假酒店", "三亚海棠湾康莱德酒店", "三亚海棠湾9号度假酒店",
				"三亚市iHome度假公寓三亚湾兰海三期店", "三亚中心皇冠假日酒店", "三亚湾旅巢海景度假公寓三亚湾椰林海岸店",
				"三亚蜈支洲岛珊瑚酒店", "三亚湾红树林度假世界木棉酒店", "三亚唐拉雅秀酒店", "三亚太阳湾柏悦酒店",
				"锦江之星三亚国际购物中心海景酒店", "三亚哪里那里海岸客栈大东海店", "三亚天涯海角十步海岸国际青年旅舍" };

		// 图片路径：ip地址+相对路径
		imgs = new String[] {
				"http://himg.qunarzz.com/imgs/201412/11/JhS1_tJo43_UG4wPJ120.jpg",
				"http://himg.qunarzz.com/imgs/201508/30/JhS1_thZs6uG8611J120.jpg",
				"http://himg.qunarzz.com/imgs/201509/23/JhS1_thBbujTHi0SJ120.jpg",
				"http://himg.qunarzz.com/imgs/201410/25/GIGWX47MEIwYoKii7120.jpg",
				"http://himg.qunarzz.com/imgs/201303/25/JhS1_tJB7Rt2q0B6J120.jpg",
				"http://himg.qunarzz.com/imgs/201412/23/JhS1_tJqLkYU11FFJ120.jpg",
				"http://himg.qunarzz.com/imgs/201605/18/66I5P2-z5lB9NY8D6120.jpg",
				"http://himg.qunarzz.com/imgs/201503/27/Z7-ECTZutCl-xjUfZ120.jpg",
				"http://himg.qunarzz.com/imgs/201408/05/JhS1_t5kGlGw-HAOJ120.jpg",
				"http://himg.qunarzz.com/imgs/201501/05/FARatFFOfDZ0aWhcF120.jpg",
				"http://himg.qunarzz.com/imgs/201212/06/nz078uvZz-60q8ylv120.jpg",
				"http://himg.qunarzz.com/imgs/201506/03/JhS1_tJIavh3tOZAJ120.jpg",
				"http://himg.qunarzz.com/imgs/201212/11/0x8gF00P-jWyPPtG0120.jpg",
				"http://himg.qunarzz.com/imgs/201510/14/JhS1_th_c0NXql2aJ120.jpg",
				"http://himg.qunarzz.com/imgs/201212/06/nz078uvZswEwrW6pv120.jpg",
				"http://himg.qunarzz.com/imgs/201108/10/Z7SfQZ9xCwKhGMUcZ120.jpg",
				"http://himg.qunarzz.com/imgs/201212/08/nz078uvZ5lytDaSuv120.jpg",
				"http://himg.qunarzz.com/imgs/201602/29/C._M0DCiyXvCk6Mympi120.jpg",
				"http://himg.qunarzz.com/imgs/201509/25/JhS1_thB9SVza42rJ120.jpg",
				"http://himg.qunarzz.com/imgs/201502/27/cWOHdHHliwsu3RQaH120.jpg",
				"http://himg.qunarzz.com/imgs/201108/10/Z7SfHT9xCPbaqdJfZ120.jpg",
				"http://himg.qunarzz.com/imgs/201304/11/JhS1_tJmaBWhoUnyJ120.jpg",
				"http://himg.qunarzz.com/imgs/201308/26/Z7-ECT9-2j3DQYxsZ120.jpg",
				"http://himg.qunarzz.com/imgs/201506/30/JhS1_th-lHjBcVrPJ120.jpg",
				"http://himg.qunarzz.com/imgs/201212/05/nz078uvZkq1zkrfVv120.jpg",
				"http://himg.qunarzz.com/imgs/201203/07/Z7-ECTkr-9yCC6HtZ120.jpg",
				"http://himg.qunarzz.com/imgs/201311/08/JhS1_th_DvtOr9iIJ120.jpg",
				"http://himg.qunarzz.com/imgs/201212/07/nz078uvZy0Zqk2mWv120.jpg",
				"http://himg.qunarzz.com/imgs/201502/28/66I5P26ffvoN7l2v6120.jpg",
				"http://himg.qunarzz.com/imgs/201310/11/Z7-ECT9UFoG1q1KUZ120.jpg",
				"http://himg.qunarzz.com/imgs/201503/17/HJI_JODRu3_Ml8TGD120.jpg",
				"http://himg.qunarzz.com/imgs/201210/31/Z7-ECTZaSjnpZJTOZ120.jpg",
				"http://himg.qunarzz.com/imgs/201102/19/kkQTtUkmZTNLCEI2k120.jpg",
				"http://himg.qunarzz.com/imgs/201305/29/JhS1_tJFZ-FstAXVJ120.jpg",
				"http://himg.qunarzz.com/imgs/201504/28/JhS1_tJz_CQDC8vvJ120.jpg",
				"http://himg.qunarzz.com/imgs/201304/12/JhS1_tJmvgjzqpwMJ120.jpg",
				"http://himg.qunarzz.com/imgs/201404/03/Z7-ECTkmWzayTsNkZ120.jpg",
				"http://himg.qunarzz.com/imgs/201412/31/Z7-ECTZWfMvUQyQCZ120.jpg",
				"http://himg.qunarzz.com/imgs/201511/15/C._M0DCifhUj6miJzgi120.jpg",
				"http://himg.qunarzz.com/imgs/201412/25/JhS1_tJGjpyLZ_5NJ120.jpg",
				"http://himg.qunarzz.com/imgs/201402/13/JhS1_t5M4NKIiqJNJ120.jpg",
				"http://himg.qunarzz.com/imgs/201510/09/nYBVfHLlu4ull_sEr120.jpg",
				"http://himg.qunarzz.com/imgs/201507/07/UfH3z9uP9nh1N31d9120.jpg",
				"http://himg.qunarzz.com/imgs/201305/03/JhS1_tJUCBZCv0JMJ120.jpg",
				"http://himg.qunarzz.com/imgs/201511/27/JhS1_th3kDv2PwY5J120.jpg",
				"http://himg.qunarzz.com/imgs/201601/04/JhS1_t5J9sZb2lwCJ120.jpg",
				"http://himg.qunarzz.com/imgs/201512/16/JhS1_thkribXcYWGJ120.jpg",
				"http://himg.qunarzz.com/imgs/201502/03/JhS1_tJZrOMZKnPIJ120.jpg",
				"http://himg.qunarzz.com/imgs/201509/02/JhS1_thEm6YyK7vFJ120.jpg",
				"http://himg.qunarzz.com/imgs/201507/02/JhS1_thQcUnGbakCJ120.jpg",
				"http://himg.qunarzz.com/imgs/201602/14/66I5P2-hXMVE0X-m6120.jpg",
				"http://himg.qunarzz.com/imgs/201409/15/JhS1_tJTKh6bGFiXJ120.jpg",
				"http://himg.qunarzz.com/imgs/201405/01/JhS1_t5R3LY3VbhdJ120.jpg",
				"http://himg.qunarzz.com/imgs/201107/15/uz8PuUzrfq4_8Ktbu120.jpg",
				"http://himg.qunarzz.com/imgs/201401/15/E7FJ-ywAorJK5OySE120.jpg",
				"http://himg.qunarzz.com/imgs/201410/19/GIGWX47JcK3uvb9i7120.jpg",
				"http://himg.qunarzz.com/imgs/201207/26/C.JhS1_tJpjwTOProUJ120.jpg",
				"http://himg.qunarzz.com/imgs/201106/02/187IHQ8bZ4mtjFTB1120.jpg",
				"http://himg.qunarzz.com/imgs/201406/25/Z7-ECTkOO_WLC8AcZ120.jpg",
				"http://himg.qunarzz.com/imgs/201209/11/C.JhS1_tJVfLxrdmUHJ120.jpg",
				"http://himg.qunarzz.com/imgs/201411/05/CHmEv2AEmtxGFNrPA120.jpg",
				"http://himg.qunarzz.com/imgs/201511/24/JhS1_th35uvrUkqTJ120.jpg",
				"http://himg.qunarzz.com/imgs/201503/12/66I5P26AVX1w1WHV6120.jpg",
				"http://himg.qunarzz.com/imgs/201209/02/_L3Yt8_YbEnNMrFY_120.jpg",
				"http://himg.qunarzz.com/imgs/201404/30/CHmfG20UDcBnu4VyA120.jpg",
				"http://himg.qunarzz.com/imgs/201508/05/JhS1_thHv9n1o8C6J120.jpg",
				"http://himg.qunarzz.com/imgs/201302/25/JhS1_tJ8xzh7i56OJ120.jpg",
				"http://himg.qunarzz.com/imgs/201509/23/C._M0DCifGeOVwsuBYi120.jpg",
				"http://himg.qunarzz.com/imgs/201404/17/JhS1_t58NZoS8M5jJ120.jpg",
				"http://himg.qunarzz.com/imgs/201202/03/JhS1_t54pFoBpgiKJ120.jpg",
				"http://himg.qunarzz.com/imgs/201509/15/JhS1_thjBSvSY2NGJ120.jpg",
				"http://himg.qunarzz.com/imgs/201503/06/JhS1_tJiH_kspjo1J120.jpg",
				"http://himg.qunarzz.com/imgs/201510/19/JhS1_thahsnBH1noJ120.jpg",
				"http://himg.qunarzz.com/imgs/201410/29/Z7-ECTZM5w0QHcFnZ120.jpg",
				"http://himg.qunarzz.com/imgs/201410/19/GIGWX47Jc26ukgPz7120.jpg",
				"http://himg.qunarzz.com/imgs/201503/26/66I5P26j2ZPcF0116120.jpg",
				"http://himg.qunarzz.com/imgs/201408/05/JhS1_t5kGR7vB-msJ120.jpg",
				"http://himg.qunarzz.com/imgs/201504/13/JhS1_tJrJCggqgn9J120.jpg",
				"http://himg.qunarzz.com/imgs/201511/04/JhS1_thUkym0JCHsJ120.jpg",
				"http://himg.qunarzz.com/imgs/201409/04/66gppi6g1YK0Jl146120.jpg",
				"http://himg.qunarzz.com/imgs/201509/27/66I5P25uM3Hbjo8h6120.jpg" };

		// 价格
		prices = new String[] { "83", "138", "90", "282", "810", "631", "609",
				"178", "511", "830", "8888", "202", "271", "505", "291", "192",
				"221", "372", "140", "138", "207", "220", "180", "389", "198",
				"208", "172", "138", "290", "350", "266", "204", "226", "262",
				"319", "448", "359", "398", "173", "1660", "1109", "568",
				"1817", "600", "179", "298", "500", "479", "308", "226", "117",
				"520", "566", "988", "425", "498", "357", "318", "471", "738",
				"798", "620", "306", "888", "801", "605", "767", "1171", "495",
				"595", "504", "178", "407", "136", "958", "422", "440", "1428",
				"136", "159", "40" };
	}

}
