package com.eCommerce.service.Impl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.repository.StatsRepo;
import com.eCommerce.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService{

	
	@Autowired
	private StatsRepo repo;
	
	@Override
	public String[][] getTotalPrice6LastMonth() {

		YearMonth now=YearMonth.now();
		String[][] result=new String[2][6];
		for(int i=0;i<6;i++) {
			YearMonth tmp = now.minusMonths(5-i);
			String month=tmp.getMonthValue()+"";
			String year=tmp.getYear()+"";
			result[0][i]=month+"-"+year;
			result[1][i]=repo.getTotalPricePerMonth(month,year);
		}
		return result;
	}

}
