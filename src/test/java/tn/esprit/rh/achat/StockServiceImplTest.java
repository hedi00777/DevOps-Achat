package tn.esprit.rh.achat;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Stock;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.services.IStockService;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class StockServiceImplTest {
	@Autowired
	IStockService stockService;
	
	@Test
	@Order(1)
	public void testAddStock() {
	List<Stock> stocks = stockService.retrieveAllStocks();
	int expected=stocks.size();
		Stock s = new Stock("stock test",10,100);
		Stock savedStock= stockService.addStock(s);
		
		assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		//stockService.deleteStock(savedStock.getIdStock());
		
	} 
	@Test
	@Order(2)
	public void testAddStockOptimized() {

		Stock s = new Stock("stock test",10,100);
		Stock savedStock= stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		//stockService.deleteStock(savedStock.getIdStock());
		
	} 
	
	@Test
	@Order(3)
	public void testDeleteStock() {
		Stock s = new Stock("stock test",30,60);
		Stock savedStock= stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}


}
