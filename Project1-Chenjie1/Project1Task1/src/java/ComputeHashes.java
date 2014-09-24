/*
 * The servlet takes in text data. And transform the data to hash value.
 * There are two ways to compute the hash, MD5 and SHA-1
 * @author Chenjie Zhao    
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 * When the user browes to the path /ComputerHashes the ComputerHashes Servlet
 * should be used.
 *
 * @author chenjie zhao
 */
@WebServlet(urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

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
        String text = request.getParameter("hashText");
        String style = request.getParameter("Hash");
        byte[] hashCode = new byte[32];

        // decide which code style to apply
        if (style.equals("MD5")) {
            // Generate MD5 hashcode
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            md5.update(text.getBytes(), 0, text.length());
            hashCode = md5.digest();
        } else {

            // Generate SHA-1 hashcode
            MessageDigest sha1 = null;
            try {
                sha1 = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            sha1.update(text.getBytes(), 0, text.length());
            hashCode = sha1.digest();
        }

        // Translate hashcode to BASE64
        BASE64Encoder encoder = new BASE64Encoder();
        String BASEcode = encoder.encode(hashCode);

        // Translate hashcode to Hex
        String HexCode = null;
        try {
            HexCode = this.getHexString(hashCode).toUpperCase();
        } catch (Exception ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("hashText", text);
        request.setAttribute("hashStyle", style);
        request.setAttribute("base", BASEcode);
        request.setAttribute("hex", HexCode);
        String nextView = "result.jsp";
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

    // From the web site “Real’s How To”
    /**
     * The hexdecimal representation of a byte array
     * @param b
     * @return hexstring
     * @throws Exception
     */
    public String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
