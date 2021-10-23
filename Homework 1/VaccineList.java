package cs3220.servlet;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cs3220.model.VaccineListEntry;

@WebServlet(urlPatterns="/VaccineList", loadOnStartup=1)
public class VaccineList extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public VaccineList() {
        super();
    }
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	
    	List<VaccineListEntry> entries = new ArrayList<VaccineListEntry>(); 
    	entries.add(new VaccineListEntry("Pfizer", 2, 21, 0, 0)); 
    	entries.add(new VaccineListEntry("Johnson & Johnson", 1, 0, 0, 0)); 
    	getServletContext().setAttribute("entries", entries);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		out.println("<html><head><title>Vaccine List</title></head><body>");
		out.println("<a href='NewVaccine'>New Vaccine |</a>");
		//New Doses Link and Class
		out.println("<a href='NewDoses'> New Doses</a><br>");
		out.println("<table border='1' style='margin-top: 20px;'> <tr><th>Vaccine</th><th>Doses Required</th><th>Days Between Doses</th>"
				+ "<th>Total Doses Received</th><th>Total Doses Left </th><th></th></tr>");
		for(VaccineListEntry entry : entries) {
			out.println("<tr>");
			out.println("<td>" + entry.getName() + "</td>");
			out.println("<td>" + entry.getDoseCount() + "</td>");
			out.println("<td>" + entry.getDayCount() + "</td>");
			out.println("<td>" + entry.getDosesReceived() + "</td>");
			out.println("<td>" + entry.getDosesLeft() + "</td>");
			out.println("<td> <a href='EditVaccine?name=" + entry.getURL() + "'>Edit</a>"); 
			out.println("</tr>");
		}
		out.println("</table></body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
