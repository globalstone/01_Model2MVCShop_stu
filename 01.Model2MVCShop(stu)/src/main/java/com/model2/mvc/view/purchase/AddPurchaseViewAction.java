package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseViewAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		UserVO userVO = (UserVO)session.getAttribute("user");
		
		int prodNo = Integer.parseInt(request.getParameter("prod_no"));
		
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = productDAO.findProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		request.setAttribute("userVO", userVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
