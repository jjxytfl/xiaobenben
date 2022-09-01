package com.example.xiaobenben;

import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider;
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials;
import com.tencent.qcloud.core.auth.SessionQCloudCredentials;
import com.tencent.qcloud.core.common.QCloudClientException;

import java.util.Date;

public class MySessionCredentialProvider
        extends BasicLifecycleCredentialProvider {

    @Override
    protected QCloudLifecycleCredentials fetchNewCredentials()
            throws QCloudClientException {

        // 首先从您的临时密钥服务器获取包含了密钥信息的响应

        //"sessionToken": "b2s9P5GpnGUtBv7fTaDV3kM6J4AnMkHac82b17bb4559b3fb15b8ba19aa28a017ONOx4c1Fo5zQ-tyPTpeT2VBl3JweF8cbHRqph07CN5ERBBdHzWxpS2w84F1GoFgVLghwQiaCxlFo2QBlkP1egf2m7RXtZTG5cauLurNdzdMvtua62Pg4Q4m8IQh9g3ZenqTQYWsH3263lsY_Ecc8VjF4rHjLcwBY3ggMOyBbzfKxABoeHjCuSrA5Zc_16lzjEwQPsgnaJ7d982cw9jon93WYaYg7SZ2Ib1XEaieQxz0wqn9CTD2voNiNN5wDYVSlwGSiWRJ7qwY_JVsIQkVWeFeh1IjpjVnHAg0ssBwlL9kIukVzaX6Mp7I-st4vLRPHAOI1TaYpgZZb2O9qgMkB2RVmfXfF7hPb9dGnRK__20ZMYgxtb_wF3Y9DoXbfsmARbBgV9KEczyhlRZ9Z3EgWeeFUiEYIS04-SepCPmEeRlhEV5M_lVGwelP5HEVWcmgI3DB68osx85DNH4r8m2IlmNLfqw9fRCpWgDZ7a6Ga_PPWMP3ilYlg-x5f9GF22Y30Ck5XALSgVkrckBS27aMOv-mWjMa4gQ7Xme5lVpj8QYTJMRAj3m44iKRL-Yr4KaPJ",
        //"tmpSecretId": "AKIDgGx_AGhWINsowGaf-ahV-eOx50CwmEB_AhLplySiw0YJf3JoR4k1uygcTEgzJTM1",
        //"tmpSecretKey": "vT9uCSFuZQQtzOV8er8R+VKqU+scyQgHcrXrJi47OIc="

        // 然后解析响应，获取临时密钥信息
        String tmpSecretId = "AKIDgGx_AGhWINsowGaf-ahV-eOx50CwmEB_AhLplySiw0YJf3JoR4k1uygcTEgzJTM1"; // 临时密钥 SecretId
        String tmpSecretKey = "vT9uCSFuZQQtzOV8er8R+VKqU+scyQgHcrXrJi47OIc="; // 临时密钥 SecretKey
        String sessionToken = "b2s9P5GpnGUtBv7fTaDV3kM6J4AnMkHac82b17bb4559b3fb15b8ba19aa28a017ONOx4c1Fo5zQ-tyPTpeT2VBl3JweF8cbHRqph07CN5ERBBdHzWxpS2w84F1GoFgVLghwQiaCxlFo2QBlkP1egf2m7RXtZTG5cauLurNdzdMvtua62Pg4Q4m8IQh9g3ZenqTQYWsH3263lsY_Ecc8VjF4rHjLcwBY3ggMOyBbzfKxABoeHjCuSrA5Zc_16lzjEwQPsgnaJ7d982cw9jon93WYaYg7SZ2Ib1XEaieQxz0wqn9CTD2voNiNN5wDYVSlwGSiWRJ7qwY_JVsIQkVWeFeh1IjpjVnHAg0ssBwlL9kIukVzaX6Mp7I-st4vLRPHAOI1TaYpgZZb2O9qgMkB2RVmfXfF7hPb9dGnRK__20ZMYgxtb_wF3Y9DoXbfsmARbBgV9KEczyhlRZ9Z3EgWeeFUiEYIS04-SepCPmEeRlhEV5M_lVGwelP5HEVWcmgI3DB68osx85DNH4r8m2IlmNLfqw9fRCpWgDZ7a6Ga_PPWMP3ilYlg-x5f9GF22Y30Ck5XALSgVkrckBS27aMOv-mWjMa4gQ7Xme5lVpj8QYTJMRAj3m44iKRL-Yr4KaPJ"; // 临时密钥 Token
        long expiredTime = new Date().getTime()+3000;//临时密钥有效截止时间戳，单位是秒

        //建议返回服务器时间作为签名的开始时间，避免由于用户手机本地时间偏差过大导致请求过期
        // 返回服务器时间作为签名的起始时间
        long startTime = new Date().getTime(); //临时密钥有效起始时间，单位是秒

        // 最后返回临时密钥信息对象
        return new SessionQCloudCredentials(tmpSecretId, tmpSecretKey,
                sessionToken, startTime, expiredTime);
    }
}