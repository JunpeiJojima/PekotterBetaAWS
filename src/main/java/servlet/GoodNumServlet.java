package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GoodLogic;

@WebServlet("/GoodNumServlet")
public class GoodNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		GoodLogic good = new GoodLogic();
		good.goodNumPlus(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Main");	
		dispatcher.forward(request, response);
	}

}
