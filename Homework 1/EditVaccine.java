package cs3220.servlet;
import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.VaccineListEntry;

@WebServlet("/EditVaccine")
public class EditVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditVaccine() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries"); 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html"); 
		String name = request.getParameter("name"); 
		for(VaccineListEntry entry: entries) {
			String entryName = entry.getURL(); 
			if(entryName.equals(name)) {
				int doseReq = entry.getDoseCount();
				out.println("<!DOCTYPE html>"
						+ "<html>"
						+ "    <head>"
						+ "        <title>New Vaccine Entry</title>"
						+ "    </head>"
						+ "    <body>"
						+ "        <form action='EditVaccine' method='POST' >"
						+ "            <table border='black, solid, 1' style='text-align: center; font-weight: bold;'>"
						+ "                <tr>"
						+ "                    <td>Name</td>"
						+ "                    <td><input type='text' name='name'value='");
					out.println(entry.getName() + "'></td>"
						+ "                </tr>"
						+ "                <tr>"
						+ "                    <td> Doses Required </td>");
					if(doseReq == 2) {
						out.println("<td><select name='doseCount'>"
						+ "                        <option value='1'>1</option>"
						+ "                        <option value='2' selected>2</option>"
						+ "                    </select></td>"
						+ "                </tr>"
						+ "                <tr>");
					} else {
						out.println("<td><select name='doseCount'>"
								+ "						+ \"                        <option value='1' selected>1</option>"
								+ "						+ \"                        <option value='2'>2</option>"
								+ "						+ \"                    </select></td>"
								+ "						+ \"                </tr>"
								+ "						+ \"                <tr>");
					}
					out.println("<td>Days Between Doses</td><td><input type='text' name='dayCount' value='");
					out.println(entry.getDayCount()+ "'></td>"
						+ "                </tr>"
						+ "                <tr colspan='2'>");
					out.println("<td><input type='hidden' name='originalName' value='" + entry.getName() + "'></td>"
						+ "                    <td colspan='2' style='text-align: left;'>"
						+ "                        <button>Add</button>"
						+ "                    </td>"
						+ "                </tr>"
						+ "            </table>"
						+ "        </form>"
						+ "    </body>"
						+ "</html>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries"); 
		String originalName = request.getParameter("originalName"); 
		String name = request.getParameter("name"); 
		var dose = request.getParameter("doseCount"); 
		int doseCount = dose == null || dose.trim().length() == 0 ? 0: Integer.parseInt(dose); 
		var days = request.getParameter("dayCount"); 
		int dayCount = days == null || days.trim().length() == 0 ? 0: Integer.parseInt(days);
		for(VaccineListEntry entry : entries) {
			if(originalName.equals(entry.getName())) {
				entry.editEntry(name, doseCount, dayCount);
				response.sendRedirect("VaccineList");
				return; 
			}
		}
		response.sendRedirect("VaccineList");
	}

}
