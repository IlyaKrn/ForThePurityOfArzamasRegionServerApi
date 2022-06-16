package com.example.ForThePurityOfArzamasRegionServerApi;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.*;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.CreateProjectUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.CreateUserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

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
	ChatRepository chatRepository;
	@Autowired
	ProjectRequestRepository requestRepository;

	@Test
	void CreateUserUseCase(){
		// корректные значения
		CreateUserUseCase useCase = new CreateUserUseCase(userRepository, imageRepository, new UserRequest("qq", "qqq", 123, "qqqq", "qqqqq", false, true, true, true, 123L, null));
		UserResponse response = useCase.execute().getResponse();
		if(!response.equals(new UserResponse(response.getId(), "qq", "qqq", 0, "qqqq", "qqqqq", false, false, false, false, null, null))) {
			fail();
		}
		if (response.getId() == null)
			fail();

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


	@Test
	void CreateProjectUseCase(){
		// корректные значения
		CreateProjectUseCase useCase = new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, new ProjectMainRequest("title", "message", null, null, new Integer[] {1, 54}));
		ProjectResponse response = useCase.execute().getResponse();
		if(!response.equals(new ProjectResponse(response.getId(), "title", "message", response.getUpload_time(), null, Arrays.asList(new ImageResponse[]{new ImageResponse(1, "url", 100, 1000), null}), null, response.getChat_id()))) {
			fail();
		}
		if(response.getChat_id() == null || response.getUpload_time() == null)
			fail();
		if(response.getId() == null)
			fail();

		// null все
		try{
			new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, new ProjectMainRequest(null, null, null, null, null)).execute();
			fail();
		} catch (ResponseStatusException e){
			if(!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null title
		try{
			new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, new ProjectMainRequest(null, "message", null, null, new Integer[] {1, 54})).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}

		// null message
		try{
			new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, new ProjectMainRequest("title", null, null, null, new Integer[] {1, 54})).execute();
			fail();
		} catch (ResponseStatusException e){
			if (!e.getStatus().equals(HttpStatus.BAD_REQUEST)){
				fail();
			}
		}


	}

}
