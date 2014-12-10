package ie.mydit.pollard.stephen;

import ie.mydit.pollard.stephen.Serve;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LastChanceServlet extends HttpServlet {
	//private PrintWriter writer;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		List<String> picURLs = Serve.displayList();	
		UserService userService = UserServiceFactory.getUserService();
		Principal myPrincipal = req.getUserPrincipal();
		String emailAddress = null;
		String pictureURL = (req.getParameter("pictureURL"));
		String[] admin = {"stephen@dit.ie, mark.deegan@dit.ie"};
		String[] users = {"john@dit.ie, oliver@dit.ie"};
		String uploadURL = "index.jsp";
		String thisURL = req.getRequestURI();
		String loginURL = userService.createLoginURL(thisURL);
		String logoutURL = userService.createLogoutURL(thisURL);
		resp.setContentType("text/html");
		
		if(myPrincipal == null) {
			resp.getWriter().println("<p>You are Not Logged In</p>");
			resp.getWriter().println("<p>You can <a href=\"" + loginURL + "\">sign in here</a>.</p>");
		} // end if not logged in
		
		if(myPrincipal !=null) {
			emailAddress = myPrincipal.getName();
			resp.getWriter().println("<p>You are Logged in as (email):"+ ""+emailAddress+"</p>");
			resp.getWriter().println("<p>You can <a href=\"" + uploadURL + "\">upload here</a>.</p>");
			resp.getWriter().println("<p>View pics uploaded <a href=\""+pictureURL+"\">here</a></p>");
			resp.getWriter().println("<p>You can <a href=\"" + logoutURL + "\">sign out</a>.</p>");
			
			for(int i=0; i< picURLs.size();i++)
            {
            	String pic = picURLs.get(i);
            	resp.getWriter().println("<p><a href=\""+pic+"\"></a></p>");
            	
            }
		 // end if logged in
			
		}
		
		
	}
}
