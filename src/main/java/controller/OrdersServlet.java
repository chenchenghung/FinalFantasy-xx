package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.OrdersBean;
import model.OrdersService;



@WebServlet(
		urlPatterns={"/pages/orders.controller"}		
)
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private OrdersService ordersService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application.getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		ordersService = context.getBean("ordersService", OrdersService.class);
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//接收資料
		String temp1 = request.getParameter("orderid");
		String name = request.getParameter("uid");
		String temp2 = request.getParameter("ordername");
		String temp3 = request.getParameter("phone");
		String temp4 = request.getParameter("orderdate");
		String temp5 = request.getParameter("code");
		String temp6 = request.getParameter("city");
		String temp7 = request.getParameter("address");
		String temp8 = request.getParameter("totalamount");
		String temp9 = request.getParameter("orderstatus");
		String orderction = request.getParameter("orderction");
		
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(orderction!=null) {
			if(orderction.equals("Update") || orderction.equals("Delete")) {
				if(temp1==null || temp1.length()==0) {
					errors.put("orderid", "Please enter Orderid for "+orderction);
				}
			}
		}
		
//轉換資料
		int orderid = 0;
		if(temp1!=null && temp1.length()!=0) {
			try {
				orderid = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("orderid", "error");
			}
		}
		String uid = "";
		if(name!=null && name.length()!=0) {
			try {
				uid = name;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("uid", "error");				
			}
		}
		String ordername = "";
		if(temp2!=null && temp2.length()!=0) {
			try {
				ordername = temp2;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("ordername", "error");				
			}
		}
		String phone = "";
		if(temp3!=null && temp3.length()!=0) {
			try {
				phone = temp3;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("phone", "error");				
			}
		}
		java.util.Date orderdate = null;
		if(temp4!=null && temp4.length()!=0) {
			try {
				orderdate = sFormat.parse(temp4);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("orderdate", "error");				
			}
		}
		String code = "";
		if(temp5!=null && temp5.length()!=0) {
			try {
				code = temp5;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("code", "error");				
			}
		}
		String city = "";
		if(temp6!=null && temp6.length()!=0) {
			try {
				city = temp6;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("city", "error");				
			}
		}
		String address = "";
		if(temp7!=null && temp7.length()!=0) {
			try {
				address = temp7;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("address", "error");
			}
		}
		int totalamount = 0;
		if(temp8!=null && temp8.length()!=0) {
			try {
				totalamount = Integer.parseInt(temp8);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("totalamount", "error");				
			}
		}
		int orderstatus = 0;
		if(temp9!=null && temp9.length()!=0) {
			try {
				orderstatus = Integer.parseInt(temp9);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("orderstatus", "error");				
			}
		}
		
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/backend/orders.view").forward(request, response);
			return;
		}

//呼叫Model
		OrdersBean bean = new OrdersBean();
		bean.setOrderid(orderid);
		bean.setUid(uid);
		bean.setOrdername(ordername); 
		bean.setPhone(phone); 
		bean.setOrderdate(orderdate); 
		bean.setCode(code);
		bean.setCity(city); 
		bean.setAddress(address); 
		bean.setTotalamount(totalamount);
		bean.setOrderstatus(orderstatus);
		OrdersBean bean2 = new OrdersBean();

//根據Model執行結果導向View
		try {
			if( orderction.equals("Select")) {
				List<OrdersBean> result = ordersService.select(bean);
				request.setAttribute("select", result);
				request.getRequestDispatcher(
						"/backend/ordersdisplay.view").forward(request, response);
//			} else if(orderction!=null && orderction.equals("Insert")) {
//				OrdersBean result = ordersService.insert(bean);
//				if(result==null) {
//					errors.put("action", "Insert fail");
//				} else {
//					List<OrdersBean> result2 = ordersService.select(bean2);
//					request.setAttribute("select", result2);
//					request.getRequestDispatcher(
//							"/pages/ordersdisplay.view").forward(request, response);
//				}
//				request.getRequestDispatcher(
//						"/pages/orders.view").forward(request, response);
			} else if(orderction!=null && orderction.equals("Update")) {
				OrdersBean result = ordersService.update(bean);
				if(result==null) {
					errors.put("action", "更新失敗");
				} else {
					List<OrdersBean> result2 = ordersService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/ordersdisplay.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/orders.view").forward(request, response);
			} else if(orderction!=null && orderction.equals("Delete")) {
				boolean result = ordersService.delete(bean);
				if(!result) {
					request.setAttribute("delete", 0);
				} else {
					List<OrdersBean> result2 = ordersService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/ordersdisplay.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/orders.view").forward(request, response);
			} else  {
				errors.put("action", "Unknown Action:"+orderction);
				List<OrdersBean> result2 = ordersService.select(bean2);
				request.setAttribute("select", result2);
				request.getRequestDispatcher(
						"/backend/ordersdisplay.view").forward(request, response);
			}
		}catch(Exception e) {
			request.getRequestDispatcher(
					"/backend/ordersdisplay.view").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
