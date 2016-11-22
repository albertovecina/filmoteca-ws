package com.avs.filmoteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avs.filmoteca.data.Charset;
import com.avs.filmoteca.data.db.DataBase;
import com.avs.filmoteca.data.repository.DataRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Movies
 */
@WebServlet("/movies")
public class Movies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Gson mGson = new Gson();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Movies() {
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
		response.setCharacterEncoding(Charset.UTF_8);
		
		List<String> movieTitles = DataRepository.getInstance().getMovies();
		DataRepository.closeRepository();
		response.getWriter().append(mGson.toJson(movieTitles));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonMovies = getRawBody(request);
		Type listType = new TypeToken<ArrayList<String>>() {
		}.getType();

		List<String> moviesList = mGson.fromJson(jsonMovies, listType);
		DataRepository.getInstance().deleteMovies();
		DataRepository.getInstance().addMovies(moviesList);
		doGet(request, response);
	}

	private String getRawBody(HttpServletRequest request) {
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				stringBuffer.append(line);
		} catch (Exception e) {
			/* report an error */ }

		return stringBuffer.toString();
	}

}
