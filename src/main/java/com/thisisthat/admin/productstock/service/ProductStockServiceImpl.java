package com.thisisthat.admin.productstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thisisthat.admin.productstock.dao.ProductStockDAO;
import com.thisisthat.admin.productstock.vo.ProductStockVO;
import com.thisisthat.util.PagingVO;

@Service("ProductStockService")
public class ProductStockServiceImpl implements ProductStockService {
	@Autowired
	private ProductStockDAO product_StockDAO;

	@Override
	public List<ProductStockVO> stockList(ProductStockVO productStockVO) {
		
		return product_StockDAO.getStockList(productStockVO);
	}

	@Override
	public ProductStockVO getStock(ProductStockVO productStockVO) {
		
		return product_StockDAO.getProductStock(productStockVO);
	}

	@Override
	public void updateStock(ProductStockVO productStockVO) {
		product_StockDAO.updateStock(productStockVO);
		
	}

	@Override
	public List<ProductStockVO> poductStockList(ProductStockVO productStockVO,PagingVO paging) {
		return product_StockDAO.getProductStockList(productStockVO, paging);
	}

	@Override
	public int getCount(ProductStockVO productStockVO) {
		return product_StockDAO.getCount(productStockVO);
	}

	@Override
	public List<ProductStockVO> allList(ProductStockVO productStockVO) {
		return product_StockDAO.allList(productStockVO);
	}

	@Override
	public List<ProductStockVO> productnoList(ProductStockVO productStockVO) {
		return product_StockDAO.productnoList(productStockVO);
	}

	@Override
	public List<ProductStockVO> productnameList(ProductStockVO productStockVO) {
		return product_StockDAO.productnameList(productStockVO);
	}

	@Override
	public ProductStockVO sizepro(ProductStockVO productStockVO) {
		return product_StockDAO.sizepro(productStockVO);
	}
	
	
}
