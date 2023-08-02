package com.aca.ArtOfPizza.dao;

import java.util.List;

import com.aca.ArtOfPizza.model.FoodGenre;
import com.aca.ArtOfPizza.model.Pizza;


public interface PizzaDao {

	public List<Pizza> getPizzas();
	public List<Pizza> getPizzasByGenre(FoodGenre genre);
	public List<Pizza> getPizzaByTitle(String title);
	public List<Pizza> getPizzaById(Integer pizzaId);
	public List<Pizza> getPizzaByToppings(String pizzaToppings);
	public List<Pizza> getPizzaByPrice(Integer pizzaPrice);
	public List<Pizza> getPizzaBySize(String pizzaSize);
	public Pizza createPizza(Pizza newPizza);
	public Pizza updatePizza(Pizza updatePizza);
	public Pizza deletePizzaById(Integer pizzaId);
}
