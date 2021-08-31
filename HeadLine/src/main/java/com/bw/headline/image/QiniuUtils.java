package com.bw.headline.image;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.util.Date;

public
/**
 *   @Author:YaPeng
 *   @Date:2021/8/30
 *   @Email:3536815334@qq.com
 *   封装阿里Oss上传图片并返回URL
 */
class QiniuUtils {
    //与个人的存储区域有关
    private static final String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    //上传仓库名
    private static final String BUCKET_NAME = "shukespicture";

    private static OSS getOSSClient(Context context) {
        OSSCredentialProvider credentialProvider =
                new OSSPlainTextAKSKCredentialProvider("LTAI5tALFad2BVib2hhbDoCi" ,
                        "KnTVaa6uagTCFH0LvGRTvZQNmDKMOE");
        return new OSSClient( context, ENDPOINT, credentialProvider);
    }
    private static String upload(String objectKey, String path,Context context) {
        // 构造上传请求
        PutObjectRequest request =
                new PutObjectRequest(BUCKET_NAME,
                        objectKey, path);
        try {
            //得到client
            OSS client = getOSSClient(context);
            //上传获取结果
            PutObjectResult result = client.putObject(request);
            //获取可访问的url
            String url = client.presignPublicObjectURL(BUCKET_NAME, objectKey);
            //格式打印输出
            Log.i("TAG",String.format("PublicObjectURL:%s", url));
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传普通图片
     *
     * @param path 本地地址
     * @return 服务器地址
     */
    public static String uploadImage(String path,Context context) {
        return upload(getDateString(), path,context);
    }

    /**
     * 获取时间
     *
     * @return 时间戳 例如:201805
     */
    private static String getDateString() {
        return DateFormat.format("yyyyMMddHHmmss", new Date()).toString();
    }
}
