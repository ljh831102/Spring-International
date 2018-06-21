package cn.ljh.international.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class HelloController {

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@RequestMapping(value = { "/hello", "/" })
	public String index(HttpServletRequest request, Locale locale) {

		// 在Bean中读取国际化资源的方式之一
		String welcome = messageSource.getMessage("welcome", null, locale);
		System.out.println("国际化资源文件Locale配置(welcome):" + welcome);

		// 在Bean中读取国际化资源的方式之二
		RequestContext requestContext = new RequestContext(request);
		String welcome2 = requestContext.getMessage("welcome");

		System.out.println("国际化资源文件Locale配置(welcome2):" + welcome2);

		Locale locale2 = new Locale("zh", "CN");
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale2);

		return "hello";
	}

	@RequestMapping(value = { "/hello2" })
	public String index2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "lang", required = false) String lang) {

		// if (lang != null) {
		// LocaleResolver localeResolver =
		// RequestContextUtils.getLocaleResolver(request);
		// localeResolver.setLocale(request, response,
		// StringUtils.parseLocaleString(lang));
		// }

		return "hello";
	}
}