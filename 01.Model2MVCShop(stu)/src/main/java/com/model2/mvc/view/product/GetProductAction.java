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

     // 최근 본 상품 정보를 쿠키에 저장
		// cookies 변수 저장
		Cookie[] cookies = request.getCookies();
		// prodNo를 String으로 변환 후 history에 저장
		String history = String.valueOf(prodNo);
		// cookie를 찾는 lookfor 변수를 false로 선언 false는 찾았다 라는 뜻.
		boolean lookfor = false;
		// cookies 가 null이 아니라면 아래 실행
		if (cookies != null) {
			//쿠키 리스트를 하나씩 확인 Cookie cookie : cookies == int i = 0; i < cookies.length; i++
			for (Cookie cookie : cookies) {
				//쿠키 이름이 history라면 아래 실행
				if (cookie.getName().equals("history")) {
					//쿠키 값을 value에 저장
					String value = cookie.getValue();
					//쿠키의 중복을 허용하기 싫으면 주석을 풀자 56번라인 61번라인
					//if (!value.contains(history)) {
					//상품 번호를 기존 값 뒤에 추가하고, 쿠키 값을 업데이트하고, 그 쿠키를 다시 사용자 브라우저로 보내서 저장
					value += "/" + history;
					cookie.setValue(value);
					response.addCookie(cookie);
					//}
					//lookfor 값 true로 변경하고 반복문 break로 멈춤.
					lookfor = true;
					break;
				}
			}
		}
		//쿠키를 못찾으면?? 아래 실행
		if (!lookfor) {
			//history라는 쿠키를 새로 만들고 상품번호를 값으로 넣어서 브라우저에 저장.
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
