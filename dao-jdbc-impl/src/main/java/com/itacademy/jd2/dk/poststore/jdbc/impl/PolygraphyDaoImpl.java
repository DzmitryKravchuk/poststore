package com.itacademy.jd2.dk.poststore.jdbc.impl;

import org.springframework.stereotype.Repository;

@Repository
public class PolygraphyDaoImpl {

	private double getActualPrice(Double price4A4, String imageFormat, Integer copyCount, Boolean isColoured,
			Boolean isDuplexPrinted) {
		Double formatFactor = 1.0;
		Double colourFactor = 1.0;
		Double duplexFactor = 1.0;

		if (imageFormat == "A4") {
			formatFactor = 1.0;
		} else if (imageFormat == "A3") {
			formatFactor = 2.0;
		} else if (imageFormat == "A5") {
			formatFactor = 0.5;
		} else if (imageFormat == "A6") {
			formatFactor = 0.25;
		}

		if (isColoured == true) {
			colourFactor = 1.5;
		}

		if (isDuplexPrinted == true) {
			duplexFactor = 1.5;
		}

		double actualPrice = Math.round((copyCount * colourFactor * duplexFactor * price4A4 * formatFactor) * 100.0)
				/ 100.0;
		return actualPrice;
	}

}
