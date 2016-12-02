package com.avs.filmoteca;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avs.filmoteca.data.db.DataBase.Environment;
import com.avs.filmoteca.data.model.RegistrationToken;
import com.avs.filmoteca.data.repository.DataRepository;
import com.avs.filmoteca.utils.RequestUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class RegisterToken
 */
@WebServlet(urlPatterns = { "/dev/registrationId", "/registrationId" })
public class RegistrationIds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationIds() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> registationIds = DataRepository.getInstance(RequestUtils.getEnvironment(request))
				.getPushRegistrationIds();
		DataRepository.closeRepository();
		response.getWriter().append(new Gson().toJson(registationIds));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataRepository.getInstance(RequestUtils.getEnvironment(request))
				.addPushRegistrationId(request.getParameter(RegistrationToken.QUERY_TOKEN));
		DataRepository.closeRepository();
	}

}
