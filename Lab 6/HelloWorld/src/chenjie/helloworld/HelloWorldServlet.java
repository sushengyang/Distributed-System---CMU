package chenjie.helloworld;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloWorldServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
