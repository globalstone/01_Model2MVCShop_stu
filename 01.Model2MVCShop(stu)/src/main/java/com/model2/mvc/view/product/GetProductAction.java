package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;



public class GetProductAction extends Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        System.out.println("prodNo : " + prodNo);

        ProductService service = new ProductServiceImpl();
        ProductVO vo = service.getProduct(prodNo);
        System.out.println("vo : " + vo);

        HttpSession session = request.getSession(true);
        session.setAttribute("pvo", vo);

     // �ֱ� �� ��ǰ ������ ��Ű�� ����
		// cookies ���� ����
		Cookie[] cookies = request.getCookies();
		// prodNo�� String���� ��ȯ �� history�� ����
		String history = String.valueOf(prodNo);
		// cookie�� ã�� lookfor ������ false�� ���� false�� ã�Ҵ� ��� ��.
		boolean lookfor = false;
		// cookies �� null�� �ƴ϶�� �Ʒ� ����
		if (cookies != null) {
			//��Ű ����Ʈ�� �ϳ��� Ȯ�� Cookie cookie : cookies == int i = 0; i < cookies.length; i++
			for (Cookie cookie : cookies) {
				//��Ű �̸��� history��� �Ʒ� ����
				if (cookie.getName().equals("history")) {
					//��Ű ���� value�� ����
					String value = cookie.getValue();
					//��Ű�� �ߺ��� ����ϱ� ������ �ּ��� Ǯ�� 56������ 61������
					//if (!value.contains(history)) {
					//��ǰ ��ȣ�� ���� �� �ڿ� �߰��ϰ�, ��Ű ���� ������Ʈ�ϰ�, �� ��Ű�� �ٽ� ����� �������� ������ ����
					value += "/" + history;
					cookie.setValue(value);
					response.addCookie(cookie);
					//}
					//lookfor �� true�� �����ϰ� �ݺ��� break�� ����.
					lookfor = true;
					break;
				}
			}
		}
		//��Ű�� ��ã����?? �Ʒ� ����
		if (!lookfor) {
			//history��� ��Ű�� ���� ����� ��ǰ��ȣ�� ������ �־ �������� ����.
			Cookie cookie = new Cookie("history", history);
			response.addCookie(cookie);
		}

        String menu = request.getParameter("menu");
        System.out.println("menu : " + menu);

        if (menu.equals("search") || menu.equals("ok")) {
            return "forward:/product/readProduct.jsp";
        } else {
            return "forward:/product/updateProduct.jsp";
        }
    }
}
