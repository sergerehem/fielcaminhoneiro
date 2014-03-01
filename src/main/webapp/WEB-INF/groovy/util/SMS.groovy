package util


import com.twilio.sdk.TwilioRestClient
import com.twilio.sdk.resource.factory.MessageFactory
import com.twilio.sdk.resource.instance.Message

import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair

public class SMS {

    private static final def ACCOUNT_SID = "AC149cc016e621cf5b75263700bf229fef"
    private static final def AUTH_TOKEN = "13524f57f6cf0c9113ab6e0b4a6f1781"
    private static final def PHONE_NUMBER = "+19292276318"
    private static final def CALLBACK = "http://www.fielcaminhoneiro.com.br/twilio_callback"

    def sendSMS(number, message) {

        number = "+55" + number.replaceAll('\\(','').replaceAll("\\)","").replaceAll("-","")

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("From", PHONE_NUMBER));
        params.add(new BasicNameValuePair("To", number));
        params.add(new BasicNameValuePair("Body", message));
        params.add(new BasicNameValuePair("StatusCallback", CALLBACK))

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message smsMessage = messageFactory.create(params);

        return (smsMessage.getSid());
    }

}