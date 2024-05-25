package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dto.Category;

public class CategoriesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Category> categories;

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

}
