package com.thisisthat.admin.productstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thisisthat.admin.productstock.service.ProductStockService;
import com.thisisthat.admin.productstock.vo.ProductStockVO;
import com.thisisthat.util.PagingVO;

@Controller
@RequestMapping("/admin/*")
public class ProductStockController {

	@Autowired
	private ProductStockService productStockService;

	@RequestMapping("getStockList.mdo")
	public String productStockList(Model model,@RequestParam(value="searchOption")String searchOption,
			@RequestParam(value="nowPage", required = false)Integer nowPage
			,ProductStockVO productStockVO){
		if(nowPage == null) nowPage = 1;
		productStockVO.setProduct_category(searchOption);
		PagingVO paging = new PagingVO(productStockService.getCount(productStockVO), nowPage, 15);
		List<ProductStockVO> prodcutStockList = productStockService.poductStockList(productStockVO, paging);
		
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("paging",paging);
		model.addAttribute("prodcutStockList", prodcutStockList);
		return "/admin/product_stock/productStockList";




	}

	@RequestMapping("getProductStockList.mdo")
	public String getProductStockList(Model model) {
		ProductStockVO productStockVO = new ProductStockVO();
		List<ProductStockVO> prodcutStockList = productStockService.stockList(productStockVO);

		model.addAttribute("prodcutStockList", prodcutStockList);
		return "/admin/product_stock/productStockList";
	}// 재고관리 리스트

	@RequestMapping("getProductStock.mdo")
	public String getProductStock(Model model,@RequestParam(value="product_no")int product_no, ProductStockVO productStockVO) {
		productStockVO.setProduct_no(product_no);
		ProductStockVO getStock =  productStockService.getStock(productStockVO);

		model.addAttribute("article", getStock);
		return "/admin/product_stock/productStock";
	}//상세보기

	@RequestMapping("updateStock.mdo")
	public String updateProductStock(@RequestParam(value="product_no")int product_no, @RequestParam(value="xs")int xs, @RequestParam(value="s")int s, @RequestParam(value="m")int m, @RequestParam(value="l")int l, @RequestParam(value="xl")int xl, ProductStockVO productStockVO) {
		productStockService.updateStock(productStockVO);


		return "redirect:productStockList.mdo";
	}

}// end class
