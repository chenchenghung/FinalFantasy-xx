package model;

import java.util.List;

public interface ProductDAO {
	//查詢單筆資料
	public abstract ProductBean select(Integer id);
	//查全部資料
	public abstract List<ProductBean> select();
	//新增單筆資料
	public abstract ProductBean insert(ProductBean bean);
	//後台訂單系統可修改全部
//	public abstract ProductBean update(ProductBean bean);
	
	//06/22
	public abstract ProductBean update(Integer pid, String picmain, String picfirst, String picsecond,
			String picthird, String picfourth,String productname, String productdesc, Integer cateid,
			String spec, String material, Integer price, String size, String weight);
	
	//刪除一筆資料
	public abstract boolean delete(Integer id);
	//依分類查詢商品
	public abstract List<ProductBean> selectbycate(Integer cateid);
}
