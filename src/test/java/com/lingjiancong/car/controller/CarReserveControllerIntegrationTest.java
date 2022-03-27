package com.lingjiancong.car.controller;

import com.lingjiancong.car.Application;
import com.lingjiancong.car.exception.ErrorDetails;
import com.lingjiancong.car.model.CarDO;
import com.lingjiancong.car.model.CarProductDO;
import com.lingjiancong.car.repository.CarProductRepository;
import com.lingjiancong.car.repository.CarRepository;
import com.lingjiancong.car.vo.req.CarReserveTimeReqVO;
import com.lingjiancong.car.vo.resp.CarReserveTimeRespVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarReserveControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private CarProductRepository carProductRepository;

	@Autowired
	private CarRepository carRepository;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	@Test
	public void contextLoads() {

	}

	private void init() {
		CarProductDO carProductDO1 = new CarProductDO(1L, "ToyotaCamry", "Toyota Camr", 2L);
		CarProductDO carProductDO2 = new CarProductDO(2L, "BMW650", "BMW 650", 2L);
		carProductRepository.save(carProductDO1);
		carProductRepository.save(carProductDO2);

		CarDO carDO1 = new CarDO("ToyotaCamry1", 1L);
		CarDO carDO2 = new CarDO("ToyotaCamry2", 1L);
		CarDO carDO3 = new CarDO("BMW6501", 2L);
		CarDO carDO4 = new CarDO("BMW6502", 2L);
		carRepository.save(carDO1);
		carRepository.save(carDO2);
		carRepository.save(carDO3);
		carRepository.save(carDO4);

	}

	@Test
	public void testGetCarReserveTime() {
	    init();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<CarReserveTimeRespVO> response = restTemplate.exchange(getRootUrl() + "/carReserveTime/ToyotaCamry1",
				HttpMethod.GET, entity, CarReserveTimeRespVO.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testCarReserveTime() {
	    init();
		CarReserveTimeReqVO req = new CarReserveTimeReqVO();
		req.setCarNo("BMW6501");
		req.setScheduleDate(LocalDate.now().toString());
		req.setStartTime(1000);
		req.setCount(3);

		ResponseEntity<Boolean> postResponse = restTemplate.postForEntity(getRootUrl() + "/carReserveTime", req, Boolean.class);
		assertNotNull(postResponse);
		assertEquals(postResponse.getBody(), true);
		ResponseEntity<?> responseEntity = restTemplate.postForEntity(getRootUrl() + "/carReserveTime", req, ErrorDetails.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

}
