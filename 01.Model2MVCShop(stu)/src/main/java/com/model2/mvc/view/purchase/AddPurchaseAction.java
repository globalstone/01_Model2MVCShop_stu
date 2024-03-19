package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String buyerId = request.getParameter("buyerId");
		String paymentOption = request.getParameter("paymentOption");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddr = request.getParameter("receiverAddr");
		String receiverRequest = request.getParameter("receiverRequest");
		String receiverDate = request.getParameter("receiverDate").replace("-", "");
		System.out.println("receiverName : " + request.getParameter("receiverName"));
		
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = productDAO.findProduct(prodNo);
		purchaseVO.setPurchaseProd(productVO);
		
		UserDAO userDAO = new UserDAO();
		UserVO userVO = userDAO.findUser(buyerId);
			
		System.out.println("userVO : " + userVO);
		purchaseVO.setBuyer(userVO);
		System.out.println("purchaseVO.getBuyer : " + purchaseVO.getBuyer());
		purchaseVO.setPaymentOption(paymentOption);
		purchaseVO.setReceiverName(receiverName);
		purchaseVO.setReceiverPhone(receiverPhone);
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		System.out.println("request.getParameter(receiverAddr) : " + request.getParameter("receiverAddr"));
		System.out.println("receiverAddr : " + receiverAddr);
		System.out.println("purchaseVO.getDivyAddr : " + purchaseVO.getDivyAddr());
		purchaseVO.setDivyRequest(receiverRequest);
		purchaseVO.setDivyDate(receiverDate);
		System.out.println("purchaseVO.getDivyDate : " + purchaseVO.getDivyDate());
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		
		request.setAttribute("buyerId", request.getParameter("buyerId"));
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/asdf.jsp";
	}

}
