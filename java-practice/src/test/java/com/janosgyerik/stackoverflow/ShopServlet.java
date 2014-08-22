package com.janosgyerik.stackoverflow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: koraytugay
 * Date: 20/07/14
 * Time: 18:07
 */

public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String addProductbyName = httpServletRequest.getParameter("addProductbyName");
        if (addProductbyName != null) {
            Map<String, Integer> cart = doSessionStuff(httpServletRequest);
            Integer count = cart.get(addProductbyName);
            if (count == null) {
                cart.put(addProductbyName, 1);
            } else {
                cart.put(addProductbyName, count + 1);
            }
        }

        httpServletResponse.sendRedirect(getServletContext().getContextPath()+"/shopping.jsp");
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> doSessionStuff(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new HashMap<String, Integer>());
        }
        return (Map<String, Integer>) session.getAttribute("cart");
    }
}