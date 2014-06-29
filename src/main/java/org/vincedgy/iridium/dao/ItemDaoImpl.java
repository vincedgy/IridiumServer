package org.vincedgy.iridium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vincedgy.iridium.model.Item;

@Service(value="ItemDao")

public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ItemDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Item> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Item")
				.list();
	}

	@Override
	@Transactional
	public Item get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Item)  session.createQuery("from Item where id=:id")
				.setLong("id", id)
				.uniqueResult();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Long save(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(item);
		session.flush();
		return item.getId();
		
	}

}
