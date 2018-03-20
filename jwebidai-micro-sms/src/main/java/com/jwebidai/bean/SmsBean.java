package com.jwebidai.bean;

/**
 * Created by wjh on 2018/3/8.
 */
public class SmsBean {
    private int sequence = 0; //唯一序列号
    private String prefix = ""; //扩展端口
    private String[] phones = new String[1]; //手机号码
    private String content = ""; //短信内容
    private String pubcode;
    private String vry;
    private int smsType;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubcode() {
        return pubcode;
    }

    public void setPubcode(String pubcode) {
        this.pubcode = pubcode;
    }

    public String getVry() {
        return vry;
    }

    public void setVry(String vry) {
        this.vry = vry;
    }

    public int getSmsType() {
        return smsType;
    }

    public void setSmsType(int smsType) {
        this.smsType = smsType;
    }
}
