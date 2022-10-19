package Model;

import java.sql.Date;

public class Fund {
 private int id;
 private String name;
 private String description;
private String content;
 public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
private String image_url;
 private int expectedResult;
 private String status;
 private Date createdDate;
 private Date endDate;
 private Category category;
 private Foundation foundation;
 public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage_url() {
	return image_url;
}
public void setImage_url(String image_url) {
	this.image_url = image_url;
}
public int getExpectedResult() {
	return expectedResult;
}
public void setExpectedResult(int expectedResult) {
	this.expectedResult = expectedResult;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public Foundation getFoundation() {
	return foundation;
}
public void setFoundation(Foundation foundation) {
	this.foundation = foundation;
}

 
}
