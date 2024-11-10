package com.connect.degree_finder.service;

import com.connect.degree_finder.model.request.ApiRequest;
import com.connect.degree_finder.model.response.ApiResponse;

public interface DegreeSuggestionService {
    ApiResponse suggestDegree(ApiRequest apiRequest);
}
