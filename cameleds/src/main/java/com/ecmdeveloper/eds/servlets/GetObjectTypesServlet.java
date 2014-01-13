package com.ecmdeveloper.eds.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class GetObjectTypesServlet
 */
public class GetObjectTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetObjectTypesServlet() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		if (connection != null) {
		    try {
//		    	CREATE TABLE FIRSTTABLE (ID INT PRIMARY KEY,NAME VARCHAR(12));   	
//		    	INSERT INTO FIRSTTABLE VALUES (10,'TEN'),(20,'TWENTY'),(30,'THIRTY');
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.println("Hello World at " + (new Date()).toString());
				
				try {
					writeNames(connection, outputStream);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} finally {
				try {
				    if (!connection.isClosed()) {
					connection.close();
				    }
				} catch (SQLException e) {
				    e.printStackTrace();
				}
			}    
		}
	}

	private void writeNames(Connection connection,
			ServletOutputStream outputStream) throws SQLException, IOException {
		String sql = "SELECT NAME FROM FIRSTTABLE";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
		    String name = rs.getString("NAME");
		    outputStream.println(name);
		}
	}
	
	private Connection getConnection() {
		Connection connection = null;
		try {
		    InitialContext context = new InitialContext();
		    DataSource dataSource = (DataSource) context
			    .lookup("jdbc/DerbyConnection" );
		    connection = dataSource.getConnection();
		} catch (NamingException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return connection;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
