package com.rest.CustEmp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rest.CustEmp.controller.CustomerController;
import com.rest.CustEmp.service.ServiceImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CustEmpApplication.class)
@SpringBootTest
public class JunitTestCases {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock 
	private ServiceImpl service;
	
	@Autowired
    private WebApplicationContext wac;

	@InjectMocks
	private CustomerController customerController;
	
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	
	}
	
	@Test
	public void testSearchById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getById/112").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.customerId").exists())
		.andExpect(jsonPath("$.customerName").exists())
		.andExpect(jsonPath("$.contactNumber").exists())
		.andExpect(jsonPath("$.emailId").exists())
		.andExpect(jsonPath("$.customerId").value(112))
		.andDo(print());
	}
	
	@Test
	public void testViewAllCustomers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", Matchers.hasSize(8))).andDo(print());
	}
	
	@Test
	public void testget() throws Exception {
		final int customerId= 122;
		final String json = "{\"customerId\":\""+customerId+"}";
				/*+",\"customerName\":\"Nyaah\",\"contactNumber\":\"986755\",\"emailId\":\"nyaah@gmail.com\",\"dateOfBirth\":\"22/4/1993\"}";
		*/
		mockMvc.perform(get("/customers")
				.accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId", Matchers.is(122)));
//				.andExpect(jsonPath("$.customerName", Matchers.is("Nyaah")))
//				.andExpect(jsonPath("$.contactNumber", Matchers.is(986755)))
//				.andExpect(jsonPath("$.emailId", Matchers.is("nyaah@gmail.com")))
//				.andExpect(jsonPath("$.dateOfBirth", Matchers.is("22/4/1993")))
//				.andExpect(jsonPath("$.*", Matchers.hasSize(5)));
		
	}
	
	
	
	@Test
	public void testPost() throws Exception {

		String json = "{\"customerId\":\"122\",\"customerName\":\"Nyaah\",\"contactNumber\":\"986755\",\"emailId\":\"nyaah@gmail.com\",\"dateOfBirth\":\"22/4/1993\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.ALL).content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(5))).andDo(print());
		
		//	String json = "{\"customerId\":\"122\",\"customerName\":\"Nyaah\",\"contactNumber\":\"986755\",\"emailId\":\"nyaah@gmail.com\",\"dateOfBirth\":\"22/4/1993\"}";
		
		/*mockMvc.perform(post("/customer/add")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId", Matchers.is(122)))
				.andExpect(jsonPath("$.customerName", Matchers.is("Nyaah")))
				.andExpect(jsonPath("$.contactNumber", Matchers.is(986755)))
				.andExpect(jsonPath("$.emailId", Matchers.is("nyaah@gmail.com")))
				.andExpect(jsonPath("$.dateOfBirth", Matchers.is("22/4/1993")))
				.andExpect(jsonPath("$", Matchers.hasSize(5)));
		*/
	}
	
	@Test
	public void testDelete() throws Exception {
		String json = "{\"customerId\":\"122\",\"customerName\":\"Nyaah\",\"contactNumber\":\"986755\",\"emailId\":\"nyaah@gmail.com\",\"dateOfBirth\":\"22/4/1993\"}";
		
		mockMvc.perform(delete("/customer/delete/122")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
}
