package com.stockmarket.www.controller.myAsset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		request.getRequestDispatcher("myAsset.jsp").forward(request, response);
		
	}

}
