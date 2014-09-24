/* The servlet takes in a text and decide whether it is a palidrom
 * especially for android mobile use
 * @author Chenjie Zhao
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * when the user browses to the path /Palin
 * then the servlet Palin should be used 
 * @author chenjie Zhao
 */
@WebServlet(urlPatterns = {"/Palin"})
public class Palin extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String str = request.getParameter("palin");
        String stringtoChar = str.toLowerCase().replaceAll("\\'", "");
        stringtoChar = stringtoChar.replaceAll("\\s", "");
        char[] charArray = stringtoChar.toCharArray();
        boolean isPalindrom = this.istPalindrom(charArray);
        String ua = request.getHeader("User-Agent");
        boolean mobile;

        // decide whether the terminal is an android device
        if (ua != null && ((ua.indexOf("Android") != -1))) {
            mobile = true;

            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        request.setAttribute("output", str);
        request.setAttribute("isPalin", isPalindrom);
        String nextView = "result.jsp";
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

    /**
     * decide the word whether is a palindrom
     * @param word input word
     * @return boolean is palindrom
     */
    public boolean istPalindrom(char[] word) {
        int forth = 0;
        int back = word.length - 1;
        while (back > forth) {
            if (word[forth] != word[back]) {
                return false;
            }
            forth++;
            back--;
        }
        return true;
    }

}
