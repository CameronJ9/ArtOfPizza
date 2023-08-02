package com.aca.ArtOfPizza.dao;

import java.util.ArrayList;
import java.util.List;

import com.aca.ArtOfPizza.model.FoodGenre;
import com.aca.ArtOfPizza.model.Pizza;
import com.aca.ArtOfPizza.dao.PizzaDaoMock;

public class PizzaDaoMock implements PizzaDao {
	
	private static Integer lastPizzaId = 0;
	
	private static Integer getNextPizzaId() {
		lastPizzaId++;
		return lastPizzaId;
	}
	
	private static List<Pizza> pizzas = new ArrayList<Pizza>();
	
	static {
		Pizza pepperoni = new Pizza();
		pepperoni.setTitle("Pepperoni");
		pepperoni.setGenre(FoodGenre.ThickCrust);
		pepperoni.setToppings("Pepperonies, Cheese, Oregano, Basil");
		pepperoni.setId(getNextPizzaId());
		pepperoni.setPrice(8);
		pepperoni.setSize("small");
		
		
		Pizza classicChicago = new Pizza();
		classicChicago.setTitle("Classic Chigago");
		classicChicago.setGenre(FoodGenre.DeepDish);
		classicChicago.setToppings("Pepperoni, Sausage, Cheese, Mozzarella, Parmesan, Sugar, Oregano, Basil");
		classicChicago.setId(getNextPizzaId());
		classicChicago.setPrice(10);
		classicChicago.setSize("large");
		
		
		Pizza louMalnati = new Pizza();
		louMalnati.setTitle("Lou Malnati's Deep Dish");
		louMalnati.setGenre(FoodGenre.DeepDish);
		louMalnati.setToppings("Ricotta, Parmesan, Gorgonzola, Mozzerella, Oregano, Basil");
		louMalnati.setId(getNextPizzaId());
		louMalnati.setPrice(7);
		louMalnati.setSize("large");
		
		
		Pizza sausage = new Pizza();
		sausage.setTitle("Sausage");
		sausage.setGenre(FoodGenre.ThickCrust);
		sausage.setToppings("Sausage, Cheese, Oregano, Basil");
		sausage.setId(getNextPizzaId());
		sausage.setPrice(8);
		sausage.setSize("small");
		
		
		Pizza neapolitan = new Pizza();
		neapolitan.setTitle("Neapolitan");
		neapolitan.setGenre(FoodGenre.ThinCrust);
		neapolitan.setToppings("Pepperonies, Cheese, Oregano, Basil");
		neapolitan.setId(getNextPizzaId());
		neapolitan.setPrice(11);
		neapolitan.setSize("medium");
	
		
		Pizza napoletana = new Pizza();
		napoletana.setTitle("Napoletana");
		napoletana.setGenre(FoodGenre.ThinCrust);
		napoletana.setToppings("Pepperonies, Cheese, Oregano, Basil, Spinach");
		napoletana.setId(getNextPizzaId());
		napoletana.setPrice(10);
		napoletana.setSize("medium");
		
		Pizza domminos = new Pizza();
		domminos.setTitle("Domminos");
		domminos.setGenre(FoodGenre.HandTossed);
		domminos.setToppings("Pepperonies, Cheese, Oregano, Basil");
		domminos.setId(getNextPizzaId());
		domminos.setPrice(11);
		domminos.setSize("large");
		
		Pizza pizzahut = new Pizza();
		pizzahut.setTitle("Pizza Hut");
		pizzahut.setGenre(FoodGenre.HandTossed);
		pizzahut.setToppings("Sausage, Cheese, Oregano, Basil");
		pizzahut.setId(getNextPizzaId());
		pizzahut.setPrice(10);
		pizzahut.setSize("large");
		
		pizzas.add(napoletana);
		pizzas.add(neapolitan);
		pizzas.add(sausage);
		pizzas.add(pepperoni);
		pizzas.add(pizzahut);
		pizzas.add(domminos);
		pizzas.add(louMalnati);
		pizzas.add(classicChicago);
		
	}

	@Override
	public List<Pizza> getPizzas() {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		myPizzas.addAll(pizzas);
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzasByGenre(FoodGenre genre) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for (Pizza pizza : PizzaDaoMock.pizzas) {
			if (pizza.getGenre().equals(genre)) {
				myPizzas.add(pizza);
			}
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaByTitle(String title) {
	List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for (Pizza pizza : PizzaDaoMock.pizzas) {
			if (pizza.getTitle().toLowerCase().contains(title.toLowerCase())) {
				myPizzas.add(pizza);
			}
		}
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaById(Integer pizzaId) {
	List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for (Pizza pizza : PizzaDaoMock.pizzas) {
			if (pizza.getId().intValue() == pizzaId.intValue()) {
				myPizzas.add(pizza);
				break;
			}
		}
		
		return myPizzas;
	}

	@Override
	public Pizza createPizza(Pizza newPizza) {
		newPizza.setId(getNextPizzaId());
		PizzaDaoMock.pizzas.add(newPizza);
		return newPizza;
	}

	@Override
	public List<Pizza> getPizzaByPrice(Integer pizzaPrice) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for(Pizza pizza : PizzaDaoMock.pizzas ) {
			if (pizza.getPrice() >= pizzaPrice - 5 && pizza.getPrice() <= pizzaPrice + 5) {
				myPizzas.add(pizza);
			}
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaBySize(String pizzaSize) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for(Pizza pizza : PizzaDaoMock.pizzas ) {
			if (pizza.getSize().equals(pizzaSize)) {
				myPizzas.add(pizza);
			}
		}
		
		return myPizzas;
	}

	@Override
	public Pizza updatePizza(Pizza updatePizza) {
		for (Pizza pizza : PizzaDaoMock.pizzas) {
			if(pizza.getId().intValue() == updatePizza.getId().intValue()) {
				pizza.setPrice(updatePizza.getPrice());
				pizza.setTitle(updatePizza.getTitle());
				pizza.setId(updatePizza.getId());
				pizza.setGenre(updatePizza.getGenre());
				pizza.setSize(updatePizza.getSize());
				pizza.setToppings(updatePizza.getToppings());
			}
		}
		
		return updatePizza;
	}

	@Override
	public Pizza deletePizzaById(Integer pizzaId) {
		List<Pizza> pizzas = getPizzaById(pizzaId);
		Pizza pizza = null;
		
		if (pizzas.size() == 1) {
			pizza = pizzas.get(0);
			PizzaDaoMock.pizzas.remove(pizza);
		}
		
		return pizza;
	}

	@Override
	public List<Pizza> getPizzaByToppings(String pizzaToppings) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		for (Pizza pizza : PizzaDaoMock.pizzas) {
			if (pizza.getToppings().equals(pizzaToppings)) {
				myPizzas.add(pizza);
			}
		}
		
		return myPizzas;
	}

}
