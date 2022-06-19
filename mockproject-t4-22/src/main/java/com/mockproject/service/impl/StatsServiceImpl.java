package com.mockproject.service.impl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.repository.StatsRepo;
import com.mockproject.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService{

	@Autowired
	private StatsRepo repo;
	
	@Override
	public String[][] getTotalPriceLast6Month() {
		String result[][]=new String[2][6];
		YearMonth now = YearMonth.now().minusMonths(5);
		for(int i=0;i<6;i++) {
			String month=Integer.toString(now.getMonthValue());
			String year=Integer.toString(now.getYear());
			result[0][i]=month+"-"+year;
			result[1][i]=repo.getTotalPricePerMonth(month, year);
			now=now.plusMonths(1);
		}
		
		return result;
	}

}
