package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdministratorBean;
import beans.AttributesBeans;
import beans.CategoriesBean;
import beans.LogBean;
import beans.UsersBean;
import dao.AdminDAO;
import dao.CategoryDAO;
import dao.LogDAO;
import dao.UserDAO;
import dto.Administrator;
import dto.Category;
import dto.User;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");

		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			session = request.getSession();
			session.setAttribute("notification", "");
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// Posto i sa stranice registration.jsp mogu da uputim zahtjev servletu ka stranici login.jsp
			// (ne radi se o direktnoj navigaciji ka login.jsp, nego se postavi da je action=login),
			// moze se desiti da je username i password == null.
			if(username != null) {
				AdministratorBean adminBean = new AdministratorBean();
				if (adminBean.login(username, password)) {
					session.setAttribute("adminBean", adminBean);
					address = "/WEB-INF/pages/administrator.jsp";
					CategoriesBean categoriesBean = new CategoriesBean();
					categoriesBean.setCategories(CategoryDAO.loadCategories());
					session.setAttribute("categoriesBean", categoriesBean);
					session.setAttribute("defaultTab", "kategorije");
					System.out.println("kategorijelogin");
					UsersBean usersBean = new UsersBean();
					usersBean.setUsers(UserDAO.loadUsers());
					session.setAttribute("usersBean", usersBean);
					LogBean logBean = new LogBean();
					session.setAttribute("logBean", logBean);
				} else {
					session.setAttribute("notification", "Pogresni parametri za pristup");
				}
			}
		} else if (action.equals("registration")) {
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			address = "/WEB-INF/pages/registration.jsp";
			
			if (firstName != null) {
				if (AdminDAO.isUserNameUsed(request.getParameter("username"))) {
					Administrator admin = new Administrator(0, firstName, lastName, username, password);
					if (AdminDAO.insert(admin))
						session.setAttribute("notification", "Novi administratorski nalog je kreiran!");
				}
				else 
					session.setAttribute("notification", "Korisnicko ime je zauzeto!");
			} 
		} else if (action.equals("deleteCategory")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			CategoryDAO.deleteCategory(categoryId);
			CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
			categoriesBean.setCategories(CategoryDAO.loadCategories());
			session.setAttribute("defaultTab", "kategorije");
			System.out.println("kategorijedelete");

			address = "/WEB-INF/pages/administrator.jsp";
		} else if (action.equals("updateCategory")) {
			if (request.getParameter("id") != null) {
				int categoryId = Integer.parseInt(request.getParameter("id"));
				Category selectedCategory = CategoryDAO.loadCategory(categoryId);
				session.setAttribute("selectedCategory", selectedCategory);
				AttributesBeans attributesBean = new AttributesBeans();
				attributesBean.setAtributes(CategoryDAO.loadAtributes());
				session.setAttribute("attributesBean", attributesBean);
				address = "/WEB-INF/pages/updatecategory.jsp";
			}
			else {
				String nazivKategorije = request.getParameter("nazivKategorije");
				String atribut_id = request.getParameter("atributi");
				Category cat = (Category) session.getAttribute("selectedCategory");
				if (CategoryDAO.updateCategory(cat.getId(), nazivKategorije, Integer.parseInt(atribut_id))) {
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					categoriesBean.setCategories(CategoryDAO.loadCategories());
					session.removeAttribute("selectedCategory");
				}
				session.setAttribute("defaultTab", "kategorije");
				System.out.println("kategorijeupdate");

				address = "/WEB-INF/pages/administrator.jsp";
			}
		} else if (action.equals("administrator")) {
			address = "/WEB-INF/pages/administrator.jsp";
			
		} else if (action.equals("newCategory")) {
			AttributesBeans attributesBean = new AttributesBeans();
			attributesBean.setAtributes(CategoryDAO.loadAtributes());
			session.setAttribute("attributesBean", attributesBean);
			session.setAttribute("defaultTab", "kategorije");
			System.out.println("kategorijenovakat");

			
			if (request.getParameter("nazivKategorije") != null) {
				String nazivKategorije = request.getParameter("nazivKategorije");
				String atribut_id = request.getParameter("atributi");
				if (CategoryDAO.insertCategory(nazivKategorije, atribut_id)) {
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					categoriesBean.setCategories(CategoryDAO.loadCategories());
					session.setAttribute("notification", "Kreirali ste novu kategoriju!");
				}
			} else if (request.getParameter("nazivAtributa") != null) {
				String nazivAtributa = request.getParameter("nazivAtributa");
				CategoryDAO.insertAttribute(nazivAtributa);
				attributesBean.setAtributes(CategoryDAO.loadAtributes());
				session.setAttribute("attributesBean", attributesBean);
				session.setAttribute("notification", "Kreirali ste novi atribut!");
			}
			address = "/WEB-INF/pages/newCategory.jsp";
			
		} else if (action.equals("deleteUser")) {
			int userId = Integer.parseInt(request.getParameter("id"));
			UserDAO.deleteUser(userId);
			UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
			usersBean.setUsers(UserDAO.loadUsers());
			session.setAttribute("defaultTab", "korisnici");
			address = "/WEB-INF/pages/administrator.jsp";
		} else if (action.equals("updateUser")) {
			if (request.getParameter("id") != null) {
				int userId = Integer.parseInt(request.getParameter("id"));
				User selectedUser = UserDAO.selectUser(userId);
				session.setAttribute("selectedUser", selectedUser);
				address = "/WEB-INF/pages/updateuser.jsp";
			}
			else {
				String ime = request.getParameter("ime");
				String grad = request.getParameter("grad");
				String email = request.getParameter("email");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String savjetnik = request.getParameter("savjetnik");
				String verifikovan = request.getParameter("verifikovan");
				
				User user = (User) session.getAttribute("selectedUser");
				if (UserDAO.updateUser(user.getId(), ime, grad, email, username, password, user.getBrojKartice(), Boolean.parseBoolean(savjetnik), Boolean.parseBoolean(verifikovan))) {
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					usersBean.setUsers(UserDAO.loadUsers());
					session.removeAttribute("selectedUser");
				}
				session.setAttribute("defaultTab", "korisnici");
				address = "/WEB-INF/pages/administrator.jsp";
			}

		} else if (action.equals("newUser")) {
			session.setAttribute("defaultTab", "korisnici");
			System.out.println("korisnici");
			
			if (request.getParameter("ime") != null) {
				String ime = request.getParameter("ime");
				String grad = request.getParameter("grad");
				String email = request.getParameter("email");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String savjetnik = request.getParameter("savjetnik");
				String verifikovan = request.getParameter("verifikovan");
				String brojKartice = request.getParameter("brojkartice");
				UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
				if (usersBean.isUsernameDuplicate(username))
					session.setAttribute("notification", "Korisnicko ime je vec zauzeto!");					
				else if (UserDAO.insertUser(ime, grad, email, username, password, Boolean.parseBoolean(savjetnik), Boolean.parseBoolean(verifikovan), brojKartice)) {
					usersBean.setUsers(UserDAO.loadUsers());
					session.setAttribute("notification", "Kreirali ste novog korisnika!");
				}
			} 
			address = "/WEB-INF/pages/newuser.jsp";
			
		} else if (action.equals("statistics")) {
			LogBean logBean = (LogBean) session.getAttribute("logBean");
			if (request.getParameter("brojlogova") != null)
				logBean.resetLogs();
			else
				logBean.loadLogs();
			session.setAttribute("defaultTab", "statistika");
			address = "/WEB-INF/pages/administrator.jsp";
		} else if (action.equals("users")) {
			UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
			usersBean.setUsers(UserDAO.loadUsers());
			session.setAttribute("defaultTab", "korisnici");
			address = "/WEB-INF/pages/administrator.jsp";
		}
		
		else
			address = "/WEB-INF/pages/404.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
