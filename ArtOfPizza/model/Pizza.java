package com.aca.ArtOfPizza.model;

public class Pizza {

	private String foodtitle;
	private String toppings;
	private FoodGenre genre;
	private String size;
	private Integer id;
	private Integer price;

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTitle() {
		return foodtitle;
	}
	public void setTitle(String title) {
		this.foodtitle = title;
	}
	public String getToppings() {
		return toppings;
	}
	public void setToppings(String toppings) {
		this.toppings = toppings;
	}
	public FoodGenre getGenre() {
		return genre;
	}
	public void setGenre(FoodGenre genre) {
		this.genre = genre;
	}
	
	
}
