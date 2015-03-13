package com.sobadeveloper.listviewdemo;

import java.io.Serializable;

/**
 * X402.41 - Advanced Application for Android (Fall 2014)
 * Assignment 1
 * Country.java
 * Purpose: Contains all the country info objects
 * @author Levi Hsiao
 *
 */
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String capital;
	private String population;
	private String area;
	private String gdp;
	
	//CONSTRUCTORS
	public Country(){
	}
	
	public Country(String name, String capital, String population, String area, String gdp){
		this.name = name;
		this.capital = capital;
		this.population = population;
		this.area = area;
		this.gdp = gdp;
	}

	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}


	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGdp() {
		return gdp;
	}

	public void setGdp(String gdp) {
		this.gdp = gdp;
	}
}
