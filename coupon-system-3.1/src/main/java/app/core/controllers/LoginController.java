package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.exceptions.CouponSystemException;
import app.core.login.LoginManagerInterface.ClientType;
import app.core.login.TokenLoginManager;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private TokenLoginManager loginManager;

	@PutMapping
	public String Login(@RequestParam String email, @RequestParam String password,
			@RequestParam ClientType clientType) {

		try {
			return loginManager.login(email, password, clientType);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
