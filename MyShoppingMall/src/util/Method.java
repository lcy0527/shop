package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * 一键创建类、接口的方法
 */
public class Method extends JdbcUtil {
	File file = null;

	@SuppressWarnings("static-access")
	public void start() {
		try {
			if (getConnection()) {
				String url = ConfigManager.getInstance().getValue("jdbc.url");
				String[] s = url.split("/");
				String dbName = s[s.length - 1];
				// System.out.println(dbName);
				showTables(dbName);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showTables(String dbName) {

		ArrayList<Object> al = null;// 储存tables
		al = new ArrayList<Object>();// 储存tables
		String sql = "show tables";
		try {
			rs = myExcuteQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getObject("Tables_in_"+dbName)+"==>"+dbName);
				al.add(rs.getObject("Tables_in_" + dbName));
			}
			for (Object s : al) {
				String temp = (String) s;
				descTables(temp);
				// System.out.println(temp);
			}

		} catch (SQLException e) {
			System.out.println("showTables rs.next() error");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		al = null;
	}

	public void descTables(String temp) {// 查看表文件字段类型、字段名
		ArrayList<String> als = new ArrayList<String>();// 临时储存某一个表中的各种变量
		ArrayList<String> als2 = new ArrayList<String>();// 临时储存某一个表中的各种变量类型
		String sql = "desc " + temp;
		try {
			ResultSet rs2 = myExcuteQuery(sql);
			while (rs2.next()) {
				als.add(rs2.getString("Field"));
				als2.add(rs2.getString("Type"));
			}
			String up = temp.substring(8, 9);
			up = up.toUpperCase();
			temp = up + temp.substring(9);

			// 创建bean目录下类文件
			String[] s = CreateBean(temp, als, als2);

			/*
			 * 创建DAO接口文件
			 */
			CreateDao(temp, s);

			/*
			 * 创建DAO实现类
			 */
			CreateDaoImpl(temp, s);

			/*
			 * 创建service下接口文件
			 */
			CreateService(temp, s);

			// 创建service实现类
			CreateServiceImpl(temp, s);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void CreateServiceImpl(String temp, String[] s) {
		file = classifyFile("serviceimpl", temp);
		createJava(file, temp);
		s[0] = "package service.impl;" + "import java.io.Serializable;"
				+ "import java.util.List;" + "import bean." + temp + ";"
				+ "import service." + temp + "Service;";
		writeJava(file, temp, s);// package表头
		// 创建类名
		s[0] = "public class " + temp + "ServiceImpl implements " + temp + "Service{";
		writeJava(file, temp, s);
		writeJava(file, temp, "foot");
	}

	private void CreateService(String temp, String[] s) {
		file = classifyFile("service", temp);
		createJava(file, temp);
		s[0] = "package service;import bean." + temp + ";";
		writeJava(file, temp, s);// package表头
		// 创建类名
		s[0] = "public interface " + temp + "Service extends BaseService<"
				+ temp + ">{";
		writeJava(file, temp, s);
		writeJava(file, temp, "foot");
	}

	private String[] CreateBean(String temp, ArrayList<String> als,
			ArrayList<String> als2) {
		file = classifyFile("bean", temp);
		createJava(file, temp);
		String[] s = { "package bean;import java.io.Serializable;" };
		writeJava(file, temp, s);// package表头
		classify(file, temp, als, als2);
		writeJava(file, temp, "foot");
		return s;
	}

	private void CreateDao(String temp, String[] s) {
		file = classifyFile("dao", temp);
		createJava(file, temp);
		s[0] = "package dao;import bean." + temp + ";";
		writeJava(file, temp, s);// package表头
		// 创建类名
		s[0] = "public interface " + temp + "Dao extends BaseDao<" + temp
				+ "> {";
		writeJava(file, temp, s);
		writeJava(file, temp, "foot");
	}

	private void CreateDaoImpl(String temp, String[] s) {
		file = classifyFile("daoimpl", temp);
		createJava(file, temp);
		s[0] = "package dao.impl;import bean." + temp + ";"
				+ "import java.io.Serializable;"
				+ "import java.sql.SQLException;"
				+ "import java.util.ArrayList;" + "import java.util.List;"
				+ "import dao." + temp + "Dao;" + "import util.JdbcUtil;"
				+ "import util.ResultSetUtil;";
		writeJava(file, temp, s);// package表头
		// 创建类名
		s[0] = "public class " + temp + "DaoImpl extends JdbcUtil implements "
				+ temp + "Dao{";
		writeJava(file, temp, s);
		writeJava(file, temp, "foot");
	}

	public File classifyFile(String name, String name2) {
		switch (name) {
		case "bean":
			file = new File("src/" + name + "/" + name2 + ".java");
			break;
		case "dao":
			file = new File("src/" + name + "/" + name2 + "Dao.java");
			break;
		case "daoimpl":
			file = new File("src/dao/impl/" + name2 + "DaoImpl.java");
			break;
		case "service":
			file = new File("src/" + name + "/" + name2 + "Service.java");
			break;
		case "serviceimpl":
			file = new File("src/service/impl/" + name2 + "ServiceImpl.java");
			break;
		}

		return file;
	}

	public void classify(File f, String name2, ArrayList<String> als,
			ArrayList<String> als2) {
		String[] s ={""};
		String temp = null;
		String temp2 = null;
		for (Object a2 : als2) {// 添加import
			String temp3 = (String) a2;
			temp3 = temp3.substring(0,4);
			if (temp3.equalsIgnoreCase("date")
					|| temp3.equalsIgnoreCase("time"))
				s[0] = "import java.util.Date;";
			writeJava(f, temp, s);
		}
		s[0] = "class";
		writeJava(f, name2, s);
		for (int i = 0; i < als.size(); i++) {
			temp = (String) als.get(i);
			temp2 = (String) als2.get(i);
			temp2 = temp2.substring(0, 4);
			switch (temp2) {
			case "int(":
				writeLine(f, name2, "int", temp);
				break;
			case "tiny":
				writeLine(f, name2, "int", temp);
				break;
			case "smal":
				writeLine(f, name2, "int", temp);
				break;
			case "medi":
				writeLine(f, name2, "int", temp);
				break;
			case "bigi":
				writeLine(f, name2, "long", temp);
				break;
			case "floa":
				writeLine(f, name2, "float", temp);
				break;
			case "doub":
				writeLine(f, name2, "double", temp);
				break;
			case "deci":
				writeLine(f, name2, "double", temp);
				break;
			case "char":
				writeLine(f, name2, "String", temp);
				break;
			case "varc":
				writeLine(f, name2, "String", temp);
				break;
			case "text":
				writeLine(f, name2, "String", temp);
				break;
			case "date":
				writeLine(f, name2, "Date", temp);
				break;
			case "time":
				writeLine(f, name2, "Date", temp);
				break;
			default:

				break;
			}
		}

	}

	public void writeLine(File f, String name2, String t, String tName) {
		String temp = "private " + t + " " + tName + ";";
		String tNameU = tName.substring(0, 1);
		tNameU = tNameU.toUpperCase() + tName.substring(1);
		String[] s = { "", "" };
		s[0] = temp;
		writeJava(f, name2, s);
		temp = "public " + t + " get" + tNameU + "() {return " + tName
				+ ";}public void set" + tNameU + "(" + t + " " + tName
				+ ") {this." + tName + " = " + tName + ";}";
		s[0] = temp;
		writeJava(f, name2, s);
	}

	public void createJava(File f, String name2) {

		if (f.exists()) {
			System.out.println(name2 + "ok");
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("createJava error");
			}
		}
	}

	public void writeJava(File f, String name2, String... s) {
		Writer out = null;
		BufferedWriter bw = null;
		try {
			out = new FileWriter(f, true);
			bw = new BufferedWriter(out);
			if (s[0].equalsIgnoreCase("class")) {
				bw.write("public class " + name2 + " implements Serializable{");
				bw.newLine();
			} else if (s[0].equalsIgnoreCase("foot")) {
				bw.write("}");
				bw.newLine();
			} else {
				bw.write(s[0]);
				bw.newLine();
			}
			bw.flush();// 清除缓存
		} catch (IOException e) {
			System.out.println("writeJava write bw/out error");
		} finally {
			try {
				if (bw != null) {
					bw.close();// 关闭输出流
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.out.println("writeJava close bw/out error");
			}
		}

	}

	public void writeInterface(String name, String... s) {
		Writer out = null;
		BufferedWriter bw = null;
		try {
			out = new FileWriter("src/bean/m_" + name + ".java", true);
			bw = new BufferedWriter(out);
			if (s[0].equalsIgnoreCase("package")) {
				bw.write("package bean;");
				bw.newLine();
				bw.write("import java.io.Serializable;");
				bw.newLine();
			} else if (s[0].equalsIgnoreCase("class")) {
				bw.write("public class m_" + name + " implements Serializable{");
				bw.newLine();
			} else if (s[0].equalsIgnoreCase("foot")) {
				bw.write("}");
				bw.newLine();
			} else {
				bw.write(s[0]);
				bw.newLine();
			}
			bw.flush();// 清除缓存
		} catch (IOException e) {
			System.out.println("writeJava write bw/out error");
		} finally {
			try {
				if (bw != null) {
					bw.close();// 关闭输出流
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.out.println("writeJava close bw/out error");
			}
		}

	}

}
