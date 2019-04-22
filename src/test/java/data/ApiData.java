/**
 * @author helen
 * @date 2018年9月25日
 */
package data;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import common.BaseData;
import common.MyConfig;
import net.sf.json.JSONObject;

/**
 * @Description:通过API处理数据
 */
public class ApiData {
	BaseData baseData = new BaseData();
	MemberData memberData = new MemberData();
	CouponData couponData = new CouponData();
	MyConfig myConfig = new MyConfig();
	CloseableHttpClient httpCilent = HttpClients.createDefault();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApiData apiData = new ApiData();
		apiData.getCoupon("helen", 12);
	}

	/*
	 * 新增满减券，指定发放会员
	 * 
	 * @couponName 优惠券名称
	 * 
	 * @discounts 面值
	 */
	public JSONObject addCoupon(JSONObject userdata, String userIds, String discounts) {
		JSONObject params = new JSONObject();
		params.put("couponType", "2");// 优惠券类型：2满减券
		params.put("totalNum", "2");// 发放总数
		params.put("deductionType", "3");//抵扣类型 1.仅抵扣现金 2.仅抵扣福豆 3.优先抵扣福豆 
		params.put("isUseThreshold", "0");// 使用门槛：0无门槛
		params.put("thresholdValue", "null");//
		params.put("couponUseScop", "1");
		params.put("useGoodsList", "null");
		params.put("useGoodsTypeId", "null");
		params.put("effectTime", baseData.getTimeStamp(-1));// 有效期开始时间:前一天
		params.put("endTime", baseData.getTimeStamp(1));// 有效期结束时间：后一天
		params.put("couponGiveScope", "1");
		params.put("giveGoodsList", "null");
		params.put("giveGoodsTypeId", "null");
		params.put("createById", "1119");// 用户UID
		params.put("name", "商城下单使用的满减券" + baseData.getNum(1, 9999));// 优惠券名称
		params.put("discounts", discounts);// 面值
		params.put("objectType", "4");// 发放对象类型：1.全部会员、2.指定条件、3.指定标签、4.指定会员
		params.put("objectValue", userIds);//多个userId,用“,”隔开
		JSONObject result = this.sysPost(userdata, "/marketingCenter/coupon/manager/add", params);
		return result;
	}

	/*
	 * 审核优惠券
	 * 
	 * @couponId 优惠券ID
	 */
	public JSONObject auditCoupon(JSONObject userdata, String couponId) {

		JSONObject params = new JSONObject();
		params.put("auditStatus", "3");// 审核状态－3（通过）
		params.put("createById", userdata.getString("userId"));// 审核人ID
		params.put("couponId", couponId);// 优惠券ID
		JSONObject result = this.sysPost(userdata, "/marketingCenter/coupon/manager/audit", params);
		return result;
	}

	/*
	 * 发放优惠券
	 * 
	 * @couponId 优惠券ID
	 */
	public JSONObject sendCoupon(JSONObject userdata, String couponId) {
		JSONObject params = new JSONObject();
		params.put("createById", userdata.getString("userId"));
		params.put("couponId", couponId);
		JSONObject result = this.sysPost(userdata, "/marketingCenter/coupon/manager/sent", params);
		return result;
	}

	/*
	 * 获取优惠券：运营后台新增满优惠券(指定会员领取)－审核－发放
	 * 
	 * @account 用户账号
	 */
	public void getCoupon(String account, int discounts) {
		String userId = memberData.get_Id(account);
		// 登录
		JSONObject sysLoginResult = this.sysLogin("helen", "123456li");
		assertEquals(sysLoginResult.getString("code"), "10000", "运营中心登录" + sysLoginResult);
		JSONObject sysUser = sysLoginResult.getJSONObject("data");
		// 新增
		JSONObject addCouponResult = this.addCoupon(sysUser, userId, String.valueOf(discounts));
		assertEquals(addCouponResult.getString("code"), "10000", "新增优惠券" + addCouponResult);
		String couponId = couponData.getLastCouponId();// 优惠券ID
		// 审核
		JSONObject auditCouponResult = this.auditCoupon(sysUser, couponId);
		assertEquals(auditCouponResult.getString("code"), "10000", "审核优惠券" + auditCouponResult);
		// 发放
		JSONObject sendCouponResult = this.sendCoupon(sysUser, couponId);
		assertEquals(sendCouponResult.getString("code"), "10000", "发放优惠券" + sendCouponResult);
	}

	/*
	 * 运营中心，post请求
	 * 
	 * @uri 接口地址
	 * 
	 * @params json参数
	 */
	public JSONObject sysPost(JSONObject userdata, String uri, JSONObject params) {
		JSONObject jsonResult = new JSONObject();
		try {
			StringEntity entity = new StringEntity(params.toString(), "UTF-8");// 这里需要设置编码，否则会出现中文乱码
			String uuid = UUID.randomUUID().toString();
			HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(new URI(myConfig.getKeys("YY_domainName") + uri))
					.addHeader("Content-Type", "application/json")// 设置JSON格式
					.addHeader("channelId", "pc") // 渠道来源
					.addHeader("requestId", uuid) // 使用UUID,保证每次请求都是唯一
					.addHeader("timestamp", String.valueOf(System.currentTimeMillis() / 1000)) // 当前时间戳,
					.addHeader("userType", "backend").addHeader("token", userdata.getString("token"))
					.addHeader("loginId", userdata.getString("loginId"))
					.addHeader("Cookie",
							"token=" + userdata.getString("token") + "; loginId=" + userdata.getString("loginId"))
					.setEntity(entity) // 需要传的参数
					.build();
			HttpResponse httpResponse = httpCilent.execute(httpUriRequest);
			String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
			jsonResult = JSONObject.fromObject(strResult);

		} catch (Exception e) {
			System.out.println("执行 post接口失败");
			e.printStackTrace();
		}
		return jsonResult;
	}

	/* PC商城的携带登录信息的post请求公共方法 */
	public JSONObject PC_loginPost(JSONObject userdata, String uri, JSONObject params) {
		JSONObject jsonResult = new JSONObject();
		try {
			StringEntity entity = new StringEntity(params.toString(), "UTF-8");

			HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(new URI(myConfig.getKeys("PC_domainName") + uri))
					.addHeader("Content-Type", "application/json")// 设置JSON格式
					.addHeader("channelId", "pc") // 渠道来源
					.addHeader("requestId", "0c922fe9-f507-4314-8715-11b9b44c08tr") // 保证每次请求都是唯一
					.addHeader("timestamp", String.valueOf(System.currentTimeMillis() / 1000)) // 当前时间戳,
					.addHeader("loginId", userdata.getString("loginId")) // 用户登录ID
					.addHeader("token", userdata.getString("token")) // 登录校验
					.addHeader("userType", userdata.getString("userType")).setEntity(entity) // 需要传的参数
					.build();
			HttpResponse httpResponse = httpCilent.execute(httpUriRequest);
			String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
			jsonResult = JSONObject.fromObject(strResult);

			// 如果处理不成功，输出返回信息
			if ((jsonResult.get("code").toString()).equals("10000")) {
				Thread.sleep(1000);
			} else {
				System.out.println(jsonResult);
			}
		} catch (Exception e) {
			System.out.println("执行 post接口失败");
			e.printStackTrace();
		}
		return jsonResult;
	}

	/*
	 * PC商城登录
	 * 
	 * @userName 账号
	 * 
	 * @password 密码
	 */
	public JSONObject PCLogin(String userName, String password) {
		JSONObject jsonResult = new JSONObject();
		try {
			HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(new URI(myConfig.getKeys("PC_domainName") + "/login/userCenter/member"))
					.addParameter("account", userName).addParameter("password", baseData.getMD5(password)).build();
			HttpResponse httpResponse = httpCilent.execute(httpUriRequest);// , localContext);
			String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
			jsonResult = JSONObject.fromObject(strResult);
		} catch (Exception e) {
			System.out.println("PC商城登录失败");
			e.printStackTrace();
		}
		return jsonResult;
	}

	/* 运营中心登录 */
	public JSONObject sysLogin(String account, String password) {
		CommonData commonData = new CommonData();
		JSONObject params = new JSONObject();
		params.put("account", account);
		params.put("password", baseData.getMD5(password));
		
		JSONObject result = this.sysPost("/userCenter/user/login", params);
		if (result.getString("code").equals("60050")) {
			String mobile = commonData.getMUserValue(account, "mobile");
			JSONObject Cparams = new JSONObject();
			Cparams.put("account", mobile);//账号
			Cparams.put("type", "7");//消息类型
			this.sysPost("/userCenter/user/sendCode", Cparams);
			String Vcode = commonData.getVerificationCode_ByPhone(mobile);
			params.put("mobile", result.getString("data"));
			params.put("mobileCode", Vcode);
			result = this.sysPost("/userCenter/user/login", params);
		}
		//System.out.println(result);
		return result;
	}

	/*
	 * 运营中心，不携带登录信息的post请求
	 * 
	 * @uri 接口地址
	 * 
	 * @params json参数
	 */
	public JSONObject sysPost(String uri, JSONObject params) {
		JSONObject jsonResult = new JSONObject();
		try {
			StringEntity entity = new StringEntity(params.toString(), "UTF-8");// 这里需要设置编码，否则会出现中文乱码

			HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(new URI(myConfig.getKeys("YY_domainName") + uri))
					.addHeader("Content-Type", "application/json")// 设置JSON格式
					.addHeader("channelId", "pc") // 渠道来源
					.addHeader("requestId", "0c922fe9-f507-4314-8715-11b9b44c08tr") // 保证每次请求都是唯一
					.addHeader("timestamp", String.valueOf(System.currentTimeMillis() / 1000)) // 当前时间戳,
					.addHeader("userType", "backend").setEntity(entity) // 需要传的参数
					.build();
			HttpResponse httpResponse = httpCilent.execute(httpUriRequest);
			String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
			jsonResult = JSONObject.fromObject(strResult);

		} catch (Exception e) {
			System.out.println("执行 post接口失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	

}
