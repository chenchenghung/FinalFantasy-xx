package model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.CategoryBean;
import model.CategoryDAO;
import model.OrderitemBean;
import model.OrdersBean;
import model.OrdersDAO;
import model.hibernate.HibernateUtil;
@Repository
public class OrdersDAOHibernate implements OrdersDAO {

//	public static void main(String[] args) {
//		
//		SessionFactory sessionFactory=HibernateUtil.getSessionfactory();
//		Session session= sessionFactory.getCurrentSession();
//		Transaction trx = session.beginTransaction();
//		
//		
//		OrdersDAO ordersDAO= new OrdersDAOHibernate(sessionFactory);
		
		//select
		//使用關聯語法依照訂單查詢訂單清單商品
//		List<OrderitemBean> beans=(List<OrderitemBean>) ordersDAO.select(1);
//		for(OrderitemBean item: beans) {
//			System.out.println(item);
//		}
		
		//select 
		//依照使用者 已完成 或未完成 查詢訂單
//		List<OrdersBean> orders=ordersDAO.select("6554ffe33gde102", 0);
//		for(OrdersBean order: orders) {
//			System.out.println(order);
//		}
		
		
		//update
//		CategoryBean bean=categoryDAO.select(3);
//		bean.setCatename("燈光照明");
//		CategoryBean result=categoryDAO.update(bean);
//		System.out.println(result);

		//select all
//		List<CategoryBean> cates= categoryDAO.select();
//		for(CategoryBean cate:cates) {
//			System.out.println(cate);
//		}
		
//		trx.commit();
//		session.close();
//		sessionFactory.close();
//		
//	}

//	@Autowired
//	private SessionFactory sessionFactory;
//	public OrdersDAOHibernate(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	public Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	@Override
	public List<OrdersBean> select(String uid) {
		//0 狀態為準備出貨中 1: 已完成=歷史訂單
		List<OrdersBean> result=null;
		if(uid!=null ) {
			String hql = "FROM OrdersBean WHERE (uid=:uid and orderstatus=:orderstatus) ORDER BY orderdate DESC";
			List<OrdersBean> orders = this.getSession().createQuery(hql, OrdersBean.class)
					.setParameter("uid", uid)
//					.setParameter("orderstatus", orderstatus)
//					.setFirstResult((currentPage - 1) * pageSize)
//					.setMaxResults(pageSize)
					.list();
			return orders;
			
		}
		return result;
	}
	
	@Override
	public List<OrderitemBean> showitems(Integer orderid) {
		List<OrderitemBean> result=null;
		if(orderid!=null) {
			String hql = "FROM OrderitemBean WHERE orderid=:orderid ";
			List<OrderitemBean> orderitems = this.getSession().createQuery(hql, OrderitemBean.class)
					.setParameter("orderid", orderid)
//					.setFirstResult((currentPage - 1) * pageSize)
//					.setMaxResults(pageSize)
					.list();
			return orderitems;
			
		}
		return result;
	}
	//06/22 後台
	@Override
	public OrdersBean select(Integer orderid) {
		if(orderid!=null) {
			return this.getSession().get(OrdersBean.class, orderid);
		}
		return null;
	}

	@Override
	public List<OrdersBean> select() {
		
		return this.getSession().createQuery("From OrdersBean",OrdersBean.class).list();
	}

	@Override
	public Integer insert(OrdersBean bean) {
		if(bean!=null) {
			
			return (Integer) this.getSession().save(bean);
	}
	return 0;
	}
	



	@Override
	public boolean delete(Integer orderid) {
		if(orderid!=null) {
			OrdersBean temp = this.getSession().get(OrdersBean.class, orderid);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

	
	public OrdersBean update(OrdersBean bean) {
		if(bean!=null && bean.getOrderid()!=null) {
			OrdersBean temp = this.getSession().get(OrdersBean.class, bean.getOrderid());
			if(temp!=null) {
				return (OrdersBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public OrdersBean update(Integer orderid, String uid,
			String ordername, String phone, java.util.Date orderdate,
			String code, String city, String address, Integer totalamount,
			Integer orderstatus) {
		if(orderid!=null) {
			OrdersBean temp = this.getSession().get(OrdersBean.class, orderid);
			if(temp!=null) {
				temp.setOrderid(orderid);
				temp.setUid(uid);
				temp.setOrdername(ordername);
				temp.setPhone(phone);
				temp.setOrderdate(orderdate);
				temp.setCode(code);
				temp.setCity(city);
				temp.setAddress(address);
				temp.setTotalamount(totalamount);
				temp.setOrderstatus(orderstatus);
				return temp;
			}
		}
		return null;
	}




}
