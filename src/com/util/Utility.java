package com.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.entity.Report;
import com.entity.Table;

public class Utility
{
	public static void closeRs(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closePs(PreparedStatement ps)
	{
		if (ps != null)
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeConn(Connection conn)
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * String[] a = returnArray("A,B,C", ","); String[] b = returnArray("A,B,C,D,E",
	 * ","); System.out.println(compareList(a, b, null));
	 * 
	 * }
	 */

	public static Long convertToLong(String str)
	{
		Long bd = null;
		if (null == str || "".equalsIgnoreCase(str))
		{
			bd = new Long(0);
		}
		else
		{
			bd = new Long(str);
		}
		return bd;
	}

	public static Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException
	{
		Set<String> fileList = new HashSet<>();
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (!Files.isDirectory(file))
				{
					fileList.add(file.getFileName().toString());
				}
				return FileVisitResult.CONTINUE;
			}
		});
		return fileList;
	}

	public static String[] returnArray(String data, String regex)
	{
		String[] str = null;
		if (null != data && !"".equalsIgnoreCase(data))
		{
			if (null != regex && !"".equalsIgnoreCase(regex))
			{
				str = data.split(regex);
			}
		}
		return str;
	}

	public static Map<String, String> compareList(String[] list1, String[] list2, String ext)
	{

		Map<String, String> map = new HashMap<>();
		Collection<String> listOne = Arrays.asList(list1);

		Collection<String> listTwo = Arrays.asList(list2);

		Collection<String> similar = new HashSet<String>(listOne);
		Collection<String> different = new HashSet<String>();
		different.addAll(listOne);
		different.addAll(listTwo);

		similar.retainAll(listTwo);
		different.removeAll(similar);
		map.put("SIMILAR", similar.toString());
		map.put("DIFFERENT", different.toString());
		return map;
	}

	public static List<Table> getListEData()
	{
		List<Table> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = "select * from g_tables";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Table t = new Table();

				t.setTblId(rs.getInt("tbl_id"));
				t.setTblName(rs.getString("tbl_name"));
				t.setTblCategory(rs.getString("tbl_category"));
				t.setTblQa(rs.getInt("tbl_qa"));
				t.setTblQaTemp(rs.getInt("tbl_qa_temp"));
				t.setTblQaRowcount(rs.getLong("qa_rowcount"));
				t.setTblQaTempRowcount(rs.getLong("qa_temp_rowcount"));
				t.setQaCsvfileSize(rs.getLong("tbl_qacsvfilezie"));
				t.setQaTempCsvfileSize(rs.getLong("tbl_qatempcsvfilezie"));

				// System.out.println(str[1]+" " + str[2] + " " + str[3] + " " + str[4] + " "
				// +str[5]);
				list.add(t);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static List<Table> getListEDataById(String identity, String type)
	{
		List<Table> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = "select * from g_tables where id=" + identity;
			if (null != type && !"".equalsIgnoreCase(type) && "name".equalsIgnoreCase(type))
			{
				sql = "select * from g_tables where name like '%" + identity + "%'";
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Table t = new Table();

				t.setTblId(rs.getInt("tbl_id"));
				t.setTblName(rs.getString("tbl_name"));
				t.setTblCategory(rs.getString("tbl_category"));
				t.setTblQa(rs.getInt("tbl_qa"));
				t.setTblQaTemp(rs.getInt("tbl_qa_temp"));
				t.setTblQaRowcount(rs.getLong("qa_rowcount"));
				t.setTblQaTempRowcount(rs.getLong("qa_temp_rowcount"));
				t.setQaCsvfileSize(rs.getLong("tbl_qacsvfilezie"));
				t.setQaTempCsvfileSize(rs.getLong("tbl_qatempcsvfilezie"));

				// System.out.println(str[1]+" " + str[2] + " " + str[3] + " " + str[4] + " "
				// +str[5]);
				list.add(t);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static Long converToLong(String str)
	{

		if (null == str || "".equalsIgnoreCase(str))
		{
			return 0L;
		}
		return Long.parseLong(str);
	}

	public static Integer converToInt(String str)
	{

		if (null == str || "".equalsIgnoreCase(str))
		{
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static List<Table> getListEDataById(int id)
	{
		List<Table> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = "select * from g_tables where tbl_id=" + id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Table t = new Table();

				t.setTblId(rs.getInt("tbl_id"));
				t.setTblName(rs.getString("tbl_name"));
				t.setTblCategory(rs.getString("tbl_category"));
				t.setTblQa(rs.getInt("tbl_qa"));
				t.setTblQaTemp(rs.getInt("tbl_qa_temp"));
				t.setTblQaRowcount(rs.getLong("qa_rowcount"));
				t.setTblQaTempRowcount(rs.getLong("qa_temp_rowcount"));
				t.setQaCsvfileSize(rs.getLong("tbl_qacsvfilezie"));
				t.setQaTempCsvfileSize(rs.getLong("tbl_qatempcsvfilezie"));

				// System.out.println(str[1]+" " + str[2] + " " + str[3] + " " + str[4] + " "
				// +str[5]);
				list.add(t);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static String getInParam(String[] data)
	{
		StringBuilder str = new StringBuilder();
		if (null != data && data.length > 0)
		{
			for (int i = 0; i < data.length; i++)
			{
				if (i < data.length - 1)
				{
					str.append("'" + data[i] + "',");
				}
				else
				{
					str.append("'" + data[i] + "'");
				}
			}
		}
		return str.toString();
	}

	public static String convertStringToInParam(String strData)
	{
		StringBuilder str = new StringBuilder();
		String data[] = strData.split(Constants.COMMA);
		if (null != data && data.length > 0)
		{
			for (int i = 0; i < data.length; i++)
			{
				if (i < data.length - 1)
				{
					str.append("'" + data[i] + "',");
				}
				else
				{
					str.append("'" + data[i] + "'");
				}
			}
		}
		return str.toString();
	}

	public static List<Table> searchEtables(String tables, String sort, String indata)
	{
		List<Table> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = null;
			if (null != tables && !"".equalsIgnoreCase(tables))
			{
				sql = "select * from g_tables where tbl_name " + indata + " (" + tables + ") order by qa_rowcount "
						+ sort;
			}
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			// ps.setString(1, tables);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Table t = new Table();

				t.setTblId(rs.getInt("tbl_id"));
				t.setTblName(rs.getString("tbl_name"));
				t.setTblCategory(rs.getString("tbl_category"));
				t.setTblQa(rs.getInt("tbl_qa"));
				t.setTblQaTemp(rs.getInt("tbl_qa_temp"));
				t.setTblQaRowcount(rs.getLong("qa_rowcount"));
				t.setTblQaTempRowcount(rs.getLong("qa_temp_rowcount"));
				t.setQaCsvfileSize(rs.getLong("tbl_qacsvfilezie"));
				t.setQaTempCsvfileSize(rs.getLong("tbl_qatempcsvfilezie"));

				// System.out.println(str[1]+" " + str[2] + " " + str[3] + " " + str[4] + " "
				// +str[5]);
				list.add(t);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static boolean updateTableData(Table table)
	{

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = "update g_tables set tbl_name = ?, tbl_category = ?, tbl_qa = ?, tbl_qa_temp = ?, qa_rowcount = ?, qa_temp_rowcount = ?, tbl_qacsvfilezie = ?, tbl_qatempcsvfilezie = ? "
					+ " where tbl_id = ?";
			System.out.println(sql.toString());
			System.out.println("Util: " + table.toString());
			ps = conn.prepareStatement(sql);

			ps.setString(1, table.getTblName());
			ps.setString(2, table.getTblCategory());
			ps.setInt(3, table.getTblQa());
			ps.setInt(4, table.getTblQaTemp());
			ps.setLong(5, table.getTblQaRowcount());
			ps.setLong(6, table.getTblQaTempRowcount());
			ps.setLong(7, table.getQaCsvfileSize());
			ps.setLong(8, table.getTblQaTempRowcount());
			ps.setInt(9, table.getTblId());

			result = ps.executeUpdate();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return result > 0;

	}

	public static List<String[]> getListData()
	{
		List<String[]> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = "select * from g_tables";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				String[] str = new String[6];
				str[0] = "" + rs.getInt(1);
				str[1] = "" + rs.getString(2);
				str[2] = "" + rs.getInt(5);
				str[3] = "" + rs.getInt(6);
				str[4] = "" + rs.getInt(7);
				str[5] = "" + rs.getInt(8);
				// System.out.println(str[1]+" " + str[2] + " " + str[3] + " " + str[4] + " "
				// +str[5]);
				list.add(str);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static String[] tbllist(String data)
	{
		String st[] = null;
		if (null != data && !"".equalsIgnoreCase(data))
		{
			st = data.split("\\s");
		}
		return st;
	}

	public static String converArrStr(String[] data)
	{
		String st = "";
		if (null != data && data.length > 0)
		{
			for (int i = 0; i < data.length; i++)
			{
				if (i < data.length - 1)
				{
					st += data[i] + ",";
				}
				else
				{
					st += data[i];

				}
			}
		}
		return st;
	}

	public static List<String> getCSVFilesList(String csvFolderPath, String csvTableList, String suffix)
	{
		String[] listTableCSV = csvTableList.split(Constants.COMMA);
		List<String> list = new ArrayList<>();
		File folder = new File(csvFolderPath);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles)
		{
			if (file.isFile())
			{
				for (int i = 0; i < listTableCSV.length; i++)
				{
					if ((listTableCSV[i] + suffix).equalsIgnoreCase(file.getName()))
					{
						list.add(file.getName());
					}
				}
			}
		}
		return list;
	}

	public static String generateQvar(String data)
	{
		String var = "";
		String arr[] = data.split(",");
		if (arr.length > 0)
		{
			for (int i = 0; i < arr.length; i++)
			{
				if (i < arr.length - 1)
				{
					var += "?,";
				}
				else
				{
					var += "?";
				}
			}
		}
		return var;
	}

	public static List<Report> getReportData(String type, String tables, String sort, String indata)
	{
		List<Report> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = null;
			if ("tables_list".equalsIgnoreCase(type))
			{
				if (null == sort && null == indata)
				{
					if (null != tables && !"".equalsIgnoreCase(tables))
					{
						sql = "select * from " + Constants.TABLES_METADATA + " where table_name='" + tables
								+ "' order by CONSTRAINT_TYPE asc ";
					}
				}
				else
				{
					sql = "select * from " + Constants.TABLES_METADATA + " where table_name='" + tables
							+ "' order by CONSTRAINT_TYPE asc ";

				}
			}
			else
			{
				if (null == sort && null == indata)
				{
					if (null != tables && !"".equalsIgnoreCase(tables))
					{
						sql = "select * from " + Constants.REPORT_METADATA + " where table_name='" + tables
								+ "' order by column_name asc ";
					}
				}
				else
				{
					sql = "select * from " + Constants.REPORT_METADATA + " where table_name='" + tables
							+ "' order by column_name asc ";

				}
			}
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			// ps.setString(1, tables);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Report r = new Report();
				r.setRid(rs.getInt("rid"));
				r.setColName(rs.getString("COLUMN_NAME"));
				r.setTable_name(rs.getString("table_name"));
				r.setDataType(rs.getString("DATA_TYPE"));
				r.setConstraintName(rs.getString("CONSTRAINT_NAME"));
				r.setNullable(rs.getString("NULLABLE"));
				r.setCharLen(rs.getString("CHAR_LENGTH"));
				r.setConstraintType(rs.getString("CONSTRAINT_TYPE"));
				list.add(r);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

	public static List<String> getReportTablesName(String type)
	{
		List<String> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DBConnection.getInstance().getConnection();
			String sql = null;
			if ("tables_init".equalsIgnoreCase(type))
			{
				sql = "select distinct table_name from " + Constants.TABLES_METADATA;
			}
			else
			{
				sql = "select distinct table_name from " + Constants.REPORT_METADATA;
			}
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			// ps.setString(1, tables);
			rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(rs.getString("table_name"));

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeRs(rs);
			closePs(ps);
			closeConn(conn);
		}
		return list;

	}

}
