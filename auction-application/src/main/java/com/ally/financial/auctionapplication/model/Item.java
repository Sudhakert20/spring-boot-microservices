package com.ally.financial.auctionapplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	private String itemId;
	private String description;

	public Item() {
	}

	public Item(String itemId, String description) {
		this.itemId = itemId;
		this.description = description;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", description=" + description + "]";
	}

}
