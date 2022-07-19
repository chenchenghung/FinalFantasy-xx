package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductService {
	
	// TODO Service 還沒測試
//	public static void main(String[] args) {
//	
//
//	}
	@Autowired
	private ProductDAO productDao;

	public List<ProductBean> selectfr(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null) {
			ProductBean temp = productDao.select(bean.getPid());
			//查詢單筆商品返回list
			if(temp!=null && bean.getPid()!=null && !bean.getPid().equals(0)) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
			//依分類查詢所有商品
			if(bean.getCateid()!=null){
				result=productDao.selectbycate(bean.getCateid());
			}
			//查詢所有商品
		} else {
			result = productDao.select(); 
		}
		return result;
	}
	
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getPid()!=null && !bean.getPid().equals(0)) {
			ProductBean temp = productDao.select(bean.getPid());
			if(temp!=null) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = productDao.select(); 
		}
		return result;
	}
	
//	public ProductBean update(ProductBean bean) {
//		ProductBean result = null;
//		if(bean!=null && bean.getPid()!=null) {
//			result = productDao.update(bean);
//		}
//		return result;
//	}
	//06/22
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null && bean.getPid()!=null) {
			result = productDao.update(bean.getPid(), bean.getPicmain(), bean.getPicfirst(), bean.getPicsecond(),
					bean.getPicthird(), bean.getPicfourth(),bean.getProductname(), bean.getProductdesc(), 
					bean.getCateid(), bean.getSpec(), bean.getMaterial(), bean.getPrice(), bean.getSize()
					,bean.getWeight());
		}
		return result;
	}
	
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.insert(bean);
		}
		return result;
	}
	
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null && bean.getPid()!=null) {
			result = productDao.delete(bean.getPid());
		}
		return result;
	}
}
