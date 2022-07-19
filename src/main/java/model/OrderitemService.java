package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderitemService {
	@Autowired
	private OrderitemDAO orderitemDAO;


	public boolean insert(OrderitemBean bean) {
		boolean result = false;
		if(bean!=null) {
			 result= orderitemDAO.insert(bean);
		}
		return result;
	}
	public OrderitemBean update(OrderitemBean bean) {
		OrderitemBean result = null;
		if(bean!=null && bean.getId()!=null) {
			result = orderitemDAO.update(bean.getId(), bean.getOrderid(), bean.getPid(),
					bean.getPicmain(), bean.getProductname(), bean.getPrice(), bean.getQty()
					,bean.getDiscount());
		}
		return result;
	}
	public boolean delete(OrderitemBean bean) {
		boolean result = false;
		if(bean!=null && bean.getId()!=null) {
			result = orderitemDAO.delete(bean.getId());
		}
		return result;
	}
	
	public OrderitemBean show(OrderitemBean bean) {
//		List<OrderitemBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
			OrderitemBean temp = orderitemDAO.select(bean.getId());
//			if(temp!=null) {
////				result = new ArrayList<OrderitemBean>();
////				result.add(temp);
//			}
//		} else {
//			result = orderitemDAO.select(); 
//		}
		return temp;
		}
		return bean;
	}
	
	public List<OrderitemBean> select(OrderitemBean bean) {
		List<OrderitemBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
			OrderitemBean temp = orderitemDAO.select(bean.getId());
			if(temp!=null) {
				result = new ArrayList<OrderitemBean>();
				result.add(temp);
			}
		} else {
			result = orderitemDAO.select(); 
		}
		return result;
	}
}
