package com.stockmarket.www.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.ett.Member;
import com.stockmarket.www.service.RankingService;
import com.stockmarket.www.service.jdbc.JdbcRankingService;

@WebServlet("/card/rank/ranking")
public class RankingController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RankingService service = new JdbcRankingService();
		
		List<Member> rankers = service.getMemberList();
		Member myRank = service.getMember();
		
		request.setAttribute("rankers", rankers);
		request.setAttribute("myRank", myRank);
		
		// jsp 파일을 요청하여 브라우저에 보여준다.
		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}
}
