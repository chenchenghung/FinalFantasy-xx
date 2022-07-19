package model;

import java.util.List;

public interface OrderitemDAO {
	//查詢單筆資料
	public abstract OrderitemBean select(Integer id);
	//新增單筆資料
	public abstract boolean insert(OrderitemBean bean);
	
	public abstract OrderitemBean update(Integer id, Integer orderid,
			Integer pid,String picmain, String productname, Integer price,
			Integer qty, Double discount);
	//
	public abstract boolean delete(Integer id);
	
	public abstract List<OrderitemBean> select();
}



// 0622
//public abstract OrderitemBean update(OrderitemBean bean);