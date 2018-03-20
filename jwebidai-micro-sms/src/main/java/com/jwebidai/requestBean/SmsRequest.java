package com.jwebidai.requestBean;

/**
 * Created by wjh on 2018/3/8.
 */
public class SmsRequest {
    private String phone;
    private String imei;
    private Integer os_type;
    private String appPkgName;
    private String channel;
    private String apk_version;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getOs_type() {
        return os_type;
    }

    public void setOs_type(Integer os_type) {
        this.os_type = os_type;
    }

    public String getAppPkgName() {
        return appPkgName;
    }

    public void setAppPkgName(String appPkgName) {
        this.appPkgName = appPkgName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getApk_version() {
        return apk_version;
    }

    public void setApk_version(String apk_version) {
        this.apk_version = apk_version;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phone='" + phone + '\'' +
                ", imei='" + imei + '\'' +
                ", os_type=" + os_type +
                ", appPkgName='" + appPkgName + '\'' +
                ", channel='" + channel + '\'' +
                ", apk_version='" + apk_version + '\'' +
                '}';
    }
}
