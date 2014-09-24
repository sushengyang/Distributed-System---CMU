/* This is a short example of MVC.
 * The welcome-file in the deployment descriptor (web.xml) points
 * to this file.  So it is also the starting place for the web
 * application.
 *
 * The servlet is acting as the controller.
 * There are two views - prompt.jsp and result.jsp.
 * It decides between the two by determining if there is a search parameter or
 * not. If there is no parameter, then it uses the prompt.jsp view, as a 
 * starting place. If there is a search parameter, then it searches for a 
 * picture and uses the result.jsp view.
 * The model is provided by InterestingPictureModel.
 */
package NGAPicture;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * The following WebServlet annotation gives instructions to the web container.
 * It states that when the user browses to the URL path /InterestingPictureServlet
 * then the servlet with the name InterestingPictureServlet should be used.
 */
@WebServlet(name = "NGAPictureServlet",
        urlPatterns = {"/getNGAPicture"})
public class NGAPictureServlet extends HttpServlet {

    NGAPictureModel ipm = null;  // The "business model" for this app

    @Override
    public void init() {
        ipm = new NGAPictureModel();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get the search parameter if it exists
        String search = request.getParameter("searchWord");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        String nextView;
        /*
         * Check if the search parameter is present.
         * If it is not, then give the user instructions and prompt for a search
         * string.
         * If there is a search parameter, then do the search and return the result.
         */
        if (search != null) {
            // use model to do the search and choose the result view
            ipm.doNGASearch(search);
            request.setAttribute("pictureURL",
                    ipm.interestingPictureSize(
                            (mobile) ? "mobile" : "desktop"));
            request.setAttribute("pictureTag", ipm.getPictureTag());
            request.setAttribute("pictureTitle", ipm.getPictureTitle());
            nextView = "result.jsp";
        } else {
            // no search parameter so choose the prompt view
            nextView = "prompt.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
