package dto;

public class Category {
	
	private int id, attributeId;
	private String categoryName, attributeName;
	
	public Category() {	}

	public Category(int id, String categoryName, int attributeId, String attributeName) {
		super();
		this.id = id;
		this.attributeId = attributeId;
		this.categoryName = categoryName;
		this.attributeName = attributeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", attributeId=" + attributeId + ", categoryName=" + categoryName
				+ ", attributeName=" + attributeName + "]";
	}
	
}
