package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import model.ProductBean;
import model.ProductService;

@WebServlet(
		urlPatterns={"/pages/product.controller"}		
)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private ProductService productService;
	@Override
	public void init() throws ServletException {
//		productService = new ProductService();
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application.getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		productService = context.getBean("productService", ProductService.class);
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//接收資料
		String temp1 = request.getParameter("pid");
		String name = request.getParameter("picmain");
		String temp2 = request.getParameter("productname");
		String temp3 = request.getParameter("productdesc");
		String temp4 = request.getParameter("cateid");
		String temp5 = request.getParameter("spec");
		String temp6 = request.getParameter("material");
		String temp7 = request.getParameter("price");
		String temp8 = request.getParameter("size");
		String temp9 = request.getParameter("weight");
		String prodaction = request.getParameter("prodaction");
		
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(prodaction!=null) {
			if(prodaction.equals("Update") || prodaction.equals("Delete")) {
				if(temp1==null || temp1.length()==0) {
					errors.put("pid", "Please enter Id for "+prodaction);
				}
			}
		}
		
//轉換資料
		int pid = 0;
		if(temp1!=null && temp1.length()!=0) {
			try {
				pid = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("pid", "error");
			}
		}
		String picmain = "";
		if(name!=null && name.length()!=0) {
			try {
				picmain = name;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("picmain", "error");				
			}
		}
		String productname = "";
		if(temp2!=null && temp2.length()!=0) {
			try {
				productname = temp2;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("productname", "error");				
			}
		}
		String productdesc = "";
		if(temp3!=null && temp3.length()!=0) {
			try {
				productdesc = temp3;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("productdesc", "error");				
			}
		}
		int cateid = 0;
		if(temp4!=null && temp4.length()!=0) {
			try {
				cateid = Integer.parseInt(temp4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("cateid", "error");				
			}
		}
		String spec = "";
		if(temp5!=null && temp5.length()!=0) {
			try {
				spec = temp5;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("spec", "error");				
			}
		}
		String material = "";
		if(temp6!=null && temp6.length()!=0) {
			try {
				material = temp6;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("material", "error");				
			}
		}
		int price = 0;
		if(temp7!=null && temp7.length()!=0) {
			try {
				price = Integer.parseInt(temp7);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("price", "error");
			}
		}
		String size = "";
		if(temp8!=null && temp8.length()!=0) {
			try {
				size = temp8;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("size", "error");				
			}
		}
		String weight = "";
		if(temp9!=null && temp9.length()!=0) {
			try {
				weight = temp9;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("weight", "error");				
			}
		}
		
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/backend/product.view").forward(request, response);
			return;
		}

//呼叫Model
		ProductBean bean = new ProductBean();
		bean.setPid(pid);
		bean.setPicmain(picmain);
		bean.setProductname(productname); 
		bean.setProductdesc(productdesc); 
		bean.setCateid(cateid); 
		bean.setSpec(spec);
		bean.setMaterial(material); 
		bean.setPrice(price); 
		bean.setSize(size);
		bean.setWeight(weight);
		ProductBean bean2 = new ProductBean();
		
//根據Model執行結果導向View
		try {
			if( prodaction.equals("Select")) {
				List<ProductBean> result = productService.select(bean);
				request.setAttribute("select", result);
				request.getRequestDispatcher(
						"/backend/display.view").forward(request, response);
			} else if(prodaction!=null && prodaction.equals("Insert")) {
				ProductBean result = productService.insert(bean);
				if(result==null) {
					errors.put("action", "Insert fail");
				} else {
					List<ProductBean> result2 = productService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/display.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/product.view").forward(request, response);
			} else if(prodaction!=null && prodaction.equals("Update")) {
				ProductBean result = productService.update(bean);
				if(result==null) {
					errors.put("action", "更新失敗");
				} else {
					List<ProductBean> result2 = productService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/display.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/product.view").forward(request, response);
			} else if(prodaction!=null && prodaction.equals("Delete")) {
				boolean result = productService.delete(bean);
				if(!result) {
					request.setAttribute("delete", 0);
				} else {
					List<ProductBean> result2 = productService.select(bean2);
					request.setAttribute("select", result2);
					request.getRequestDispatcher(
							"/backend/display.view").forward(request, response);
				}
				request.getRequestDispatcher(
						"/backend/product.view").forward(request, response);
			} else  {
				errors.put("action", "Unknown Action:"+prodaction);
				List<ProductBean> result2 = productService.select(bean2);
				request.setAttribute("select", result2);
				request.getRequestDispatcher(
						"/backend/display.view").forward(request, response);
			}
		}catch(Exception e) {
			request.getRequestDispatcher(
					"/backend/display.view").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
