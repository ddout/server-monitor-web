package com.cdhy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath*:conf/spring*.xml" })
public class TestAccessController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
	this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testRegist() throws Exception {
	//
	ResultActions ra = mockMvc
		.perform((post("/access/regist.do").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
			.param("rpwd", "64535asdasdasdasd437539")
			.param("name", "test5")
			.param("ip", "192.168.0.113")
			.param("note", "测试用的")
			.param("mstate_str", "alksjdlaksjd kasjdlkasjd alksdjalks alksdjalks aksdj lasjd aj lasjdlkaj d")))
		.andExpect(status().isOk()).andDo(print());
	JSONObject result = JSONObject.fromObject(ra.andReturn().getResponse().getContentAsString());
	System.out.println(result);
    }

    @Test
    public void testVA() throws Exception {
	//
	ResultActions ra = mockMvc
		.perform((post("/access/viewA.do").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)))
		.andExpect(status().isOk()).andDo(print());
	JSONObject result = JSONObject.fromObject(ra.andReturn().getResponse().getContentAsString());
	System.out.println(result);
    }

    @Test
    public void testVB() throws Exception {
	//
	ResultActions ra = mockMvc.perform((post("/access/viewB.do").characterEncoding("UTF-8")
		.contentType(MediaType.APPLICATION_JSON).param("name", "test"))).andExpect(status().isOk())
		.andDo(print());
	JSONObject result = JSONObject.fromObject(ra.andReturn().getResponse().getContentAsString());
	System.out.println(result);
    }
}
