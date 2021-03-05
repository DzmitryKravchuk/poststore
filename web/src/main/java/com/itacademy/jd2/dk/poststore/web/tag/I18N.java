package com.itacademy.jd2.dk.poststore.web.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itacademy.jd2.dk.poststore.web.util.XMLResourceBundleControl;

public class I18N extends SimpleTagSupport {
	public static final String SESSION_LOCALE_KEY = "current-locale";

	private final Locale DEFAULT_LOCALE = new Locale("ru");
	private String key;

	@Override
	public void doTag() throws JspException, IOException {

		final JspContext jspContext = getJspContext();

		Locale locale = (Locale) jspContext.findAttribute(SESSION_LOCALE_KEY);
		if (locale == null) {
			locale = DEFAULT_LOCALE;
		}

		jspContext.getOut().println(getLocalized(key, locale));
	}

	private String getLocalized(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("bundles", locale, new XMLResourceBundleControl());
		String string = bundle.getString(key);
		return string;
	}

	public void setKey(final String key) {
		this.key = key;
	}
}