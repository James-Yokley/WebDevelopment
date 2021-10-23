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


@WebServlet("/NewVaccine")
public class NewVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewVaccine() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "    <head>\r\n"
				+ "        <title>New Vaccine Entry</title>\r\n"
				+ "    </head>\r\n"
				+ "    <body>\r\n"
				+ "        <form action=\"NewVaccine\" method=\"POST\" >\r\n"
				+ "            <table border=\"black, solid, 1\" style=\"text-align: center; font-weight: bold;\">\r\n"
				+ "                <tr>\r\n"
				+ "                    <td>Name</td>\r\n"
				+ "                    <td><input type=\"text\" name=\"name\"></td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td> Doses Required </td>\r\n"
				+ "                    <td><select name=\"doseCount\">\r\n"
				+ "                        <option value=\"1\">1</option>\r\n"
				+ "                        <option value=\"2\" selected>2</option>\r\n"
				+ "                    </select></td>\r\n"
				+ "                </tr>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td>Days Between Doses</td>\r\n"
				+ "                    <td><input type=\"text\" name=\"dayCount\"></td>\r\n"
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
		String name = request.getParameter("name");
		var dose = request.getParameter("doseCount");
		int doseCount = dose == null || dose.trim().length() == 0 ? 0: Integer.parseInt(dose);
		var days = request.getParameter("dayCount");
		int dayCount = days == null || days.trim().length() == 0 ? 0: Integer.parseInt(days); 
		VaccineListEntry entry = new VaccineListEntry(name, doseCount, dayCount, 0, 0); 
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries");
		//Currently removes the previous entry on adding new entry
		entries.add(entry); 
		response.sendRedirect("VaccineList"); 
		
	}

}
