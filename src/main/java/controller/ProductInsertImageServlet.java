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

@WebServlet(urlPatterns = { "/pages/productinsertimage.controller" })
@MultipartConfig(
		location = "C:\\JavaFramework\\eclipse-workspace\\labweb-topic-final-test3\\src\\main\\webapp\\upload"
		)
public class ProductInsertImageServlet extends HttpServlet {
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
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		int pid = 0;
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
		while (parameterNames.hasMoreElements()) {
			System.out.println("run parameterNames");
			String temp = parameterNames.nextElement();
				if (temp.equals("productname")) {
					try {
						String temp1 = request.getParameter(temp);
						productname = temp1;
					} catch (Exception e) {
						e.printStackTrace();
						errors.put("productname", "error");
					}
				} else if (temp.equals("pid")) {
					try {
						String temp1 = request.getParameter(temp);
						pid = Integer.parseInt(temp1);;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("pid", "error");
				}
				}else if (temp.equals("picmain")) {
				try {
					String temp1 = request.getParameter(temp);
					picmain = temp1;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("picmain", "error");
				}
				} else if (temp.equals("picfirst")) {
					try {
						String temp1 = request.getParameter(temp);
						picfirst = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("picfirst", "error");
					}
				} else if (temp.equals("picsecond")) {
					try {
						String temp1 = request.getParameter(temp);
						picsecond = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("picsecond", "error");
					}
				} else if (temp.equals("picthird")) {
					try {
						String temp1 = request.getParameter(temp);
						picthird = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("picthird", "error");
					}
				} else if (temp.equals("picfourth")) {
					try {
						String temp1 = request.getParameter(temp);
						picfourth = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("picfourth", "error");
					}
				} else if (temp.equals("productdesc")) {
					try {
						String temp1 = request.getParameter(temp);
						productdesc = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("productdesc", "error");
					}
				} else if (temp.equals("cateid")) {
					try {
						String temp1 = request.getParameter(temp);
						cateid = Integer.parseInt(temp1);
					} catch (Exception e) {
						e.printStackTrace();
						errors.put("cateid", "error");
					}
				} else if (temp.equals("spec")) {
					try {
						String temp1 = request.getParameter(temp);
						spec = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("spec", "error");
					}
				} else if (temp.equals("material")) {
					try {
						String temp1 = request.getParameter(temp);
						material = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("material", "error");
					}
				} else if (temp.equals("price")) {
					try {
						String temp1 = request.getParameter(temp);
						price = Integer.parseInt(temp1);
					} catch (Exception e) {
						e.printStackTrace();
						errors.put("price", "error");
					}
				} else if (temp.equals("size")) {
					try {
						String temp1 = request.getParameter(temp);
						size = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("size", "error");
					}
				} else if (temp.equals("weight")) {
					try {
						String temp1 = request.getParameter(temp);
						weight = temp1;
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("weight", "error");
					}
				}
				String temp1 = request.getParameter(temp);
				System.out.println("引數名：" + temp + "\t" + "結果：" + request.getParameter(temp));
			}

		// 驗證資料
//		if (errors != null && !errors.isEmpty()) {
//			request.getRequestDispatcher("/pages/productinsert.view").forward(request, response);
//			return;
//		}

		String fname = "";
		String picUrl = "../upload/";
//		String uploadRootPath = request.getServletContext().getRealPath("upload/");
//		System.out.println(uploadRootPath);
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
					System.out.println(picmain);
				} else if (i == 1) {
					picfirst = picUrl + fname;
					System.out.println(picfirst);
				} else if (i == 2) {
					picsecond = picUrl + fname;
					System.out.println(picsecond);
				} else if (i == 3) {
					picthird = picUrl + fname;
					System.out.println(picthird);
				} else if (i == 4) {
					picfourth = picUrl + fname;
					System.out.println(picfourth);
				}
				i++;
//				System.out.println(fname);
			}

		}
//呼叫Model
	ProductBean bean = new ProductBean();
	bean.setPid(pid);
	bean.setPicmain(picmain);
	bean.setPicfirst(picfirst);
	bean.setPicsecond(picsecond);
	bean.setPicthird(picthird);
	bean.setPicfourth(picfourth);
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
		ProductBean result = productService.insert(bean);
		if(result==null)
		{
			errors.put("action", "Insert fail");
			System.out.println("Insert fail");
		}else
		{
			List<ProductBean> result2 = productService.select(bean2);
			request.setAttribute("select", result2);
			request.getRequestDispatcher("/pages/display.view").forward(request, response);
		}
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
