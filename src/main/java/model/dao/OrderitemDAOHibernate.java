package model.dao;


import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.OrderitemBean;
import model.OrderitemDAO;
import model.ProductDAO;
import model.hibernate.HibernateUtil;


@Repository
public class OrderitemDAOHibernate implements OrderitemDAO {

//	public static void main(String[] args) {
//		SessionFactory sessionFactory=HibernateUtil.getSessionfactory();
//		Session session= sessionFactory.getCurrentSession();
//		Transaction trx = session.beginTransaction();
//		
//		
//		OrderitemDAO orderitemDAO= new OrderitemDAOHibernate(sessionFactory);
		//select
//		OrderitemBean bean=orderitemDAO.select(2);
//		System.out.println(bean);
		
		//update
//		OrderitemBean bean=orderitemDAO.select(2);
//		bean.setQty(4);
//		OrderitemBean result=orderitemDAO.update(bean);
//		System.out.println(result);
		
		
//		trx.commit();
//		session.close();
//		sessionFactory.close();
//	}
	
//	@Autowired
//	private SessionFactory sessionFactory;
//	public OrderitemDAOHibernate(SessionFactory sessionFactory) {
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
	public OrderitemBean select(Integer id) {
		if(id!=null) {
			return this.getSession().get(OrderitemBean.class, id);
		}
		return null;
	}

	@Override
	public boolean insert(OrderitemBean bean) {
		if(bean!=null && bean.getOrderid()!=null ) {
			
			Integer pk =(Integer) this.getSession().save(bean);
			if (pk!=null && !pk.equals(0)) {
				return true;
			}
		}	
		return false;
	
	}
//	@Override
//	public OrderitemBean update(OrderitemBean bean) {
//		if(bean!=null && bean.getId()!=null) {
//			OrderitemBean temp = this.getSession().get(OrderitemBean.class, bean.getId());
//			if(temp!=null) {
//				return (OrderitemBean) this.getSession().merge(bean);
//			}
//		}
//		return null;
//	}
	
	
	@Override
	public List<OrderitemBean> select() {

		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<OrderitemBean> criteriaQuery = criteriaBuilder.createQuery(OrderitemBean.class);
		
		Root<OrderitemBean> root = criteriaQuery.from(OrderitemBean.class);
		
		TypedQuery<OrderitemBean> typedQuery = this.getSession().createQuery(criteriaQuery);
		List<OrderitemBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		}
		return null;
	}
	@Override
	public OrderitemBean update(Integer id, Integer orderid,
			Integer pid, String picmain, String productname, 
			Integer price, Integer qty, Double discount) {
		if(id!=null) {
			OrderitemBean temp = this.getSession().get(OrderitemBean.class, id);
			if(temp!=null) {
				temp.setId(id);
				temp.setOrderid(orderid);
				temp.setPid(pid);
				temp.setPicmain(picmain);
				temp.setProductname(productname);
				temp.setPrice(price);
				temp.setQty(qty);
				temp.setDiscount(discount);
				return temp;
			}
		}
		return null;
	}
	@Override
	public boolean delete(Integer id) {
		if(id!=null) {
			OrderitemBean temp = this.getSession().get(OrderitemBean.class, id);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}	
		return false;
		
	}
	
}
