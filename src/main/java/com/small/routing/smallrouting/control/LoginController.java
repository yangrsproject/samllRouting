package com.small.routing.smallrouting.control;

import com.github.qcloudsms.SmsSenderUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.small.routing.common.ResponseMsg;
import com.small.routing.common.StatusCode;
import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import com.small.routing.smallrouting.ser_inter.LoginMsgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@RequestMapping(value = "/api/v1/login")
@RestController
public class LoginController {

    private final Logger logger = LogManager.getLogger(UserConroller.class);

    @Autowired
    private LoginMsgService loginMsgService;

    @Value("${msg.template.id}")
    private int TEMPLATE_ID ;

    @Value(value = "${msg.app.id}")
    private int APP_ID;

    @Value(value = "${msg.app.key}")
    private String APP_KEY;

    @Value(value = "${msg.timeout}")
    private String MSG_TIME_OUT;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsg sendMsg(@RequestParam(name = "phoneNum") String phoneNum)
            throws HTTPException, IOException {
        SmsSingleSender smsSingleSender = new SmsSingleSender(APP_ID, APP_KEY);
        ArrayList<String> params = new ArrayList<>();
        long randomMsg = SmsSenderUtil.getRandom();
        logger.info("用户：{}，短信随机码为：{}", phoneNum, randomMsg);
        params.add(randomMsg + "");
        params.add(MSG_TIME_OUT);
        SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86", phoneNum,
                TEMPLATE_ID, params, "", "", "");
        LoginSendMsgEntity loginSendMsgEntity = new LoginSendMsgEntity();
        loginSendMsgEntity.setPhone_num(phoneNum);
        loginSendMsgEntity.setMsg_content(randomMsg+"");
        loginSendMsgEntity.setOp_time(new Date(System.currentTimeMillis()));
        loginMsgService.insert(loginSendMsgEntity);
        if (smsSingleSenderResult.getResponse().statusCode == 200 && smsSingleSenderResult.getResponse().reason.equals("OK")) {
            return new ResponseMsg(HttpStatus.OK.value(), StatusCode.SUCCESS);
        }
        return new ResponseMsg(HttpStatus.BAD_REQUEST.value(), StatusCode.FAILED);
    }
}
