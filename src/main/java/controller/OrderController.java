package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.CreateServerOrderObj;
import model.CustomerBean;
import model.OrderitemBean;
import model.OrdersBean;
import model.OrdersService;
import model.ProductBean;
import model.ProductService;


@CrossOrigin
@SessionAttributes(value= {"cart","carList","user"})
@Controller
public class OrderController {
	@PersistenceContext
	private Session session;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersService ordersService;


	@RequestMapping(
			path= {"/pages/showOrdersDetail/{uid}"}
			)
	public @ResponseBody String showOrdersDetail(@PathVariable String uid) {
		OrdersBean bean=new OrdersBean();
		bean.setUid(uid);
		List<OrdersBean> orders=ordersService.showorders(bean);
		JSONArray arr= new JSONArray();
		for(OrdersBean order:orders) {
			Integer orderid=order.getOrderid();
			List<OrderitemBean> orderitems=ordersService.showitems(order);
			for(OrderitemBean item:orderitems) {
				JSONObject obj=new JSONObject();
				obj.put("orderid",item.getOrderid());
				obj.put("pid",item.getPid());
				obj.put("picmain",item.getPicmain());
				obj.put("productname",item.getProductname());
				obj.put("price", item.getPrice());
				obj.put("qty", item.getQty());
				arr.put(obj);
			}
		}
		
		return arr.toString();
	}
	
	@RequestMapping(
			path= {"/pages/showOrders/{uid}"}
			)
	public @ResponseBody String showOrders(@PathVariable String uid) {
		OrdersBean bean=new OrdersBean();
		bean.setUid(uid);
		List<OrdersBean> orders=ordersService.showorders(bean);
		JSONArray arr= new JSONArray();
		for(OrdersBean order:orders) {
			JSONObject obj=new JSONObject();
			obj.put("orderid", order.getOrderid());
			obj.put("uid", order.getUid());
			obj.put("ordername", order.getOrdername());
			obj.put("orderdate", order.getOrderdate().toString());
			obj.put("code", order.getCode());
			obj.put("city", order.getCity());
			obj.put("address", order.getAddress());
			obj.put("totalamount", order.getTotalamount());
			obj.put("orderstatus", order.getOrderstatus());
			arr.put(obj);
		}
		
		return arr.toString();
		
	}
	
	
	@RequestMapping(
			path= {"/pages/addtocart/{pid}"}
			)
	public String addtoCartMap(@PathVariable String pid,Model model, HttpSession httpsession) {
		//把網頁的加到我的購物車 透過hashmap 存 {商品:數量}
		//1.獲得商品的id
		//2.從cart(Map)中取出該商品id,數量(String,integer)
		System.out.println("addtocart");

		 @SuppressWarnings("unchecked")
		HashMap<String, Integer> cart= (HashMap<String, Integer>) httpsession.getAttribute("cart");
		 if(cart==null) {
			 cart=new HashMap<String,Integer>();
		 }
		 if(cart.containsKey(pid))
	        {
	            //4.如果有則+1
	            int number=cart.get(pid);
	            cart.put(pid, number+1);
	        }else{
	            //5.如果不存在 則新增一個
	            cart.put(pid, 1);
	        }
		 
		 httpsession.setAttribute("cart", cart);
		return "/pages/products";
		
	}

	@RequestMapping(
			path= {"/pages/cartlist"}
			,produces="application/json;charset=utf-8"
			)
	public @ResponseBody String showMyCart(Model model,HttpSession httpsession) {
		System.out.println("showcartlist");
		
		
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart= (HashMap<String, Integer>) httpsession.getAttribute("cart");
		
		if(cart!=null){
		    List<HashMap<String,Object>> cartList= new ArrayList<>();
		    JSONArray cartarray=new JSONArray();
			Set<String> keys=cart.keySet();
		    for(String id:keys){
		    	HashMap<String,Object> cartMap=new HashMap<>();
		    	JSONObject cartobj=new JSONObject();
		        Integer qty=cart.get(id);//每件商品的数量
		        Integer pid=Integer.parseInt(id);
		       
		        ProductBean bean=session.get(ProductBean.class, pid);
		        String picmain=bean.getPicmain();
		        Integer price=bean.getPrice();
		        String productname=bean.getProductname();
		        //jsp
		        cartMap.put("pid", pid);
		        System.out.println("商品名稱:"+productname+",商品数量："+qty+"商品價錢"+price+"pid"+pid);
		        cartMap.put("picmain", picmain);
		        cartMap.put("productname", productname);
		        cartMap.put("price", price);
		        cartMap.put("qty", qty);
		        cartList.add(cartMap);
		        
		        //json版本還沒寫
		        cartobj.put("pid", pid);
		        cartobj.put("picmain", picmain);
		        cartobj.put("productname", productname);
		        cartobj.put("price", price);
		        cartobj.put("qty", qty);
		        cartarray.put(cartobj);
		        }

//		    httpsession.setAttribute("cartList", cartList);
		    
		    return cartarray.toString();
		    }

		
		return null ;
		
	}
	@RequestMapping(
			path= {"/pages/delfromcart/{pid}"}
			)
	public String delFromCart(@PathVariable String pid,Model model, HttpSession httpsession) {
		//把網頁的加到我的購物車 透過hashmap 存 {商品:數量}
		//1.獲得商品的id
		//2.從cart(Map)中取出該商品id,數量(String,integer)
		System.out.println("delfromcart");
		System.out.println(pid);
		 @SuppressWarnings("unchecked")
		HashMap<String, Integer> cart= (HashMap<String, Integer>) httpsession.getAttribute("cart");
		 if(cart==null) {
			 cart=new HashMap<String,Integer>();
		 }
		 if(cart.containsKey(pid))
	        {
	            cart.remove(pid);
	        }
		 
		httpsession.setAttribute("cart", cart);
		return "/pages/cartlist.view";
		
	}
	
	@RequestMapping(
			path= {"/pages/status.controller"}
			,produces="application/json;charset=utf-8"
					,method=RequestMethod.POST
			)
	public String status(@RequestBody String data ,Model model) {
		System.out.println("status: "+data);
		JSONObject obj=new JSONObject(data);
		if(obj!=null) {
			OrdersBean bean=new OrdersBean();
			if(obj.get("cancel")!=null) {
				Integer orderid= obj.getInt("cancel");
				bean.setOrderid(orderid);
				OrdersBean order=ordersService.select(bean).get(0);
				order.setOrderstatus(3);
			}else{
				Integer orderid= obj.getInt("done");
				bean.setOrderid(orderid);
				OrdersBean order=ordersService.select(bean).get(0);
				order.setOrderstatus(2);
			}
			
			return "更新成功";
		}
		
		
		 
		return "更新失敗";
		
	}
	
	@RequestMapping(
			path= {"/pages/neworder.controller"}
			,produces="application/json;charset=utf-8"
			,method=RequestMethod.POST
			)
//	@ResponseBody
	public String neworder(@CookieValue(value="user",required=false) String user, @RequestBody String data,Model model, HttpSession httpsession) {
		//1. 取得要結帳的商品
		System.out.println(data);
		JSONObject raw=new JSONObject(data);
		JSONObject checkouts=raw.getJSONObject("checkout");
		JSONObject orders=raw.getJSONObject("orders");
		
//		System.out.println(checkouts);
//		System.out.println(orders);
		
		OrdersBean newOrder=new OrdersBean();
		//uid 要在會員登入的時候 用 session 保存 目前先做假的
		JSONObject cookie=new JSONObject(user);
		System.out.println(cookie);
		newOrder.setUid(cookie.getString("email"));
		newOrder.setOrdername(orders.getString("ordername"));
		newOrder.setPhone(orders.getString("phone"));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();   
		try {
			newOrder.setOrderdate(sdf.parse(sdf.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newOrder.setCode(orders.getString("code"));
		newOrder.setCity(orders.getString("city"));
		newOrder.setAddress(orders.getString("address"));
		newOrder.setTotalamount((Integer)orders.getInt("totalamount"));
		newOrder.setOrderstatus((Integer)0);
		
		// checkouts
		List<OrderitemBean> orderitems=new ArrayList<>();
		Set<String> keys=checkouts.keySet();
		for(String key:keys) {
			OrderitemBean orderitem=new OrderitemBean();
			
			Integer pid=Integer.parseInt(key);
			ProductBean product=session.get(ProductBean.class, pid);
			orderitem.setPid(pid);
			orderitem.setPicmain(product.getPicmain());
			orderitem.setProductname(product.getProductname());
			orderitem.setPrice(product.getPrice());
			Integer qty=(Integer)checkouts.get(key);
			orderitem.setQty(qty);
				
			orderitems.add(orderitem);
		}
		
		try {
			boolean result = ordersService.insert(newOrder, orderitems);
			
			if (result) {
				
				return "下單成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return "下單失敗！";
		
	}
}
