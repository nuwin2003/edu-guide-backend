package com.connect.degree_finder.service.impl;

import com.connect.degree_finder.model.UserResponseStream;
import com.connect.degree_finder.model.request.ApiRequest;
import com.connect.degree_finder.model.response.ApiResponse;
import com.connect.degree_finder.service.StreamSuggestionService;
import com.connect.degree_finder.util.Messages;
import com.connect.degree_finder.util.RequestStatus;
import com.connect.degree_finder.util.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamSuggestionServiceImpl implements StreamSuggestionService {

    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory.getLogger(StreamSuggestionServiceImpl.class);

    @Override
    public ApiResponse suggestStream(ApiRequest apiRequest) {
        logger.info("Stream Suggest Starts");

        ApiResponse apiResponse = new ApiResponse();
        List<UserResponseStream> userResponseStreams = apiRequest.getUserResponseStreams();
        int scienceScore = 0;
        int technologyScore = 0;
        int commerceScore = 0;
        int artsScore = 0;

        for (UserResponseStream response : userResponseStreams) {
            switch (response.getQuestionId()) {
                case 1:
                case 2:
                    if (response.getAnswer().equalsIgnoreCase("Y")) scienceScore++;
                    break;
                case 3:
                case 4:
                    if (response.getAnswer().equalsIgnoreCase("Y")) commerceScore++;
                    break;
                case 5:
                case 6:
                    if (response.getAnswer().equalsIgnoreCase("Y")) artsScore++;
                    break;
                case 7:
                case 8:
                    if (response.getAnswer().equalsIgnoreCase("Y")) technologyScore++;
                    break;
            }
        }

        if (scienceScore >= commerceScore && scienceScore >= artsScore && scienceScore >= technologyScore) {
            apiResponse.setStreamName("Science");
        } else if (commerceScore >= scienceScore && commerceScore >= artsScore && commerceScore >= technologyScore) {
            apiResponse.setStreamName("Commerce");
        } else if (technologyScore >= scienceScore && technologyScore >= commerceScore && technologyScore >= artsScore) {
            apiResponse.setStreamName("Technology");
        } else {
            apiResponse.setStreamName("Arts");
        }

        apiResponse.setStatus(RequestStatus.SUCCESS.getStatus());
        apiResponse.setResponseCode(ResponseCodes.SUCCESS);
        apiResponse.setMessage(messages.getMessageForResponseCode(ResponseCodes.DEGREE_SUGGEST_SUCCESS, null));

        logger.info("Stream Suggest Ends");
        return apiResponse;
    }
}
