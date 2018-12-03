package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.permit.PermitService;
import dao.PermitDAO;
import helpers.Builder;
import pojo.Permit;
import pojo.Permit.PermitColor;


@WebServlet("/permit")
public class PermitController extends HttpServlet {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PermitService permitService = new PermitService();
		permitService.addPermit(request, response);
	}
	
}
