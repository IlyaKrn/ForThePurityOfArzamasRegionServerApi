package com.example.ForThePurityOfArzamasRegionServerApi;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.CreateUserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
class ForThePurityOfArzamasRegionServerApiApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ProjectRequestRepository requestRepository;

	@Test
	void CreateUserUseCase(){
		// корректные значения
		CreateUserUseCase useCase = new CreateUserUseCase(userRepository, imageRepository, new UserRequest("qq", "qqq", 123, "qqqq", "qqqqq", false, true, true, true, 123L, null));
		UserResponse response = useCase.execute().getResponse();
		if(!response.equals(new UserResponse(response.getId(), "qq", "qqq", 0, "qqqq", "qqqqq", false, false, false, false, response.getLast_session(), null))) {
			fail();
		}

		// null все
		try{
			new CreateUserUseCase(userRepository, imageRepository,new UserRequest(null, null, null, null, null, null, null, null, null, null, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if(!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null email
		try{
			new CreateUserUseCase(userRepository, imageRepository, new UserRequest(null, "null", 123, "qqqq", "qqqqq", true, true, true, true, 123L, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null password
		try{
			new CreateUserUseCase(userRepository, imageRepository, new UserRequest("qq", null, 123, "qqqq", "qqqqq", true, true, true, true, 123L, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null first_name
		try{
			new CreateUserUseCase(userRepository, imageRepository, new UserRequest("qq", "null", 123, null, "qqqqq", true, true, true, true, 123L, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null last_name
		try{
			new CreateUserUseCase(userRepository, imageRepository, new UserRequest("qq", "null", 123, "qqqq", null, true, true, true, true, 123L, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}
	}
}
