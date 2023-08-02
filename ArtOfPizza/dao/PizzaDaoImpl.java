package com.aca.ArtOfPizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aca.ArtOfPizza.model.FoodGenre;
import com.aca.ArtOfPizza.model.Pizza;



public class PizzaDaoImpl implements PizzaDao{
	
	private static String selectAllPizzas =
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza ";
	
	private static String selectPizzasByGenre = 
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE genreId = ? ";
	
	private static String selectPizzasByTitle =
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE title LIKE %?% ";
	
	private static String selectPizzasById = 
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE id = ? ";

	private static String selectPizzasBySize =
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE size = ? ";
	
	private static String selectPizzasByToppings =
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE toppings LIKE ? ";
	
	private static String selectPizzasByPrice = 
			"SELECT id, title, genreId, toppings, size, price " +
			"FROM pizza " +
			"WHERE price = ? ";
	
	private static String deletePizzaById = 
			"DELETE FROM pizza " +
			"WHERE id = ? " ;
	
	private static String updatePizzasById =
			"UPDATE pizza " +
			"SET title = ?, " +
			"	genreId = ?, " + 
			"	toppings = ?, " + 
			"	size = ?, " + 
			"	price = ? " + 
			"WHERE id = ? "; 
	
	private static String insertPizza =
			"INSERT INTO pizza (title, genreId, toppings, size, price) " +
			"VALUES " +
			"(?,?,?,?,?)" ;
	
	private static String selectNewPizzaId =
			"SELECT LAST_INSERT_ID() AS pizzaId";
	
	@Override
	public List<Pizza> getPizzas() {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllPizzas);
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	private List<Pizza> makePizza(ResultSet result) throws SQLException {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		
		while(result.next()) {
			Pizza pizza = new Pizza();
			pizza.setId(result.getInt("id"));
			pizza.setTitle(result.getString("title"));
			pizza.setPrice(result.getInt("price"));
			pizza.setToppings(result.getString("toppings"));
			pizza.setSize(result.getString("size"));
			String genreString = result.getString("genreId");
			FoodGenre myGenre = FoodGenre.convertStringToGenre(genreString);
			pizza.setGenre(myGenre);
			myPizzas.add(pizza);
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzasByGenre(FoodGenre genre) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasByGenre);
			ps.setString(1, genre.toString());
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaByTitle(String title) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasByTitle);
			ps.setString(1, "%" + title + "%");
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaById(Integer pizzaId) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasById);
			ps.setInt(1, pizzaId);
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaByPrice(Integer pizzaPrice) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasByPrice);
			ps.setInt(1, pizzaPrice);
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	@Override
	public List<Pizza> getPizzaBySize(String pizzaSize) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasBySize);
			ps.setString(1, pizzaSize);
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

	@Override
	public Pizza createPizza(Pizza newPizza) {
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(insertPizza);
			ps.setString(1,  newPizza.getTitle());
			ps.setString(2, newPizza.getGenre().toString());
			ps.setInt(5, newPizza.getPrice());
			ps.setString(4, newPizza.getSize());
			ps.setString(3, newPizza.getToppings());
			int updateRowCount = ps.executeUpdate();
			System.out.println("rows inserted: " + updateRowCount);
			int movieId = getNewPizzaId(conn);
			newPizza.setId(movieId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return newPizza;
	}

	private int getNewPizzaId(Connection conn) {
		ResultSet rs = null;
		Statement statement = null;
		int newPizzaId = 0;
		
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectNewPizzaId);
			while(rs.next()) {
				newPizzaId = rs.getInt("pizzaId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newPizzaId;
	}

	@Override
	public Pizza updatePizza(Pizza updatePizza) {
		List<Pizza> myPizzas = getPizzaById(updatePizza.getId());
		
		if (myPizzas.size() == 1) {
			PreparedStatement ps = null;
			
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(updatePizzasById);
			ps.setString(1,  updatePizza.getTitle());
			ps.setString(2, updatePizza.getGenre().toString());
			ps.setInt(6, updatePizza.getId());
			ps.setInt(5, updatePizza.getPrice());
			ps.setString(4, updatePizza.getSize());
			ps.setString(3, updatePizza.getToppings());
			
			int updateRowCount = ps.executeUpdate();
			System.out.println("rows updated: " + updateRowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return updatePizza;
	}

	@Override
	public Pizza deletePizzaById(Integer pizzaId) {
		List<Pizza> myPizzas = getPizzaById(pizzaId);
		Pizza pizzaToDelete = null;
		
		if (myPizzas.size() == 1) {
			pizzaToDelete = myPizzas.get(0);
			PreparedStatement ps = null;
			
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(deletePizzaById);
			ps.setInt(1, pizzaId);
			int updateRowCount = ps.executeUpdate();
			System.out.println("rows deleted: " + updateRowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return pizzaToDelete;
	}

	@Override
	public List<Pizza> getPizzaByToppings(String pizzaToppings) {
		List<Pizza> myPizzas = new ArrayList<Pizza>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPizzasByToppings);
			ps.setString(1, "%" + pizzaToppings + "%");
			result = ps.executeQuery();
			myPizzas = makePizza(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPizzas;
	}

}
