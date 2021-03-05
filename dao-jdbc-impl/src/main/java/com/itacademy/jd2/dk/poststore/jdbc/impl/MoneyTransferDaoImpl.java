package com.itacademy.jd2.dk.poststore.jdbc.impl;

import org.springframework.stereotype.Repository;

@Repository
public class MoneyTransferDaoImpl {
	final Double PERCENT = 3.0;

	private Double getActualPrice(Double ammount) {
		Double actualPrice = Math.round(ammount * PERCENT / 100 * 100.0) / 100.0;
		return actualPrice;
	}
}
