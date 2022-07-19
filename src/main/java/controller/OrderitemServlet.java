package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.OrderitemBean;
import model.OrderitemService;


@WebServlet(
		urlPatterns={"/pages/orderitem.controller"}		
)
public class OrderitemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private OrderitemService orderitemService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application.getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		orderitemService = context.getBean("orderitemService", OrderitemService.class);
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//接收資料
		String temp1 = request.getParameter("id");
		String name = request.getParameter("orderID");
		String name2 = request.getParameter("pid");
		String temp2 = request.getParameter("picmain");
		String temp3 = request.getParameter("productname");
		String temp4 = request.getParameter("price");
		String temp5 = request.getParameter("qty");
		String temp6 = request.getParameter("discount");
		String orderitemction = request.getParameter("orderitemction");
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(orderitemction!=null) {
			if(orderitemction.equals("Update") || orderitemction.equals("Delete")) {
				if(temp1==null || temp1.length()==0) {
					errors.put("id", "Please enter Id for "+orderitemction);
				}
			}
		}
		
//轉換資料
		int id = 0;
		if(temp1!=null && temp1.length()!=0) {
			try {
				id = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("id", "error");
			}
		}
		int orderid = 0;
		if(name!=null && name.length()!=0) {
			try {
				orderid = Integer.parseInt(name);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("orderid", "error");				
			}
		}
		int pid = 0;
		if(name2!=null && name2.length()!=0) {
			try {
				pid = Integer.parseInt(name2);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("pid", "error");				
			}
		}
		String picmain = "";
		if(temp2!=null && temp2.length()!=0) {
			try {
				picmain = temp2;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("picmain", "error");				
			}
		}
		String productname = "";
		if(temp3!=null && temp3.length()!=0) {
			try {
				productname = temp3;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("productname", "error");				
			}
		}
		int price = 0;
		if(temp4!=null && temp4.length()!=0) {
			try {
				price = Integer.parseInt(temp4);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("price", "error");				
			}
		}
		int qty = 0;
		if(temp5!=null && temp5.length()!=0) {
			try {
				qty = Integer.parseInt(temp5);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("qty", "error");				
			}
		}
		Double discount = 0.0;
		if(temp6!=null && temp6.length()!=0) {
			try {
				discount = Double.parseDouble(temp6);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("discount", "error");
			}
		}
		
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/backend/orderitem.view").forward(request, response);
			return;
		}
//		System.out.println(orderid);
//呼叫Model
		OrderitemBean bean = new OrderitemBean();
		bean.setId(id);
		bean.setOrderid(orderid);
		bean.setPid(pid);
		bean.setPicmain(picmain); 
		bean.setProductname(productname); 
		bean.setPrice(price); 
		bean.setQty(qty);
		bean.setDiscount(discount); 
		OrderitemBean bean2 = new OrderitemBean();
//根據Model執行結果導向View
		try {
			if( orderitemction.equals("Select")) {
				List<OrderitemBean> result = orderitemService.select(bean);
				request.setAttribute("select", result);
				request.getRequestDispatcher(
						"/backend/orderitemdisplay.view").forward(request, response);
//			} else if(orderitemction!=null && orderitemction.equals("Insert")) {
//				OrderitemBean result = orderitemService.insert(bean);
//				if(result==null) {
//					errors.put("action", "Insert fail");
//				} else {
//					List<OrderitemBean> result2 = orderitemService.select(bean2);
//					request.setAttribute("select", result2);
//					request.getRequestDispatcher(
//							"/pages/orderitemdisplay.view").forward(request, response);
//				}
//				request.getRequestDispatcher(
//						"/pages/orderitem.view").forward(request, response);
			} else if(orderitemction!=null && orderitemction.equals("Update")) {
				OrderitemBean result = orderitemService.update(bean);
				if(result==null) {
					errors.put("action", "更新失敗");
				} else {
					List<OrderitemBean> result2 = orderitemService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/orderitemdisplay.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/orderitem.view").forward(request, response);
			} else if(orderitemction!=null && orderitemction.equals("Delete")) {
				boolean result = orderitemService.delete(bean);
				if(!result) {
					request.setAttribute("delete", 0);
				} else {
					List<OrderitemBean> result2 = orderitemService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/orderitemdisplay.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/orderitem.view").forward(request, response);
			}else if(orderitemction!=null && orderitemction.equals("Show")){
//					System.out.println("hello");
					List<OrderitemBean> result = orderitemService.select(bean2);
					List<OrderitemBean> resquestresult = null;
					resquestresult = new ArrayList<OrderitemBean>();
					for(int i=0;i<result.size();i++) {
//						System.out.println("i="+i);
						OrderitemBean ids = result.get(i);
//						System.out.println("getOrderid="+ids.getOrderid());
//						System.out.println("getId="+ids.getId());
						if(ids.getOrderid().equals(orderid)) {
							bean.setId(ids.getId());
							OrderitemBean result2 = orderitemService.show(bean);
//							System.out.println(result2);
							resquestresult.add(result2);
						}
					}
					
					
					request.setAttribute("show", resquestresult);
					request.getRequestDispatcher(
							"/backend/orderitemdisplay.view").forward(request, response);
			} else  {
				errors.put("action", "Unknown Action:"+orderitemction);
				List<OrderitemBean> result2 = orderitemService.select(bean2);
				request.setAttribute("select", result2);
				request.getRequestDispatcher(
						"/backend/orderitemdisplay.view").forward(request, response);
			}
		}catch(Exception e) {
				request.getRequestDispatcher(
						"/backend/orderitemdisplay.view").forward(request, response);
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
