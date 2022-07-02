package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Chat;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.MessageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Chat;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Event;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Message;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.EventRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.MessageRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.MessageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class CreateMessageUseCase {

    private ChatRepository chatRepository;
    private MessageRepository messageRepository;
    private ImageRepository imageRepository;
    private MessageRequest message;
    private Integer chatId;


    public CreateMessageUseCase(ChatRepository chatRepository, ImageRepository imageRepository, MessageRequest message) {
        this.chatRepository = chatRepository;
        this.imageRepository = imageRepository;
        this.message = message;
    }

    public ResponseModel<MessageResponse> execute() {
        ResponseModel<MessageResponse> response = new ResponseModel<>();

        Message m = new Message(null, message.getUser_id(), message.getImage_ids(), message.getMessage());
        m = messageRepository.save(m);
        try{

            Chat c = chatRepository.findById(chatId).get();
            Integer[] temp = new Integer[c.getMessage_ids().length+1];
            for (int i = 0; i < temp.length; i++) {
                if(i == temp.length-1){
                    temp[i] = m.getId();
                }else{
                    temp[i] = c.getMessage_ids()[i];
                }
            }



            ArrayList<ImageResponse> images = new ArrayList<>();
            if (m.getImage_ids() != null && m.getImage_ids().length > 0){
                for(Integer id : m.getImage_ids()){
                    ImageResponse image = null;
                    try {
                        Image i = imageRepository.findById(id).get();
                        image = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                    } catch (Exception ignored){

                    }
                    images.add(image);
                }

            }
            response.setResponse(new MessageResponse(m.getId(), m.getUser_id(), images, m.getMessage()));
            return response;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("chat with id %d not found", chatId), e);
        }




    }
}
