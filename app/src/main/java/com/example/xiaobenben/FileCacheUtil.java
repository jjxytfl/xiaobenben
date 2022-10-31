package com.example.xiaobenben;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.xiaobenben.ben.Ben;
import com.example.xiaobenben.ben.Riji;
import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.transfer.COSXMLDownloadTask;
import com.tencent.cos.xml.transfer.COSXMLUploadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.cos.xml.transfer.TransferState;
import com.tencent.cos.xml.transfer.TransferStateListener;
import com.tencent.qcloud.core.auth.QCloudCredentialProvider;
import com.tencent.qcloud.core.auth.ShortTimeCredentialProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileCacheUtil {
    private static final String fileBen = "benData";

    public static final int BenImgurl = 1;
    public static final int BenName = 2;
    public static final int BenNewTime = 3;
    public static final int RijiNewTime = 4;
    public static final int RijiContent = 5;

    public static final int R_Ben = 6;
    public static final int R_Riji = 7;


    private String gethead(String head){
        return "<"+head+">";
    }
    private String gettail(String tail){
        return "</"+tail+">";
    }

    private int isheadSign(String str){
        switch (str){
            case "<BenImgurl>":
                return BenImgurl;
            case "<BenName>":
                return BenName;
            case "<BenNewTime>":
                return BenNewTime;
            case "<RijiNewTime>":
                return RijiNewTime;
            case "<RijiContent>":
                return RijiContent;
        }
        return 0;
    }
    private int istailSign(String str){
        switch (str){
            case "</Ben>":
                return R_Ben;
            case "</Riji>":
                return R_Riji;
        }

        return 0;
    }




    public void write_benlist(Context context, List<Ben> benList){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = context.openFileOutput(fileBen, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));

            String wristr = "";
            wristr += gethead("List<Ben>") + "\n";

            for(int i = 0;i < benList.size(); ++i){
                wristr += gethead("Ben") + "\n";

                wristr += gethead("BenName") + "\n";
                wristr += benList.get(i).getName() + "\n";
                wristr += gettail("BenName") + "\n";

                wristr += gethead("BenImgurl") + "\n";
                wristr += benList.get(i).getImgUrl() + "\n";
                wristr += gettail("BenImgurl") + "\n";

                wristr += gethead("BenNewTime") + "\n";
                wristr += benList.get(i).getNewTime() + "\n";
                wristr += gettail("BenNewTime") + "\n";


                for(int j = 0; j < benList.get(i).getRijiList().size(); ++j){
                    wristr += gethead("Riji") + "\n";


                    wristr += gethead("RijiNewTime") + "\n";
                    wristr += benList.get(i).getRijiList().get(j).getNewTime() + "\n";
                    wristr += gettail("RijiNewTime") + "\n";

                    wristr += gethead("RijiContent") + "\n";
                    wristr += benList.get(i).getRijiList().get(j).getContent() + "\n";
                    wristr += gettail("RijiContent") + "\n";


                    wristr += gettail("Riji") + "\n";
                }
                wristr += gettail("Ben") + "\n";
            }

            wristr += gettail("List<Ben>") + "\n";

            writer.write(wristr);


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null)
                    writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }



    }




    public List<Ben> read_benlist(Context context){

        List<Ben> benList = new ArrayList<>();
        Boolean isend = false;


        Ben ben = new Ben();
        Riji riji = new Riji();

        int temp;

        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = context.openFileInput(fileBen);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while(isend || (line = reader.readLine()) != null){

                if((temp = isheadSign(line)) != 0) {
                    line = reader.readLine();
                    switch (temp) {
                        case BenName:
                            ben.setName(line);
                            break;
                        case BenImgurl:
                            ben.setImgUrl(line);
                            break;
                        case BenNewTime:
                            ben.setNewTime(line);
                            break;
                        case RijiNewTime:
                            riji.setNewTime(line);
                            break;
                        case RijiContent:
                            riji.setContent(line);
                            break;
                    }
                }

                if((temp = istailSign(line)) != 0) {
                    switch (temp) {
                        case R_Ben:
                            benList.add(ben);
                            ben = new Ben();
                            break;
                        case R_Riji:
                            ben.getRijiList().add(riji);
                            riji = new Riji();
                            break;
                    }
                }


            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d("12345", "get: "+ content.toString());
        return benList;
    }
    public CosXmlService getcosXmlService(Context context){

        QCloudCredentialProvider myCredentialProvider =
                new ShortTimeCredentialProvider("id", "key", 1800);
        // 存储桶所在地域简称，例如广州地区是 ap-guangzhou
        String region = "ap-shanghai";

        // 创建 CosXmlServiceConfig 对象，根据需要修改默认的配置参数
        CosXmlServiceConfig serviceConfig = new CosXmlServiceConfig.Builder()
                .setRegion(region)
                .isHttps(true) // 使用 HTTPS 请求, 默认为 HTTP 请求
                .builder();

        // 初始化 COS Service，获取实例
        CosXmlService cosXmlService = new CosXmlService(context,
                serviceConfig, myCredentialProvider);

        return cosXmlService;
    }



    public void qCloudUpload_benList(Context context, List<Ben> benList){
        write_benlist(context,benList);

        CosXmlService cosXmlService = getcosXmlService(context);

        // 初始化 TransferConfig，这里使用默认配置，如果需要定制，请参考 SDK 接口文档
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        // 初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService,
                transferConfig);

        // 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
        String bucket = "test-1313466189";
        String cosPath = "bendata.txt"; //对象在存储桶中的位置标识符，即称对象键

        String srcPath = context.getFilesDir().getAbsoluteFile().getPath()+"/benData";
        File file = new File(srcPath);

        //若存在初始化分块上传的 UploadId，则赋值对应的 uploadId 值用于续传；否则，赋值 null
        String uploadId = null;

        // 上传文件
        COSXMLUploadTask cosxmlUploadTask = transferManager.upload(bucket, cosPath,
                srcPath, uploadId);

        //设置上传进度回调
        cosxmlUploadTask.setCosXmlProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                // todo Do something to update progress...
            }
        });
        //设置返回结果回调
        cosxmlUploadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                COSXMLUploadTask.COSXMLUploadTaskResult uploadResult =
                        (COSXMLUploadTask.COSXMLUploadTaskResult) result;
                Log.d("12345", "onSuccess: " + "ok");
            }

            // 如果您使用 kotlin 语言来调用，请注意回调方法中的异常是可空的，否则不会回调 onFail 方法，即：
            // clientException 的类型为 CosXmlClientException?，serviceException 的类型为 CosXmlServiceException?
            @Override
            public void onFail(CosXmlRequest request,
                               @Nullable CosXmlClientException clientException,
                               @Nullable CosXmlServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                } else {
                    serviceException.printStackTrace();
                }
            }
        });
        //设置任务状态回调, 可以查看任务过程
        cosxmlUploadTask.setTransferStateListener(new TransferStateListener() {
            @Override
            public void onStateChanged(TransferState state) {
                // todo notify transfer state
            }
        });
    }

    public void qCloudDownload_benList(Context context){
        // 高级下载接口支持断点续传，所以会在下载前先发起 HEAD 请求获取文件信息。
        // 如果您使用的是临时密钥或者使用子账号访问，请确保权限列表中包含 HeadObject 的权限。

        CosXmlService cosXmlService = getcosXmlService(context);

        // 初始化 TransferConfig，这里使用默认配置，如果需要定制，请参考 SDK 接口文档
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        //初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService,
                transferConfig);

        // 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
        String bucket = "test-1313466189";
        String cosPath = "bendata.txt"; //对象在存储桶中的位置标识符，即称对象键
        //本地目录路径
        String savePathDir = context.getFilesDir().getAbsoluteFile().getPath();
        //String savePathDir = Environment.getExternalStorageDirectory().getPath().toString() + "/download";
        Log.d("12345", "fun: " + savePathDir);
        //本地保存的文件名，若不填（null），则与 COS 上的文件名一样
        String savedFileName = "benData";


        Context applicationContext = context.getApplicationContext(); // application
        // context
        COSXMLDownloadTask cosxmlDownloadTask =
                transferManager.download(applicationContext,
                        bucket, cosPath, savePathDir, savedFileName);

        //设置下载进度回调
        cosxmlDownloadTask.setCosXmlProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                // todo Do something to update progress...
            }
        });
        //设置返回结果回调
        cosxmlDownloadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                COSXMLDownloadTask.COSXMLDownloadTaskResult downloadTaskResult =
                        (COSXMLDownloadTask.COSXMLDownloadTaskResult) result;
                Log.d("12345", "onProgress22: " + "ok");
            }

            // 如果您使用 kotlin 语言来调用，请注意回调方法中的异常是可空的，否则不会回调 onFail 方法，即：
            // clientException 的类型为 CosXmlClientException?，serviceException 的类型为 CosXmlServiceException?
            @Override
            public void onFail(CosXmlRequest request,
                               @Nullable CosXmlClientException clientException,
                               @Nullable CosXmlServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                } else {
                    serviceException.printStackTrace();
                }
            }
        });
        //设置任务状态回调，可以查看任务过程
        cosxmlDownloadTask.setTransferStateListener(new TransferStateListener() {
            @Override
            public void onStateChanged(TransferState state) {
                // todo notify transfer state
            }
        });


    }



    private String loadFromSDFile(File f) {
        String result=null;
        try {
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fin=new FileInputStream(f);
            fin.read(buff);
            fin.close();
            result=new String(buff,"UTF-8");
            Log.d("12345", "loadFromSDFile: "+result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }








}
