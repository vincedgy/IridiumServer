package org.vincedgy.iridium.dao;

import java.util.List;

import org.vincedgy.iridium.model.Item;

public interface ItemDao {

	public abstract List<Item> getAll();

	public abstract Item get(Long id);

	public abstract Long save(Item item);

}