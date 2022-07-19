package model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeDAO employeeDao;
	
	public List<EmployeeBean> select(EmployeeBean bean){
		List<EmployeeBean> result = null;
		if(bean!=null && bean.getid()!=null && !bean.getid().equals(0)) {
			EmployeeBean temp = employeeDao.select(bean.getid());
			if(temp != null) {
				result = new ArrayList<EmployeeBean>();
				result.add(temp);
			}
		}
		else {
			result = employeeDao.select();
		}
		return result;
	}
	public EmployeeinsertBean insert(EmployeeinsertBean bean2) {
		EmployeeinsertBean result = null;
		if(bean2!=null && bean2.getname()!=null) {
			result = employeeDao.insert(bean2);
		}
		return result;
	}
	public EmployeeBean update(EmployeeBean bean) {
		EmployeeBean result = null;
		if(bean!=null && bean.getid()!=null) {
			result = employeeDao.update(bean.getid(), bean.getengname(),
					bean.getname(), bean.getsex(), bean.getage(), bean.getPhone() ,bean.getposition());
		}
		return result;
	}
	public boolean delete(EmployeeBean bean) {
		boolean result = false;
		if(bean!=null && bean.getid()!=null) {
			result = employeeDao.delete(bean.getid());
		}
		return result;
	}
	
}
