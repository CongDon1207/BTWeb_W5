package vn.iotstar.services.impl;

import java.io.File;
import java.util.List;

import vn.iotstar.models.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.utils.Constant;
import vn.iotstar.dao.impl.*;
import vn.iotstar.dao.ICategoryDao;

public class CategoryServiceImpl implements ICategoryService{
	ICategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category newCategory) {
	    // Lấy thông tin của Category cũ từ CSDL
	    Category oldCategory = categoryDao.get(newCategory.getCategoryId());

	    // Cập nhật tên của Category
	    oldCategory.setCategoryname(newCategory.getCategoryname());
        oldCategory.setStatus(newCategory.getStatus());

	    // Kiểm tra xem có icon mới hay không
	    if (newCategory.getImages() != null) {
	        // Xóa ảnh cũ
	        String fileName = oldCategory.getImages();
	        File file = new File(Constant.DIR + "/category" + fileName);
	        
	        if (file.exists()) {
	            file.delete(); // Xóa file nếu tồn tại
	        }

	        // Cập nhật icon mới
	        oldCategory.setImages(newCategory.getImages());

	    }

	    // Cập nhật dữ liệu của Category vào CSDL
	    categoryDao.edit(oldCategory);
	}


	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);

	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}
	
	public static void main(String[] args) {
		
		try {
			ICategoryService cateService = new CategoryServiceImpl();
			int categoryid = 2;
	    	String categoryname = "iphone 16";
	        
	        int statuss = 0;
	        String image = "white-goods.png";
	        
	        Category category = new Category();
	        category.setCategoryId(categoryid);
	        category.setCategoryname(categoryname);
	        category.setImages(image);
	        category.setStatus(statuss);
	        
	        cateService.edit(category);
	        Category newcate = cateService.get(2);
	        System.out.print(newcate.getCategoryname());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
