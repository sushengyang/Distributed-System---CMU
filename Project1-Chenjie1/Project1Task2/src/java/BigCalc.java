/*
 * The servelet takes in big integer and performe calculations.
 * The operations includes addition, multipilcation, modulo, inverse modulo and power
 */

import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * when user browses to the oath /BigCalc
 * then the servlet BigCalc should be used
 * @author chenjie zhao
 */
@WebServlet(urlPatterns = {"/BigCalc"})
public class BigCalc extends HttpServlet {

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
        String X = request.getParameter("inputX");
        String Y = request.getParameter("inputY");
        String op = request.getParameter("operations");
        String nextView;

        // validate input is pure digits and operation is not null
        if (X.matches("[0-9]+") && Y.matches("[0-9]+") && op != null) {
            BigInteger addition = BigInteger.valueOf(0);
            BigInteger multi = BigInteger.valueOf(0);
            BigInteger modulo = BigInteger.valueOf(0);
            BigInteger invMod = BigInteger.valueOf(0);
            BigInteger pow = BigInteger.valueOf(0);
            BigInteger gcd = BigInteger.valueOf(0);

            BigInteger biX = new BigInteger(X);
            BigInteger biY = new BigInteger(Y);

            // set output attribute according to operations
            switch (op) {
                case "add":
                    addition = biX.add(biY);
                    request.setAttribute("output", addition);
                    break;
                case "multiply":
                    multi = biX.multiply(biY);
                    request.setAttribute("output", multi);
                    break;
                case "mod":
                    modulo = biX.mod(biY);
                    request.setAttribute("output", modulo);
                    break;
                case "modinverse":
                    gcd = biX.gcd(biY);
                    if (gcd.equals(new BigInteger("1"))) {
                        invMod = biX.modInverse(biY);
                        request.setAttribute("output", invMod);
                    } else {
                        request.setAttribute("output", "X and Y are not relatively prime");
                    }

                    break;
                case "power":
                    pow = biX.pow(biY.intValue());
                    request.setAttribute("output", pow);
                    break;
                case "relativelyprime":
                    gcd = biX.gcd(biY);
                    request.setAttribute("output", gcd.equals(new BigInteger("1")));
                    break;
                default:
                    break;
            }

            request.setAttribute("inputX", X);
            request.setAttribute("inputY", Y);
            request.setAttribute("operation", op);

            nextView = "result.jsp";
        } else {
            nextView = "error.jsp";
        }

        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}
