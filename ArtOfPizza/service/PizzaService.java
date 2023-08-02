package com.aca.ArtOfPizza.service;

import java.util.List;

import com.aca.ArtOfPizza.dao.PizzaDao;
import com.aca.ArtOfPizza.dao.PizzaDaoImpl;
import com.aca.ArtOfPizza.dao.PizzaDaoMock;
import com.aca.ArtOfPizza.model.FoodGenre;
import com.aca.ArtOfPizza.model.Pizza;
import com.aca.ArtOfPizza.model.RequestError;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class PizzaService {
	
	private PizzaDao pizzaDao = new PizzaDaoImpl();
	
	public List<Pizza> getPizzas() {
		return pizzaDao.getPizzas();
	}

	public List<Pizza> getPizzasByGenre(FoodGenre genre) {
		return pizzaDao.getPizzasByGenre(genre);
	}

	public List<Pizza> getPizzaByTitle(String title) {
		validatePizzaTitle(title);
		return pizzaDao.getPizzaByTitle(title);
	}
	
	public List<Pizza> getPizzaById(Integer pizzaId) {
		validatePizzaId(pizzaId);
		return pizzaDao.getPizzaById(pizzaId);
	}

	public Pizza createPizza(Pizza newPizza) {
		return pizzaDao.createPizza(newPizza);
	}

	public List<Pizza> getPizzasByPrice(Integer price) {
		return pizzaDao.getPizzaByPrice(price);
	}

	public List<Pizza> getPizzasBySize(String size) {
		return pizzaDao.getPizzaBySize(size);
	}
	
	public Pizza updatePizza(Pizza updatePizza) {
		validatePizzaTitle(updatePizza.getTitle());
		validatePizzaId(updatePizza.getId());
		
		List<Pizza> pizzas = pizzaDao.getPizzaById(updatePizza.getId());
		if (pizzas.size() == 1) {
			return pizzaDao.updatePizza(updatePizza);
		} else {
			RequestError error = new RequestError(1,
					"Pizza id '" + updatePizza.getId() + "' does not exist");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
	}

	public Pizza deletePizzaById(Integer pizzaId) {
		return pizzaDao.deletePizzaById(pizzaId);
	}
	
	private void validatePizzaId(Integer pizzaId) {
		if (pizzaId <= 0) {
			RequestError error = new RequestError(2,
					"Invalid value for pizza Id '" + pizzaId + "'. Value must be > 0");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
	}
	
	private void validatePizzaTitle(String title) {
		if (null == title || (title.length() < 1 || title.length() > 50)) {
			RequestError error = new RequestError(3,
					"Invalid value for pizza title '" + title + "'. Value must be > 1 and < 51 or more characters");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
	}

	public List<Pizza> getPizzasByToppings(String toppings) {
		return pizzaDao.getPizzaByToppings(toppings);
	}
	
}
