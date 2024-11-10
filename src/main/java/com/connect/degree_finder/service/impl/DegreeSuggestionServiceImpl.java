package com.connect.degree_finder.service.impl;

import com.connect.degree_finder.model.request.ApiRequest;
import com.connect.degree_finder.model.response.ApiResponse;
import com.connect.degree_finder.model.UserResponseDegree;
import com.connect.degree_finder.service.DegreeSuggestionService;
import com.connect.degree_finder.util.Messages;
import com.connect.degree_finder.util.RequestStatus;
import com.connect.degree_finder.util.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeSuggestionServiceImpl implements DegreeSuggestionService {

    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory.getLogger(DegreeSuggestionServiceImpl.class);

    @Override
    public ApiResponse suggestDegree(ApiRequest apiRequest) {
        logger.info("Degree Suggest Starts");

        ApiResponse apiResponse = new ApiResponse();
        List<UserResponseDegree> userResponseDegrees = apiRequest.getUserResponseDegrees();
        boolean enjoysMath = false;
        boolean enjoysScience = false;
        boolean interestedInTech = false;
        boolean creative = false;
        boolean interestedInHealthcare = false;
        boolean interestedInArtDesign = false;

        for (UserResponseDegree response : userResponseDegrees) {
            switch (response.getQuestionId()) {
                case 1:
                    enjoysMath = response.getAnswer().equalsIgnoreCase("Y");
                    break;
                case 2:
                    enjoysScience = response.getAnswer().equalsIgnoreCase("Y");
                    break;
                case 3:
                    interestedInTech = response.getAnswer().equalsIgnoreCase("Y");
                    break;
                case 4:
                    creative = response.getAnswer().equalsIgnoreCase("Y");
                    break;
                case 5:
                    interestedInHealthcare = response.getAnswer().equalsIgnoreCase("Y");
                    break;
                case 6:
                    interestedInArtDesign = response.getAnswer().equalsIgnoreCase("Y");
                    break;
            }
        }

        if (enjoysMath && interestedInTech) {
            apiResponse.setDegreeName("Computer Science or Engineering");
        } else if (enjoysScience && interestedInHealthcare) {
            apiResponse.setDegreeName("Medicine or Health Sciences");
        } else if (creative && !interestedInTech && interestedInArtDesign) {
            apiResponse.setDegreeName("Design or Media Studies");
        } else if (enjoysMath && enjoysScience) {
            apiResponse.setDegreeName("Physics or Mathematics");
        } else {
            apiResponse.setDegreeName("General Studies or Business");
        }

        apiResponse.setStatus(RequestStatus.SUCCESS.getStatus());
        apiResponse.setResponseCode(ResponseCodes.SUCCESS);
        apiResponse.setMessage(messages.getMessageForResponseCode(ResponseCodes.DEGREE_SUGGEST_SUCCESS, null));

        logger.info("Degree Suggest Ends");
        return apiResponse;
    }
}
