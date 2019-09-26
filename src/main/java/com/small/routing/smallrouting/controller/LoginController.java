package com.small.routing.smallrouting.controller;

import com.github.qcloudsms.SmsSenderUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.google.gson.Gson;
import com.small.routing.common.ResponseMsg;
import com.small.routing.common.StatusCode;
import com.small.routing.common.StringUtil;
import com.small.routing.smallrouting.entity.LoginSendMsgEntity;
import com.small.routing.smallrouting.entity.PhotoInfo;
import com.small.routing.smallrouting.inter.LoginMsgService;
import com.small.routing.smallrouting.mapper.PhotoInfoDAO;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.hcm.v20181106.HcmClient;
import com.tencentcloudapi.hcm.v20181106.models.EvaluationRequest;
import com.tencentcloudapi.hcm.v20181106.models.EvaluationResponse;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/api/v1/login")
@RestController
@CrossOrigin
public class LoginController {

    private final Logger logger = LogManager.getLogger(UserConroller.class);

    @Autowired
    private LoginMsgService loginMsgService;

    @Autowired
    private PhotoInfoDAO photoInfoDAO;

    @Value("${msg.template.id}")
    private int TEMPLATE_ID ;

    @Value(value = "${msg.app.id}")
    private int APP_ID;

    @Value(value = "${msg.app.key}")
    private String APP_KEY;

    @Value(value = "${msg.timeout}")
    private String MSG_TIME_OUT;

    @Value(value = "${photo.app.key}")
    private String PHOTO_APP_KEY;

    @Value(value = "${photo.app.id}")
    private String PHOTO_APP_ID;

    @Value(value = "${photo.save.path}")
    private String PATH;

    /**
     * 短信随机码
     * @param bodyMap
     * @return
     * @throws HTTPException
     * @throws IOException
     */
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsg sendMsg(@RequestBody Map<String, Object> bodyMap)
            throws HTTPException, IOException {
        String phoneNum = bodyMap.get("phoneNum").toString();
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

    /**
     * 查询短信随机码记录
     * @return
     */
    @RequestMapping(value = "/qryLoginMsg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsg qryLoginMsg(){
        System.out.println(loginMsgService.selectAll());
        return new ResponseMsg(HttpStatus.OK.value(), loginMsgService.selectAll());
    }

    /**
     * 身份证识别
     * @param bodyMap
     * @return
     * @throws TencentCloudSDKException
     */
    @RequestMapping(value = "/sGetPhotoMsg", method = RequestMethod.POST)
    public ResponseMsg sGetPhotoMsg(@RequestBody Map<String, Object> bodyMap) throws TencentCloudSDKException {
        String base64Img = StringUtil.obj2Str(bodyMap.get("IMG_BASE"));
        String frontOrBack = StringUtil.obj2Str(bodyMap.get("CARD_SIDE"));
        return new ResponseMsg(HttpStatus.BAD_REQUEST.value(), getPhotoMsg(base64Img, frontOrBack));
    }
    private String getPhotoMsg(String base64Img, String frontOrBack) throws TencentCloudSDKException {
        Credential cred = new Credential(PHOTO_APP_ID, PHOTO_APP_KEY);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("ocr.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);
        String img =base64Img.split(",")[1];

        Map<String, Object> map = new HashMap<>();
        map.put("CardSide", frontOrBack);
        map.put("ImageBase64", img);
        Gson gson = new Gson();
        IDCardOCRRequest req = IDCardOCRRequest.fromJsonString(gson.toJson(map), IDCardOCRRequest.class);
        IDCardOCRResponse resp = client.IDCardOCR(req);
        return IDCardOCRRequest.toJsonString(resp);
    }


    /**
     * 上传图片
     * @param bodyMap
     * @return
     */
    @RequestMapping(value = "/sUploadImg", method = RequestMethod.POST)
    public ResponseMsg sUploadImg(@RequestBody Map<String, Object> bodyMap) {
        String base64Img = StringUtil.obj2Str(bodyMap.get("IMG_BASE"));
        String phoneName = StringUtil.obj2Str(bodyMap.get("PHONE_NAME"));
        String img = "";
        if (base64Img.startsWith("data:image/jpeg;base64")) {
            img=base64Img.split(",")[1];
        }
        String fileName = getFileName(phoneName);
        PhotoInfo photoInfo = new PhotoInfo(fileName, PATH);
        photoInfoDAO.insert(photoInfo);
        return new ResponseMsg(HttpStatus.BAD_REQUEST.value(), generateImageFromBase64(img,
                PATH+fileName));
    }

    private String getFileName(String fileName){
        String fileNameNew = fileName;
        if (StringUtil.isNotEmptyOrNull(fileName)) {
            int index = fileName.lastIndexOf(".");
            String idType = fileName.substring(index);
            fileNameNew = UUID.randomUUID().toString() + idType;
        }
        return fileNameNew;
    }

    // base64字符串转化成图片
    public static boolean generateImageFromBase64(String imgStr, String path) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i){
                if (b[i] < 0){// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            // String imgFilePath = "d://222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
