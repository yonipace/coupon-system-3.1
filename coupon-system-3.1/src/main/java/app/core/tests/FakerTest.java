package app.core.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;

//@Component
public class FakerTest {

	private static Faker faker = new Faker();

	public static void main(String[] args) {

		List<Company> companies = new ArrayList<>();

		companies.add(generateRandomCompany());
		companies.add(generateRandomCompany());
		companies.add(generateRandomCompany());

//		companies.stream().forEach(e -> System.out.println(e));

		System.out.println("=============================");

		List<Customer> customers = new ArrayList<>();

		customers.add(generateRandomCustomer());
		customers.add(generateRandomCustomer());
		customers.add(generateRandomCustomer());

//		customers.stream().forEach(e -> System.out.println(e));

		System.out.println("=============================");

		List<Coupon> coupons = new ArrayList<>();

		coupons.add(generateRandomCoupon());
		coupons.add(generateRandomCoupon());
		coupons.add(generateRandomCoupon());

		coupons.stream().forEach(e -> System.out.println(e));

	}

	private static Company generateRandomCompany() {
		String name = faker.internet().domainWord();
		String email = name + "@email.com";
		String password = name;

		return new Company(name, email, password);
	}

	private static Customer generateRandomCustomer() {

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = firstName + "@email.com";
		String password = firstName;

		return new Customer(firstName, lastName, email, password);
	}

	private static Coupon generateRandomCoupon() {

		Category category = Category.values()[(int) (Math.random() * Category.values().length)];
		String title = faker.commerce().department();
		String description = faker.commerce().productName();
		int amount = (int) (Math.random() * 100);
		double price = Double.parseDouble(faker.commerce().price());
		LocalDate startDate = LocalDate.of(2022, 01, 01);
		LocalDate endDate = startDate.plusDays((int) (Math.random() * 100));
		Company company = null;

		return new Coupon(category, title, description, startDate, endDate, amount, price, company);
	}

}
