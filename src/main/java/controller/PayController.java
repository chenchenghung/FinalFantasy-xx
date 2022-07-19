package controller;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

@Controller
public class PayController {
	
	public static AllInOne all;
	
	@RequestMapping(
			path= {"/ecpay/genorder"}
			,produces="application/json;charset=utf-8"
			,method=RequestMethod.POST
			)
	@ResponseBody
	public String genorder(@RequestBody String data) {
		System.out.println("genorder:"+data);
		System.out.println("genorder:"+data.toString());
		JSONObject js=new JSONObject();
		try {
			all=new AllInOne("");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
//		obj.setMerchantTradeNo("testCompany0008");
		obj.setMerchantTradeNo("eePay123456785");
		obj.setMerchantTradeDate("2022/01/01 08:05:23");
		obj.setTotalAmount("500");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("https://b692-112-105-38-137.jp.ngrok.io/javaweb-mvc-finalfantasy/ecpay/callback");
		obj.setOrderResultURL("https://b692-112-105-38-137.jp.ngrok.io/javaweb-mvc-finalfantasy/pages/products.view");
		obj.setNeedExtraPaidInfo("N");
		obj.setRedeem("Y");
		String form = all.aioCheckOut(obj, null);
		System.out.println(form);

		
		
		return form;
		
	}
	
	@RequestMapping(
			path= {"/ecpay/callback"}
			,produces="application/json;charset=utf-8"
			,method=RequestMethod.POST
			)
	@ResponseBody
	public String callback(@RequestBody String data ) {
		System.out.println("callback: ");
		System.out.println(data);
		System.out.println(data.toString());


		
		
		return "";
		
	}
	
}
