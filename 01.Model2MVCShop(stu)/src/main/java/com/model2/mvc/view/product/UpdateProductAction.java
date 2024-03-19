package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("prodNo : " + request.getParameter("prodNo"));
		ProductVO pvo=new ProductVO();
		pvo.setProdNo(prodNo);
		pvo.setProdName(request.getParameter("prodName"));
		pvo.setProdDetail(request.getParameter("prodDetail"));
		pvo.setManuDate((request.getParameter("manuDate")).replace("-", ""));
		pvo.setPrice(Integer.parseInt(request.getParameter("price")));
		pvo.setFileName(request.getParameter("fileName"));
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(pvo);
		
		System.out.println("request prod_number : " + prodNo);
		System.out.println("getProdNo : " + pvo.getProdNo());
	
		
		return "redirect:/getProduct.do?prodNo=" + pvo.getProdNo()+"&menu=ok";
	}
}