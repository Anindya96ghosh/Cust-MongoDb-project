package com.rest.CustEmp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rest.CustEmp.service.ServiceImpl;

@SpringBootApplication
public class CustEmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustEmpApplication.class, args);
	}
	
private static Logger logger=  LogManager.getLogger(CustEmpApplication.class);
	 @Bean
	    public CommandLineRunner initialLoadData(ServiceImpl service) {
		 logger.warn("This is a warning");
			return (args) -> {
				/*service.addCustomer(new Customer(112, "Nyaah", 9844540, "nyaah@gmail.com", "22/5/1990"));
				service.addCustomer(new Customer(113, "Myra", 986755, "myra@gmail.com", "2/8/1990"));
				service.addCustomer(new Customer(114, "Riya", 9877765, "riya@gmail.com", "22/10/1990"));
				service.addCustomer(new Customer(115, "Alia", 980065, "alia@gmail.com", "12/7/1990"));
				service.addCustomer(new Customer(116, "Amyra", 9766434, "amyra@gmail.com", "6/5/1990"));
				service.addCustomer(new Customer(117, "Ryan", 9843433, "ryan@gmail.com", "18/5/1990"));
				service.addCustomer(new Customer(118, "John", 9807655, "john@gmail.com", "30/5/1990"));
				service.addCustomer(new Customer(119, "Charlie", 9987665, "charlie@gmail.com", "1/1/1990"));*/
				System.out.println("Database initially loaded");
			};
	    	
	    }
	
}
