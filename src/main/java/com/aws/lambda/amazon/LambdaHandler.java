package com.aws.lambda.amazon;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.sns.model.PublishResult;
import com.aws.lambda.commons.HttpClient;
import com.aws.lambda.commons.JsonToObject;
import com.aws.lambda.constants.LambdaConstants;
import com.aws.lambda.models.RedditResponse;
import com.aws.lambda.utils.LambdaUtils;
import lombok.extern.java.Log;

@Log
public class LambdaHandler implements RequestHandler<ScheduledEvent, String> {

    public String handleRequest(ScheduledEvent scheduledEvent, Context context) {
        log.info("Starting lambda to send cute dog pics");

        //send get request to reddit
        String response = HttpClient.sendGetRequest(LambdaConstants.REDDIT_URL + LambdaConstants.RARE_PUPPERS_URL);
        RedditResponse redditResponse = JsonToObject.redditResponse(response);

        //form message to be sent
        String message = LambdaUtils.formatRedditResponse(redditResponse);
        log.info("Message to be sent:\n" + message);

        //send text of dog pics
        PublishResult result = SnsService.sendMessage(message);
        log.info(result.toString());

        log.info("Ending lambda to send cute dog pics");
        return null;
    }
}
