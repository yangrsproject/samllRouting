package com.small.routing.smallrouting.control;

import com.github.qcloudsms.SmsSenderUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.small.routing.common.ResponseMsg;
import com.small.routing.common.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RequestMapping(value = "/api/v1/login")
@Controller
public class LoginController {

    private final Logger logger = LogManager.getLogger(UserConroller.class);

    @Value("${msg.template.id}")
    private int TEMPLATE_ID ;

    @Value(value = "${msg.app.id}")
    private int APP_ID;

    @Value(value = "${msg.app.key}")
    private String APP_KEY;

    @Value(value = "${msg.timeout}")
    private String MSG_TIME_OUT;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public ResponseMsg sendMsg(@RequestBody(required = true) String phoneNum)
            throws HTTPException, IOException {
        SmsSingleSender smsSingleSender = new SmsSingleSender(APP_ID, APP_KEY);
        ArrayList<String> params = new ArrayList<>();
        long randomMsg = SmsSenderUtil.getRandom();
        logger.info("用户：{}，短信随机码为：{}", phoneNum, randomMsg);
        params.add(randomMsg + "");
        params.add(MSG_TIME_OUT);
        SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86", phoneNum,
                TEMPLATE_ID, params, "", "", "");
        if (smsSingleSenderResult.getResponse().statusCode == 200 && smsSingleSenderResult.getResponse().reason.equals("OK")) {
            return new ResponseMsg(HttpStatus.OK.value(), StatusCode.SUCCESS);
        }
        return new ResponseMsg(HttpStatus.BAD_REQUEST.value(), StatusCode.FAILED);
    }
}
