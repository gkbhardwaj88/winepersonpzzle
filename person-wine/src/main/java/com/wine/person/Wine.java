package com.wine.person;
/**
 * 
 * @author gautam
 *
 */
public class Wine {
	private String wineId;

	public String getWineId() {
		return wineId;
	}

	public void setWineId(String wineId) {
		this.wineId = wineId;
	}

	public Wine(String wineId) {
		super();
		this.wineId = wineId;
	}

	@Override
	public String toString() {
		return "Wine [wineId=" + wineId + "]";
	}
	
}
