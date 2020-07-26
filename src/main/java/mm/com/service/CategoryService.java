package mm.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mm.com.entity.Category;
import mm.com.repository.CategoryRepository;
 
@Service
public class CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAllInOrderByName() {
		List<Category> rs = categoryRepository.findAllInOrderByName();
		return rs;
	}
	
	public List<Category> findAllOutOrderByName() {
		List<Category> rs = categoryRepository.findAllOutOrderByName();
		return rs;
	}
	
	public Category save(Category category) {
		categoryRepository.save(category);
		return category;
	}
	
	public void delete(long[] ids) {
		for(long item: ids) {
			categoryRepository.deleteById(item);
		}	
	}
	
	public Category findById(long id) {
		return categoryRepository.findById(id).get();
	}
}
