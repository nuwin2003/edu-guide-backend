package com.connect.degree_finder.model.request;

import com.connect.degree_finder.model.UserResponseDegree;
import com.connect.degree_finder.model.UserResponseStream;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRequest {
    private List<UserResponseDegree> userResponseDegrees;
    private List<UserResponseStream> userResponseStreams;
}
