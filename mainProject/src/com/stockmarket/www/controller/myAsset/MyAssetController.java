package com.stockmarket.www.controller.myAsset;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;
import com.stockmarket.www.service.basic.BasicAssetDistrService;
import com.stockmarket.www.service.basic.BasicAssetTrendService;

@WebServlet("/card/asset/myAsset")
public class MyAssetController extends HttpServlet{
	
	private AssetTrendService assetTrendService;
	private AssetDistrService assetDistrService;
	
	public MyAssetController() {
		assetTrendService = new BasicAssetTrendService();
		assetDistrService = new BasicAssetDistrService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("id");
		
		// 현재의 보유 자산
		float assetPesent = assetTrendService.getAssetPresent(userId);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        request.setAttribute("assetPesent", assetPesent);
					
		request.getRequestDispatcher("myAsset.jsp").forward(request, response);
		
	}

}
