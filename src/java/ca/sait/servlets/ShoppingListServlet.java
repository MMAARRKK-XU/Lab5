package ca.sait.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ShoppingListServlet extends HttpServlet {

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
        String name = (String) request.getSession().getAttribute("name");
        String action = request.getParameter("action");
        if(name != null){
            if(action.equals("logout")){
                request.getSession().invalidate();
                this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action.equals("register")){
            String name = request.getParameter("name");
        
            if(name != null){
                // Creates Session
                request.getSession().setAttribute("name", name);
                
                ArrayList<String> items = new ArrayList<>();
                request.getSession().setAttribute("items", items);
            }
        } else if (action.equals("add")){
             String item = request.getParameter("item");
             
             //Adds item
             ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
             
             items.add(item);
             
             request.getSession().setAttribute("items", items);
        } else if (action.equals("delete")){
            String itemValue = request.getParameter("item");
            
            //Deletes item
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
            
            //items.remove(itemValue);
            int indexToDelete = -1;
            
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).equals(itemValue)){
                    indexToDelete = i;
                    break;
                }
            }
            if(indexToDelete != -1){
                items.remove(indexToDelete);
            }
            
            request.getSession().setAttribute("items", items);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
}
