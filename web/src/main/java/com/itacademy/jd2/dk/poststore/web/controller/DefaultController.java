package com.itacademy.jd2.dk.poststore.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itacademy.jd2.dk.poststore.web.tag.I18N;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

	private static final Locale LOCALE_RU = new Locale("ru");
	private static final Locale LOCALE_EN = new Locale("en");

	@RequestMapping(method = RequestMethod.GET)
	public String index(final HttpServletRequest req,
			@RequestParam(name = "language", required = false) final String lang) {
		if (lang != null) {

			Locale locale;
			if ("ru".equals(lang)) {
				locale = LOCALE_RU;
			} else {
				locale = LOCALE_EN;
			}

			req.getSession().setAttribute(I18N.SESSION_LOCALE_KEY, locale);
			LOGGER.info("switch to locale:" + locale);
		}

		return "redirect:/orderHistory";
	}

}
