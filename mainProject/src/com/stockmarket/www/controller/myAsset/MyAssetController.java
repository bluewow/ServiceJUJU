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
		
		// String presnetAsset = String.valueOf(assetTrendService.getAssetPresent(3));
		
		/*
		List<RecordAsset> list = assetTrendService.getRecordAsset(3);
				
		StringBuilder builder = new StringBuilder();
	      builder.append("[");
	      // {"id":1, "title":"안녕하세요", "writerId":"newlec"}
	      // {"memberId" :
	      for(int i=0; i<list.size(); i++) {
	         String item = String.format("{\"id\":%d,\"regdate\":\"%s,\"value\":%d}"
	        		 , list.get(i).getMemberId()
	        		 , list.get(i).getRegdate()
	        		 , list.get(i).getValue());

	         builder.append(item);
	         
	         if(i < list.size()-1)
	            builder.append(",");
	      }		
	      builder.append("]");
	      String json = builder.toString();
	       
	       PrintWriter out = response.getWriter();
	       out.write(json); 
	       out.write(presnetAsset);
	       */
		
		/*
	       List<Map<String, Object>> list = assetDistrService.getHaveStockList(3);
	       
	       StringBuilder builder = new StringBuilder();
	       builder.append("[");
	       // {"id":1, "title":"안녕하세요", "writerId":"newlec"}
	       // {"memberId" :
	       for(int i=0; i<list.size(); i++) {
	    	   String item = String.format("{\"stockName\":%s,\"ratio\":\"%f}"
	    			   , list.get(i).get("stockName")
	    			   , list.get(i).get("ratio"));

	    	   builder.append(item);
	    	   
	    	   if(i < list.size()-1)
	    		   builder.append(",");
	       }		
	       builder.append("]");
	       
	       String json = builder.toString();
	       
	       String StockName1 = String.valueOf(list.get(0).get("stockName")); 
	       */
		
//	       PrintWriter out = response.getWriter();
//	       out.write(StockName1); 
	       // out.write(presnetAsset);
					
		
		request.getRequestDispatcher("myAsset.jsp").forward(request, response);
		
	}

}
