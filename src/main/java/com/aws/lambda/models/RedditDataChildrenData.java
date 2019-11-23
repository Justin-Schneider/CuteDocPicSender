package com.aws.lambda.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditDataChildrenData {

    private String title;

    private String url;
}
