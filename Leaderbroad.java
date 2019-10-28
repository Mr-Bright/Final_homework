package android_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Leaderbroad
 */
@WebServlet("/Leaderbroad")
public class Leaderbroad extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<Record_bean> broad;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Leaderbroad() {
        super();
        broad = new ArrayList<Record_bean>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
		
		if(flag.equals("leaderbroad")) {
			System.out.print("leaderbroad");
			Collections.sort(broad);
			Gson gson = new Gson();
            String strJson = gson.toJson(broad);
    		out.print(strJson);
		}
		else if(flag.equals("commit")) {
			System.out.print("commit");
			String username = request.getParameter("username");
			int step = Integer.parseInt(request.getParameter("step"));
			int best = Integer.parseInt(request.getParameter("best"));
			Record_bean bean = new Record_bean();
			bean.username = username;
			bean.step = step;
			bean.best = best;
			broad.add(bean);
			out.print("upload successfully!");
		}
		else {
			out.print("Error!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
		if(flag.equals("leaderbroad")) {
			Gson gson = new Gson();
            String strJson = gson.toJson(broad);
    		out.print(strJson);
		}
		else {
			out.print("Error!");
		}
	}

}
