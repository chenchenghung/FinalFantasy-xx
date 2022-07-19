package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.ProductBean;
import model.ProductService;

@WebServlet(urlPatterns = { "/pages/productuploadimage.controller" })
@MultipartConfig(location = "C:\\JavaFramework\\eclipse-workspace\\labweb-topic-final-test3\\src\\main\\webapp\\upload")
public class ProductUploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private ProductService productService;

	@Override
	public void init() throws ServletException {
//		productService = new ProductService();
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		productService = context.getBean("productService", ProductService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//接收資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		int pid = 0;
		String prodaction = "";
		String productname = "";
		String productdesc = "";
		int cateid = 0;
		String spec = "";
		String material = "";
		int price = 0;
		String size = "";
		String weight = "";

		String picmain = "";
		String picfirst = "";
		String picsecond = "";
		String picthird = "";
		String picfourth = "";
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String tempt = parameterNames.nextElement();
			if (tempt.equals("productname")) {
				try {
					String temp1 = request.getParameter(tempt);
					productname = temp1;
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("productname", "error");
				}
			} else if (tempt.equals("pid")) {
				try {
					String temp1 = request.getParameter(tempt);
					pid = Integer.parseInt(temp1);
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("pid", "error");
				}
			} else if (tempt.equals("picmain")) {
				try {
					String temp1 = request.getParameter(tempt);
					picmain = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picmain", "error");
				}
			} else if (tempt.equals("picfirst")) {
				try {
					String temp1 = request.getParameter(tempt);
					picfirst = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picfirst", "error");
				}
			} else if (tempt.equals("picsecond")) {
				try {
					String temp1 = request.getParameter(tempt);
					picsecond = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picsecond", "error");
				}
			} else if (tempt.equals("picthird")) {
				try {
					String temp1 = request.getParameter(tempt);
					picthird = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picthird", "error");
				}
			} else if (tempt.equals("picfourth")) {
				try {
					String temp1 = request.getParameter(tempt);
					picfourth = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picfourth", "error");
				}
			} else if (tempt.equals("productdesc")) {
				try {
					String temp1 = request.getParameter(tempt);
					productdesc = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("productdesc", "error");
				}
			} else if (tempt.equals("cateid")) {
				try {
					String temp1 = request.getParameter(tempt);
					cateid = Integer.parseInt(temp1);
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("cateid", "error");
				}
			} else if (tempt.equals("spec")) {
				try {
					String temp1 = request.getParameter(tempt);
					spec = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("spec", "error");
				}
			} else if (tempt.equals("material")) {
				try {
					String temp1 = request.getParameter(tempt);
					material = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("material", "error");
				}
			} else if (tempt.equals("price")) {
				try {
					String temp1 = request.getParameter(tempt);
					price = Integer.parseInt(temp1);
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("price", "error");
				}
			} else if (tempt.equals("size")) {
				try {
					String temp1 = request.getParameter(tempt);
					size = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("size", "error");
				}
			} else if (tempt.equals("weight")) {
				try {
					String temp1 = request.getParameter(tempt);
					weight = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("weight", "error");
				}
			}else if (tempt.equals("prodaction")) {
				try {
					String temp1 = request.getParameter(tempt);
					prodaction = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("prodaction", "error");
				}
			}
			String temp1 = request.getParameter(tempt);
			System.out.println("引數名：" + temp1 + "\t" + "結果：" + request.getParameter(temp1));
		}

		// 驗證資料
//		if (errors != null && !errors.isEmpty()) {
//			request.getRequestDispatcher("/pages/product.view").forward(request, response);
//			return;
//		}
		if(prodaction.equals("Update")) {
		String fname = "";
		String picUrl = "../upload/";
		int i = 0;
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			String name = part.getName();
			if (name.equals("upload") || name.equals("upload1") || name.equals("upload2") || name.equals("upload3") || name.equals("upload4")) {
				String sname = part.getSubmittedFileName();
				String suffixName = sname.substring(sname.lastIndexOf("."));
				fname = UUID.randomUUID() + suffixName;
				System.out.println(suffixName);
//				if (sname.length() == 0)
//					continue;
				part.write(fname);
				if (i == 0) {
					picmain = picUrl + fname;
				} else if (i == 1) {
					picfirst = picUrl + fname;
				} else if (i == 2) {
					picsecond = picUrl + fname;
				} else if (i == 3) {
					picthird = picUrl + fname;
				} else if (i == 4) {
					picfourth = picUrl + fname;
				}
				i++;
				System.out.println(fname);
				}
			}
		}else {
			
		}

	

//呼叫Model
	ProductBean bean = new ProductBean();
	bean.setPid(pid);
	if(picmain==null || picmain.length()==0) {
		
	}else {
		bean.setPicmain(picmain);
	}
	if(picfirst==null || picfirst.length()==0) {
			
	}else {
		bean.setPicfirst(picfirst);
	}
	if(picsecond==null || picsecond.length()==0) {
		
	}else {
		bean.setPicsecond(picsecond);
	}
	if(picthird==null || picthird.length()==0) {
		
	}else {
		bean.setPicthird(picthird);
	}
	if(picfourth==null || picfourth.length()==0) {
		
	}else {
		bean.setPicfourth(picfourth);
	}
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
	if( prodaction.equals("Update")) {
		ProductBean result = productService.update(bean);
		if(result==null)
		{
			errors.put("action", "更新失敗");
		}else
		{
			List<ProductBean> result2 = productService.select(bean2);
			request.setAttribute("select", result2);
			request.getRequestDispatcher("/pages/display.view").forward(request, response);
		}
	}else if(prodaction.equals("Delete")){
			boolean result = productService.delete(bean);
			if (!result) {
				request.setAttribute("delete", 0);
			} else {
				List<ProductBean> result2 = productService.select(bean2);
				request.setAttribute("select", result2);
				request.getRequestDispatcher("/pages/display.view").forward(request, response);
			}
		}
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
