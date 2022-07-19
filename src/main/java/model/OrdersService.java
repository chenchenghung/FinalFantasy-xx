package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrdersService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrderitemDAO orderitemDAO;
	
	public boolean insert(OrdersBean bean,List<OrderitemBean> orderitems) throws Exception {
		boolean result = false;
		if(bean!=null && orderitems!=null) {
			Integer pk = ordersDAO.insert(bean);
			System.out.println("get and insert orderid="+pk);
			
			for(OrderitemBean orderitem:orderitems) {
				orderitem.setOrderid(pk);
				if (!orderitemDAO.insert(orderitem)) {
					// 訂單品項新增失敗就rollback
					throw new Exception();
				}
			}
			return true;
			
		}
		return result;
	}
	
	public List<OrdersBean> showorders(OrdersBean bean) {
		List<OrdersBean> result = null;
		if(bean!=null && bean.getUid()!=null ) {
			return ordersDAO.select(bean.getUid());
			
		}else {
			return result;
		}
	}
	
	public List<OrderitemBean> showitems(OrdersBean bean){
		List<OrderitemBean> result=null;
		if(bean!=null && bean.getOrderid()!=null) {
			result= ordersDAO.showitems(bean.getOrderid());
			return result;
		}else {
			return result;
		}
	}
	
	public List<OrdersBean> select(OrdersBean bean) {
		List<OrdersBean> result = null;
		if(bean!=null && bean.getOrderid()!=null && !bean.getOrderid().equals(0)) {
			OrdersBean temp = ordersDAO.select(bean.getOrderid());
			if(temp!=null) {
				result = new ArrayList<OrdersBean>();
				result.add(temp);
			}
		} else {
			result = ordersDAO.select(); 
		}
		return result;
	}
	
	public OrdersBean update(OrdersBean bean) {
		OrdersBean result = null;
		if(bean!=null && bean.getOrderid()!=null) {
			result = ordersDAO.update(bean.getOrderid(), bean.getUid(),
					bean.getOrdername(), bean.getPhone(), bean.getOrderdate(), bean.getCode()
					,bean.getCity(), bean.getAddress(), bean.getTotalamount()
					,bean.getOrderstatus());
		}
		return result;
	}
	public boolean delete(OrdersBean bean) {
		boolean result = false;
		if(bean!=null && bean.getOrderid()!=null) {
			result = ordersDAO.delete(bean.getOrderid());
		}
		return result;
	}
}
