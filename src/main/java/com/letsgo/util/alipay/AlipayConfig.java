package com.letsgo.util.alipay;

import java.io.FileWriter;
import java.io.IOException;
/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/22 2:15 PM
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102700771141";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDC6elw1W2iJM2qU99xNkMSuvHxep1kmYHiV6YlzeQZUTAQG8sMidPTK2nL/x1txoq9GbZH++55hhYzwnfxrfu0REEkE8oGYAzOwrXKNZmpWmBfleUPuSgwpOKWoTApVvfLHAV2FmxJjk5+TYIRVYUI4xscJ+yzD9ocwPrc79oHHkuCsMT6VGKBnNcc9F4mO4H4lpJI3dpaKuJgva+zzZVBV3AQJ9ZU6HfYBHMIMpQYzfvxcF0fBhF0/uG77M29q/bjjzca3e22zfCvOcEyUiUzyE3XOge2L4bVg6J1Z8ugCZrTn6bcHyIRgdgqAKi2anESpJmHI/r8k2yG3SuCe4mjAgMBAAECggEAP/VDjavrIraS6h5Fhr+4eK/xbjy+4uDgVuZN9b++5nPWoqKMGVwsrvFi8PaQsJHiurRyNOK9WREfvfJFK3/cxUVUXsNDmp3v9fS8dFLsTj8CurrUBDnMr1GbrLPNuvL3sqVKQXqQI/7XUOicEpYOZmEnZzDOod9EwgStDMyDrau2U9yYYojx9974adGGRbiFj6nyAWfHuP86QvUBPc1mhL2ywWCylHnPay6m6V4tCmtVsg/V2/dbJfO+pZDo8zA3ekXuEt2gzfVJWn6LcpSv9S+8psn/06v6O1SYnWl/HW+5m+lYiLuhQEzwO8EY/ce2PonNEqlUEg7V8iDKsYDqaQKBgQD/JbXwGlr+TW32RzjGEOGoIP92eqMUtEvb+WKZu7n54wPuxY1DCf6Mo9t41uMj5zHTaU7QZ7MXDlOjJZoD2VhLgIqs/qFXHCt06JvopK89XdtnTjqAt/zHfoPfuZXYHrCY/K/TPCtFU85bSmy3/QOtJp6T2k3+OHDFtoIDRp4aLQKBgQDDkKs2mFGekoZSvKOzhQnpLCzwXOp9EhS+4Yyg9wJs4X7/QscAm16g/jWYO48MquK2lHgqZtA7m8+CbPMbvns7LADnkpqQZRy/dz2Tx/ouTFQKX1l32s8xlLNcMNNEK0Ijf0NLLYcCqCDhkBPgvQMOLWy5cm3XNCq/FkXKjl2lDwKBgDBbcFkaVaae/DlSFFONvYcFAgXthl0VMs91O5fReZT/zq4BaR2Mll8Ha0BC0ffrCIl+Cv76uHmlMHraVI9oMRDWQvI4rFF7OE1HDM2R8Nibokq5C+N7e4A/3TSY0uqMHoJQWe6aF8Vi9dhdia9xV7IIgMOa+280OUNvrMJ8LtjdAoGAHs6nag5MrUI/zsDQuPL4QLcozSSV++XhRsCZOrIuu6A6NVP7rWjMW2gwYS7gtgoldMWmuGdB3mIJKm6nTXOQTh38dn5twtlq0e6i0KjsDGcRxiCHI45gnVrjdl5qqY52+MiPfURKxGxf4aikuP6NgKo/OmeATip1oWyMyf/rWJkCgYBI/eBPFBqFH44PbiPclLhwx7rs7SwomlYMUsHUFaLFKRAujM2ayW6C2q8YyWu8tJ+d+VD3ORjMjsqJYMb0qasFmfD8E/qbGgPx2YnqMtEK/J6LRmRfxm9mp/dUfvw0VKslC7WqYThx/3JCHsCRgHk8CZtIN5igfHQMeKSB5BnZ8w==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuraCuKby7CIrVoXv0OWjvpuKdAQvMaL8/rfOb8KK5YK9N35IENRcrOkUNeCWZgVuzmlwfa6jyGzydH2C6yi2hKUPw0DieddW1wNF6amldsThRBkoCuMsZt6W9FXkDaUj0ljPZs2mu2nEKDobznFdAH0ITDOkK8VKa/Yc0dnEISN3rImek3GrR+/EOcO8yJ/kqMtpZ7TwAUTOm+xE+cHQS3gXbZalaxwQG16v2pdZ3RbWB6yRN8ovTNdvXxa3w4nCysV02Wsy6H1AsXlE1uvEf+UigQ2C1IdcTIAKlVAPY7arm0g/oDtfL38H0a5Cjy7wmR0rWfq0qXD8B0q9RIxpKwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/callBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/callBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    //https://openapi.alipaydev.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
