package org.vincedgy.iridium;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vincedgy.iridium.dao.ItemDao;
import org.vincedgy.iridium.model.Item;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemDaoTest {

	@Autowired
	private ItemDao itemDao;
	
	@Test
	public void testGetAll() {
		assertTrue(itemDao.getAll().size() > 0);
	}

	@Test
	public void testGet() {
		Item item = itemDao.get(1L);
		org.junit.Assert.assertNotNull(item);
	}

	@Test
	public void testSave() {
		Item item = new Item();
		item.setPrice(2.2);
		item.setProduct("Yahourt");
		item.setQuantity(2);
		
		Long newId = itemDao.save(item);
		
		item = null;
		item = itemDao.get(newId);
		
		org.junit.Assert.assertNotNull(item);
		System.out.println(item);
		
	}

}
