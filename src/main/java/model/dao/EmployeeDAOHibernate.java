package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.EmployeeBean;
import model.EmployeeDAO;
import model.EmployeeinsertBean;

@Repository
public class EmployeeDAOHibernate implements EmployeeDAO {
	@Autowired
	private SessionFactory sessionFactory = null;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public EmployeeBean select(Integer id) {
		if(id!=null) {
			return this.getSession().get(EmployeeBean.class, id);
		}
		return null;
	}
	@Override
	public List<EmployeeBean> select(){
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<EmployeeBean> criteriaQuery = criteriaBuilder.createQuery(EmployeeBean.class);
		Root<EmployeeBean> root = criteriaQuery.from(EmployeeBean.class);
		TypedQuery<EmployeeBean> typedQuery = this.getSession().createQuery(criteriaQuery);
		List<EmployeeBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		}
		return null;
	}
	@Override
	public EmployeeinsertBean insert(EmployeeinsertBean bean2) {
		if(bean2!=null && bean2.getname()!=null) {
			EmployeeinsertBean temp = this.getSession().get(EmployeeinsertBean.class, bean2.getname());
			if(temp==null) {
				this.getSession().save(bean2);
				return bean2;
			}
		}
		return null;
	}
	
	public EmployeeBean update(EmployeeBean bean) {
		if(bean!=null && bean.getid()!=null) {
			EmployeeBean temp = this.getSession().get(EmployeeBean.class, bean.getid());
			if(temp!=null) {
				return (EmployeeBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public EmployeeBean update(Integer id, String englishname, String name,
			String sex, Integer age,String phone,String position) {
		if(id!=null) {
			EmployeeBean temp = this.getSession().get(EmployeeBean.class, id);
			if(temp!=null) {
				temp.setengname(englishname);
				temp.setname(name);
				temp.setgender(sex);
				temp.setage(age);
				temp.setPhone(phone);
				temp.setposition(position);
				return temp;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if(id!=null) {
			EmployeeBean temp = this.getSession().get(EmployeeBean.class, id);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

}
