package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Report;
import com.entity.Table;
import com.opencsv.CSVReader;
import com.util.CSVHelper;
import com.util.Constants;
import com.util.Utility;

/**
 * Servlet implementation class UtilServlet
 */
@WebServlet("/UtilServlet")
public class UtilServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtilServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String type = request.getParameter("type");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if ("compare".equalsIgnoreCase(type))
		{
			compareTwoList(request, response, out);
		}
		else if ("listfiles".equalsIgnoreCase(type))
		{
			listfilesInFolder(request, response, out);
		}
		else if ("commalist".equalsIgnoreCase(type))
		{
			commalist(request, response, out);
		}
		else if ("tables".equalsIgnoreCase(type))
		{
			try
			{
				tables(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("updatetable".equalsIgnoreCase(type))
		{
			try
			{
				updateTable(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("search".equalsIgnoreCase(type))
		{
			try
			{
				searchTables(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("tblonly".equalsIgnoreCase(type))
		{
			try
			{
				tblonly(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("report_list".equalsIgnoreCase(type))
		{
			try
			{
				report_list(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("report_init".equalsIgnoreCase(type))
		{
			try
			{
				report_init(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("tables_list".equalsIgnoreCase(type))
		{
			try
			{
				report_list(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ("tables_init".equalsIgnoreCase(type))
		{
			try
			{
				report_init(request, response, out);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void report_init(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
		String type = request.getParameter("type");
		List<String> list = Utility.getReportTablesName(type);
		for (int i = 0; i < list.size(); i++)
		{
			out.println("<option value='" + list.get(i) + "'>" + list.get(i) + "</option>");
		}

	}

	private void report_list(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
		String tables = request.getParameter("tables");
		String type = request.getParameter("type");
		List<Report> list = Utility.getReportData(type, tables, null, null);
		out.println("<table class='table table-bordered'>");
		out.println("<tr>");
		out.println("<td>TABLE NAME</td>");
		out.println("<td>COLUMN NAME</td>");
		out.println("<td>Data Type</td>");
		out.println("<td>Char Length</td>");
		out.println("<td>Constraint Name</td>");
		out.println("<td>Constraint Type</td>");
		out.println("<td>Nullable</td>");
		out.println("</tr>");
		for (int i = 0; i < list.size(); i++)
		{
			Report r = list.get(i);
			out.println("<tr>");
			out.println("<td>" + r.getTable_name() + "</td>");
			out.println("<td>" + r.getColName() + "</td>");
			out.println("<td>" + r.getDataType() + "</td>");
			out.println("<td>" + r.getCharLen() + "</td>");
			out.println("<td>" + r.getConstraintName() + "</td>");
			out.println("<td>" + r.getConstraintType() + "</td>");
			out.println("<td>" + r.getNullable() + "</td>");
			out.println("</tr>");
		}

		out.println("</table>");

	}

	private void updateTable(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{

		String tbl_id = request.getParameter("tbl_id");
		String tbl_name = request.getParameter("tbl_name");
		String tbl_category = request.getParameter("tbl_category");
		String tbl_qa = request.getParameter("tbl_qa");
		String tbl_qa_temp = request.getParameter("tbl_qa_temp");
		String qa_rowcount = request.getParameter("qa_rowcount");
		String qa_temp_rowcount = request.getParameter("qa_temp_rowcount");
		String tbl_qacsvfilezie = request.getParameter("tbl_qacsvfilezie");
		String tbl_qatempcsvfilezie = request.getParameter("tbl_qatempcsvfilezie");

		Table t = new Table();
		t.setTblId(Utility.converToInt(tbl_id));
		t.setTblName(tbl_name);
		t.setTblCategory(tbl_category);
		t.setQaCsvfileSize(Utility.converToLong(tbl_qacsvfilezie));
		t.setQaTempCsvfileSize(Utility.converToLong(tbl_qatempcsvfilezie));
		t.setTblQa(Utility.converToInt(tbl_qa));
		t.setTblQaTemp(Utility.converToInt(tbl_qa_temp));
		t.setTblQaRowcount(Utility.converToLong(qa_rowcount));
		t.setTblQaTempRowcount(Utility.converToLong(qa_temp_rowcount));

		System.out.println("Table: " + t.toString());
		boolean isUpdate = Utility.updateTableData(t);
		String msg = "";
		if (isUpdate)
		{
			msg = "data of table " + t.getTblName() + " updated successfully ";
		}
		else
		{
			msg = "error occured while updating table ";
		}
		out.println(msg);

	}

	private void commalist(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{

		String tbllist = request.getParameter("tbllist");
		String view = request.getParameter("view");
		try
		{
			String[] fileList = Utility.tbllist(tbllist);

			if ("table".equalsIgnoreCase(view))
			{

				out.println("<table class='form-control table-bordered'>");
				for (String s : fileList)
				{
					out.println("<tr><td>" + s + "</td></tr>");
				}
				out.println("</table>");

			}
			else
			{
				String data = Utility.converArrStr(fileList);
				out.println("Data: " + data);

			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void tblonly(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
		String inputData = request.getParameter("searchdata");
		String sort = request.getParameter("sort");
		String tbl = request.getParameter("tbl");
		String indata = request.getParameter("indata");
		System.out.println("inputData: " + inputData);
		String inParamData = Utility.convertStringToInParam(inputData);
		List<Table> list = Utility.searchEtables(inParamData, sort, indata);
		System.out.println(list);
		for (int i = 0; i < list.size(); i++)
		{
			Table t = list.get(i);
			if (i < list.size() - 1)
			{
				out.println(t.getTblName().trim() + ",");
			}
			else
			{
				out.println(t.getTblName().trim());

			}
		}
	}

	private void searchTables(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
		String inputData = request.getParameter("searchdata");
		String sort = request.getParameter("sort");
		String indata = request.getParameter("indata");
		System.out.println("inputData: " + inputData);
		String inParamData = Utility.convertStringToInParam(inputData);
		List<Table> list = Utility.searchEtables(inParamData, sort, indata);
		System.out.println(list);
		out.println(list.size() + " tables ");
		out.println("<table class='table table-bordered'>");
		out.println(
				"<tr><td> tbl_id </td><td> tbl_name </td><td>tbl_qa  </td><td> tbl_qa_temp </td><td> qa_rowcount  </td><td> qa_temp_rowcount</td><td>Diff</td></tr>");
		for (int i = 0; i < list.size(); i++)
		{
			Table t = list.get(i);
			out.println("<tr>");
			out.println("<td>" + t.getTblId() + "</td>");
			out.println("<td>" + t.getTblName() + "</td>");
			out.println("<td>" + t.getTblQa() + "</td>");
			out.println("<td>" + t.getTblQaTemp() + "</td>");
			out.println("<td>" + t.getTblQaRowcount() + "</td>");
			out.println("<td>" + t.getTblQaTempRowcount() + "</td>");
			out.println("<td>" + (t.getTblQaRowcount() - t.getTblQaTempRowcount()) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");

	}

	private void tables(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception
	{
		// List<String[]> list = Utility.getListData();
		List<Table> list = Utility.getListEData();
		out.println("<table class='table table-bordered'>");
		out.println(
				"<tr><td> tbl_id </td><td> tbl_name </td><td> qa_rowcount  </td><td> qa_temp_rowcount</td><td>Edit  </td><td> View </td></tr>");
		for (int i = 0; i < list.size(); i++)
		{
			Table t = list.get(i);
			out.println("<tr>");
			out.println("<td>" + t.getTblId() + "</td>");
			out.println("<td>" + t.getTblName() + "</td>");

			out.println("<td>" + t.getTblQaRowcount() + "</td>");
			out.println("<td>" + t.getTblQaTempRowcount() + "</td>");
			out.println(
					"<td><a href='edittable.jsp?id=" + t.getTblId() + "' title='" + t.getTblName() + "'>Edit</a></td>");
			out.println("<td>View</td>");
			out.println("</tr>");
		}

		out.println("</table>");
	}

	private void listfilesInFolder(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
		String dir = request.getParameter("dir");
		try
		{
			Set<String> fileList = Utility.listFilesUsingFileWalkAndVisitor(dir);
			out.println("<table class='form-control table-bordered'>");
			for (String s : fileList)
			{
				out.println("<tr><td>" + s + "</td></tr>");
			}
			out.println("</table>");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void compareTwoList(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
	{
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
