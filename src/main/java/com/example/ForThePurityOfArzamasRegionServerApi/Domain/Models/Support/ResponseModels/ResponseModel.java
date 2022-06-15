package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    T response;
    ResponseError error;
}
