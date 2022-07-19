package model;

import java.util.List;

public interface EmployeeDAO {
	public abstract EmployeeBean select(Integer id);
	
	public abstract List<EmployeeBean> select();
	
	public abstract EmployeeinsertBean insert(EmployeeinsertBean bean2);

	public abstract EmployeeBean update(Integer id, String englishname, String name,
			String sex, Integer age, String phone, String position);

	public abstract boolean delete(Integer id);
}
