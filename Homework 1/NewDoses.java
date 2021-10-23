package cs3220.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.VaccineListEntry;

import java.io.PrintWriter;
import java.util.List;
@WebServlet("/NewDoses")
public class NewDoses extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public NewDoses() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries"); 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "    <head>\r\n"
				+ "        <title>New Vaccine Entry</title>\r\n"
				+ "    </head>\r\n"
				+ "    <body>\r\n"
				+ "        <form action=\"NewDoses\" method=\"POST\" >\r\n"
				+ "            <table border=\"black, solid, 1\" style=\"text-align: center; font-weight: bold;\">\r\n"
				+ "                <tr>\r\n"
				+ "                    <td>Vaccine</td>\r\n"
				+ "                    <td><select name=\"doseName\">\r\n");
		for(VaccineListEntry entry: entries) {
			out.println("<option value='" + entry.getName() + "'>" + entry.getName() + "</option>");
		}
		out.println("");
		out.println("</select></td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td> New Doses Received </td>\r\n"
				+ "                    <td><input type=\"text\" name=\"dosesReceived\"></td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr colspan=\"2\">\r\n"
				+ "                    <td colspan=\"2\" style=\"text-align: left;\">\r\n"
				+ "                        <button>Add</button>\r\n"
				+ "                    </td>\r\n"
				+ "                </tr>    \r\n"
				+ "            </table>\r\n"
				+ "        </form>\r\n"
				+ "    </body>\r\n"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries"); 
		String vaccineName = request.getParameter("doseName");
		var received = request.getParameter("dosesReceived");
		int doseReceieved = received == null || received.trim().length() == 0 ? 0: Integer.parseInt(received); 
		for(VaccineListEntry entry: entries) {
			if(entry.getName().equals(vaccineName)) {
				entry.addDoses(doseReceieved, doseReceieved);
			}
		}
		response.sendRedirect("VaccineList"); 
	}

}
