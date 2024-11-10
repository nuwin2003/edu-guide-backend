package com.connect.degree_finder.controller;

import com.connect.degree_finder.model.request.ApiRequest;
import com.connect.degree_finder.model.response.ApiResponse;
import com.connect.degree_finder.service.StreamSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/stream")
public class StreamSuggestionController {

    @Autowired
    StreamSuggestionService streamSuggestionService;

    @PostMapping("/suggest")
    public ApiResponse suggestStream(@RequestBody ApiRequest apiRequest){
        return streamSuggestionService.suggestStream(apiRequest);
    }
}
