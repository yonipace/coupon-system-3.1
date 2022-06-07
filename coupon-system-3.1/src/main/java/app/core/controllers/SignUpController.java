package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;

@RestController
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private AdminService service;

	@PutMapping("/company")
	public Company companySignUp(@RequestParam String name, @RequestParam String email, @RequestParam String password) {

		try {
			return service.addCompany(new Company(name, email, password));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("/customer")
	public Customer customerSignUp(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam String password) {

		try {
			return service.addCustomer(new Customer(firstName, lastName, email, password));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
