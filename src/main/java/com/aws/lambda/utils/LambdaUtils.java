package com.aws.lambda.utils;

import com.aws.lambda.models.RedditResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LambdaUtils {

    public static String formatRedditResponse(RedditResponse redditResponse) {

        StringBuilder message = new StringBuilder();
        //only want the first three posts, to get only three pictures don't want to send anymore than that
        redditResponse.getData().getChildren().subList(0, 3).forEach(
                redditDataChildren -> message
                        .append(redditDataChildren.getData().getTitle()).append("\n")
                        .append(redditDataChildren.getData().getUrl()).append("\n")
        );
        return message.toString();
    }
}
