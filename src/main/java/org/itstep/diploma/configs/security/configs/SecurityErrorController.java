package org.itstep.diploma.configs.security.configs;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityErrorController implements ErrorController {
	@GetMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		int statusCode = 0;
		if (errorStatusCode != null) {
			statusCode = Integer.parseInt(errorStatusCode.toString());
		}
		model.addAttribute("errorStatusCode", statusCode);
		return "error/error";
	}
}
