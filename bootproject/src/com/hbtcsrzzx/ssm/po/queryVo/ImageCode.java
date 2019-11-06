package com.hbtcsrzzx.ssm.po.queryVo;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ImageCode implements Serializable{

    //验证码字符串
    private String code;
    //过期时间
    private LocalDateTime expireTime;
    //图片
    private BufferedImage image;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
