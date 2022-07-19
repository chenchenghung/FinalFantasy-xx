package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
	@RequestMapping(path = {"/"})
	public String handlerMethod1() {
		return "/index";
	}
	@RequestMapping(path = "/secure/login.view")
	public String handlerMethod2() {
		return "/secure/login";
	}
	@RequestMapping(value = "/pages/client.view")
	public String handlerMethod3() {
		return "/pages/client";
	}
	
	
	@RequestMapping(value = "/pages/products.view")
	public String handlerMethod5() {
		return "/pages/products";
	}
	
	@RequestMapping(value = "/pages/productdetail.view")
	public String handlerMethod6() {
		return "/pages/productdetail";
	}

	@RequestMapping(value = "/pages/cartlist.view")
	public String handlerMethod7() {
		return "/pages/cartlist";
	}
	
	
	@RequestMapping(value = "/pages/newaccount.view")
	public String handlerMethod9() {
		return "/pages/newaccount";
	}
	
	
//	@RequestMapping(path = {"/secure/login.view"})
//	public String handlerMethod2() {
//		return "/secure/login";
//	}
	@RequestMapping(path = {"/backend/product.view"})
	public String handlerMethod22() {
		return "/backend/product";
	}
	@RequestMapping(path = {"/backend/display.view"})
	public String handlerMethod23() {
		
		return "/backend/display";
	}
	@RequestMapping(path = {"/backend/staffdisplay.view"})
	public String handlerMethod24() {
		
		return "/backend/staffdisplay";
	}
	@RequestMapping(path = {"/backgroundsystem.view"})
	public String handlerMethod25() {
		
		return "/backgroundsystem";
	}
	@RequestMapping(path = {"/backend/staffinsert.view"})
	public String handlerMethod26() {
		
		return "/backend/staffinsert";
	}
	@RequestMapping(path = {"/backend/staff.view"})
	public String handlerMethod27() {
		
		return "/backend/staff";
	}
	@RequestMapping(path = {"/backend/productinsert.view"})
	public String handlerMethod28() {
		
		return "/backend/productinsert";
	}
	@RequestMapping(path = {"/backend/member.view"})
	public String handlerMethod10() {
		
		return "/backend/member";
	}
	@RequestMapping(path = {"/backend/memberdisplay.view"})
	public String handlerMethod11() {
		
		return "/backend/memberdisplay";
	}
	@RequestMapping(path = {"/backend/memberinsert.view"})
	public String handlerMethod12() {
		
		return "/backend/memberinsert";
	}
	@RequestMapping(path = {"/backend/ordersinsert.view"})
	public String handlerMethod13() {
		
		return "/backend/ordersinsert";
	}
	@RequestMapping(path = {"/backend/orders.view"})
	public String handlerMethod14() {
		
		return "/backend/orders";
	}
	@RequestMapping(path = {"/backend/ordersdisplay.view"})
	public String handlerMethod15() {
		
		return "/backend/ordersdisplay";
	}
	@RequestMapping(path = {"/backend/orderitemdisplay.view"})
	public String handlerMethod16() {
		
		return "/backend/orderitemdisplay";
	}
	@RequestMapping(path = {"/backend/orderitem.view"})
	public String handlerMethod17() {
		
		return "/backend/orderitem";
	}
	@RequestMapping(path = {"/backend/orderiteminsert.view"})
	public String handlerMethod18() {
		
		return "/backend/orderiteminsert";
	}
	@RequestMapping(path = {"/backend/productsnapshotdisplay.view"})
	public String handlerMethod19() {
		
		return "/backend/productsnapshotdisplay";
	}
	@RequestMapping(path = {"/backend/productsnapshotinsert.view"})
	public String handlerMethod20() {
		
		return "/backend/productsnapshotinsert";
	}
	@RequestMapping(path = {"/backend/productsnapshot.view"})
	public String handlerMethod21() {
		
		return "/backend/productsnapshot";
	}
}
