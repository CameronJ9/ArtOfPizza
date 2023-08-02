package com.aca.ArtOfPizza.model;

public enum FoodGenre {
	DeepDish, ThinCrust, HandTossed, ThickCrust; 
	
	public static FoodGenre convertStringToGenre(String value) {
		FoodGenre myGenre = null;
		
		for (FoodGenre genre : FoodGenre.values()) {
			if (genre.toString().equalsIgnoreCase(value)) {
				myGenre = genre;
				break;
			}
		}
		
		return myGenre;
	}
	
}
