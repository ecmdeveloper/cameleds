package com.ecmdeveloper.eds.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ecmdeveloper.eds.core.Engine;

/**
 * Servlet implementation class UpdateObjectTypeServlet
 * 
 * This is how the JSON coming in is structured.
 * 
 * POST /type/<object type name>
 * 
 *{
 *	"repositoryId":"<target repository>",
 *	"objectId" : "<if an existing instance, the GUID, PID, etc>",
 *	"requestMode" : "<indicates context that info is being requested>",
 *	"externalDataIdentifier" : "<opaque identifier meaningful to service">,
 *	"properties":
 *	[
 *		{
 *			"symbolicName" : "<symbolic_name>", 
 *			"value" : <The current value>,
 *		}
 *		// More properties ...
 *	],
 *	"clientContext":
 *	{
 *		"userid":"<user id>",
 *		"locale":"<browser locale>",
 *		"desktop": "<desktop id>"
 *	}
 *}
 * 
 * The requestMode has values:
 *  
 * initialNewObject: when a new object is being created (when add doc, create folder, checkin dialogs first appear)
 * initialExistingObject: when an existing object is being edited (when edit properties first appears) 
 * inProgressChanges: when an object is being modified (for dependent choice lists) 
 * finalNewObject: before the object is persisted (when action is performed on add doc, create folder, checkin)
 * finalExistingObject: before the existing object is persisted (when save action is performed on edit properties)\
 */
public class UpdateObjectTypeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateObjectTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Engine engine = getCamelEngine();
		
		String objectType = request.getPathInfo().substring(1);
		System.out.println( objectType );
		InputStream inputStream = request.getInputStream();
		String convert = engine.convert(inputStream);
		System.out.println(convert);
		
		PrintWriter writer = response.getWriter();
		writer.print(convert);
		writer.close();
	}

	private Engine getCamelEngine() {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Engine engine = context.getBean(Engine.class);
		if (engine.getCamelContext() != null ) {
			System.out.println( engine.getCamelContext() );
		}
		return engine;
	}

/*	
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
*/	
}
