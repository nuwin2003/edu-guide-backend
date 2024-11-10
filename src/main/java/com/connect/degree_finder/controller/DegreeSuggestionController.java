package com.connect.degree_finder.controller;

import com.connect.degree_finder.model.request.ApiRequest;
import com.connect.degree_finder.model.response.ApiResponse;
import com.connect.degree_finder.service.DegreeSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/degree")
public class DegreeSuggestionController {

    @Autowired
    DegreeSuggestionService degreeSuggestionService;

    @PostMapping("/suggest")
    public ApiResponse suggestDegree(@RequestBody ApiRequest apiRequest){
        return degreeSuggestionService.suggestDegree(apiRequest);
    }
}
