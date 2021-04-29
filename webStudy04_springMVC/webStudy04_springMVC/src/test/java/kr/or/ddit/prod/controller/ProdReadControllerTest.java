package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class ProdReadControllerTest {
	
	@Inject
	private WebApplicationContext container;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(container).build();
	}

	@Test
	public void testListHTML() throws Exception {
		mockMvc.perform(get("/prod/prodList.do").param("page", "2"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pagingVO"))
			.andExpect(view().name("prod/prodList"))
			.andDo(log())
			.andReturn();
	}
	
	@Test
	public void testListJson() throws Exception {
		mockMvc.perform(get("/prod/prodList.do")
				.param("page", "2")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk())
		.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
		.andDo(log())
		.andReturn();
	}

	@Test
	public void testView() throws Exception {
		mockMvc.perform(get("/prod/prodView.do"))
			.andExpect(status().isBadRequest())
			.andReturn();
		
		mockMvc.perform(get("/prod/prodView.do").param("what", "P101000001"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("prod"))
			.andExpect(view().name("prod/prodView"))
			.andDo(log())
			.andReturn();
	}

}
