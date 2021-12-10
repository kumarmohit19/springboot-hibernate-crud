package com.spring.project.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.config.AppConfig;
import com.spring.project.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class })
public class SpringRestTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void addUserTest_ok() throws Exception {
		User u = new User();
		u.setId(1);
		u.setName("jim");
		u.setEmail("jim@split.com");
		byte[] iJson = toJson(u);
		mockMvc.perform(
				post("/user").content(iJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addUserTest1_ok() throws Exception {
		User u = new User();
		u.setId(2);
		u.setName("jim1");
		u.setEmail("jim1@split.com");
		byte[] iJson = toJson(u);
		mockMvc.perform(
				post("/user").content(iJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void retrievetest_ok() throws Exception {
		MvcResult res = mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk()).andReturn();
		assert (res.getResponse().getContentAsString().contains("jim@split.com"));

	}

	@Test
	public void deleteEmployee_ok() throws Exception {
		User u = new User();
		u.setId(4);
		u.setName("jim4");
		u.setName("jim4@split.com");
		byte[] iJson = toJson(u);
		mockMvc.perform(
				post("/user").content(iJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mockMvc.perform(delete("/user/4").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}

}
