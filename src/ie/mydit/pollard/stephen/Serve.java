package ie.mydit.pollard.stephen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Serve extends HttpServlet {
	
	static List<String> picURLs = new ArrayList<String> ();
 
	private static final long serialVersionUID = 1L;
	//private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
    		String pictureURL = (req.getParameter("pictureURL"));
    		picURLs.add(pictureURL);
    		res.getWriter().println("<p>View pics uploaded <a href=\""+pictureURL+"\">here</a></p>");
    		res.getWriter().println("<p>Return Home<a href=\"lastchance\">here</a></p>");
    		//BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
            //blobstoreService.serve(blobKey, res);
            //res.getWriter().println(blobKey);
            
    		
            for(int i=0; i< picURLs.size();i++)
            {
            	String pic = picURLs.get(i);
            	res.getWriter().println("<p><a href=\""+pic+"\"></a></p>");
            	
            }
            
        }
    public static List<String> displayList(){
    return picURLs;
    }
    
    }
