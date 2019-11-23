package com.aws.lambda.amazon;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.aws.lambda.constants.LambdaConstants;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;

@Log
public class SnsService {

    private static AmazonSNS getSnsClient(){
        return AmazonSNSClient.builder()
                .withRegion("us-east-1")
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .build();
    }

    private static Map<String, MessageAttributeValue> getMessageAttributes(){
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put(LambdaConstants.AWS_SNS_SENDER_ID, new MessageAttributeValue()
                                                                    .withStringValue("Justin")
                                                                    .withDataType(LambdaConstants.AWS_SNS_DATA_TYPE_STRING));
        smsAttributes.put(LambdaConstants.AWS_SNS_SMS_TYPE, new MessageAttributeValue()
                                                                    .withStringValue("Promotional")
                                                                    .withDataType(LambdaConstants.AWS_SNS_DATA_TYPE_STRING));

        return smsAttributes;
    }

    public static PublishResult sendMessage(String message){
        return getSnsClient().publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(System.getenv("number"))
                .withMessageAttributes(getMessageAttributes()));
    }
}
