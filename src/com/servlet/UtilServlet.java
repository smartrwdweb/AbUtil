package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Constants;
import com.util.Utility;

/**
 * Servlet implementation class UtilServlet
 */
@WebServlet("/UtilServlet")
public class UtilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if ("compare".equalsIgnoreCase(type)) {
			compareTwoList(request, response, out);
		}
	}

	private void compareTwoList(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		// TODO Auto-generated method stub
		String list1 = request.getParameter("list1");
		String list2 = request.getParameter("list2");
		String extFile = request.getParameter("extFile");
		String[] list1Arr = Utility.returnArray(list1, Constants.COMMA);
		String[] list2Arr = Utility.returnArray(list2, Constants.COMMA);
		Map<String, String> map = Utility.compareList(list1Arr, list2Arr, extFile);

		out.println("<strong>EQUAL</strong> : " + map.get("SIMILAR"));
		out.println("<br /><strong>UN EQUAL</strong> : " + map.get("DIFFERENT"));

	}

}
