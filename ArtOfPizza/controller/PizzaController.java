package com.aca.ArtOfPizza.controller;

import java.util.List;

import com.aca.ArtOfPizza.model.FoodGenre;
import com.aca.ArtOfPizza.model.Pizza;
import com.aca.ArtOfPizza.service.PizzaService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pizza")
public class PizzaController {

	private PizzaService service = new PizzaService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzas() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service.getPizzas();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Pizza createPizza(Pizza newPizza) {
		return service.createPizza(newPizza);
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Pizza updatePizza(Pizza updatePizza) {
		return service.updatePizza(updatePizza);
	}
	
	@GET
	@Path("/price/{foodpriceValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasByPrice(@PathParam("foodpriceValue") Integer price) {
		return service.getPizzasByPrice(price);
	}
	
	@GET
	@Path("/size/{foodsizeValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasBySize(@PathParam("foodsizeValue") String size) {
		return service.getPizzasBySize(size);
	}
	
	
	@GET
	@Path("/genre/{genreValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasByGenre(@PathParam("genreValue") FoodGenre genre) {
		return service.getPizzasByGenre(genre);
	}
	
	@GET
	@Path("/toppings/{foodtoppingsValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasByToppings(@PathParam("foodtoppingsValue") String toppings) {
		return service.getPizzasByToppings(toppings);
	}
	
	
	@DELETE
	@Path("/{pizzaIdValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pizza deletePizzasById(@PathParam("pizzaIdValue") Integer pizzaId) {
		return service.deletePizzaById(pizzaId);
	}
	
	@GET
	@Path("/{pizzaIdValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasById(@PathParam("pizzaIdValue") Integer pizzaId) {
		return service.getPizzaById(pizzaId);
	}
	
	@GET
	@Path("/title/{titleValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzasByTitle(@PathParam("titleValue") String title) {
		return service.getPizzaByTitle(title);
	}
}
