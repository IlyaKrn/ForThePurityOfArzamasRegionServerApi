package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Chat;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Chat;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class DeleteMessageUseCase {

    private ChatRepository chatRepository;
    private Integer id;
    private Integer chatId;



    public ResponseModel<EventResponse> execute() {
        ResponseModel<EventResponse> response = new ResponseModel<>();
        try{
            Chat temp = chatRepository.findById(chatId).get();
            ArrayList<Integer> t = new ArrayList<>();
            for (int i = 0; i < temp.getMessage_ids().length; i++) {
                if(!temp.getMessage_ids()[i].equals(id)){
                   t.add(temp.getMessage_ids()[i]);
                }
            }
            if(t.size() == temp.getMessage_ids().length){
                temp.setMessage_ids(t.toArray(new Integer[]{}));
                chatRepository.save(temp);
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("message with id %d not found", id), null);
            }


        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("chat with id %d not found", chatId), e);
        }
        response.setResponse(null);
        return response;
    }
}
