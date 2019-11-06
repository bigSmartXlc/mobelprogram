package com.hbtcsrzzx.utils;


public class Result {

    private Integer code;
    private String msg;
    private Object obj;
    private boolean flag;
    private Long count;


    private Result() {
    }

    private Result(boolean flag, Integer code, String msg) {
        super();
        this.flag = flag;
        this.code = code;
        this.msg = msg;

    }


    public Result(Integer code, String msg, Object obj) {
        super();
        this.code = code;
        this.msg = msg;
        this.obj = obj;
        if (code == StatusCode.OK) {
            this.flag = true;
        }

        if (code == StatusCode.ERROR) {
            this.flag = false;
        }
    }

    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
        if (code == StatusCode.OK) {
            this.flag = true;
        }

        if (code == StatusCode.ERROR) {
            this.flag = false;
        }
    }

    private Result(boolean flag, Integer code, String msg, Object obj) {
        super();
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    private Result(boolean flag, Integer code, String msg, Object obj, Long count) {
        super();
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.obj = obj;
        this.count = count;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getmsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }

    public Object getobj() {
        return obj;
    }

    public void setobj(Object obj) {
        this.obj = obj;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * 返回成功消息
     *
     * @return Result
     */
    public static Result ok() {
        return new Result(true, StatusCode.OK, "成功");
    }

    /**
     * 返回成功消息
     *
     * @return Result
     */
    public static Result ok(Object obj) {
        return new Result(true, StatusCode.OK, "成功", obj);
    }

    /**
     * 返回成功消息
     *
     * @return Result
     */
    public static Result ok(String msg, Object obj) {
        return new Result(true, StatusCode.OK, msg, obj);
    }

    /**
     * 返回成功消息
     *
     * @return Result
     */
    public static Result ok(Object obj, Long count) {
        return new Result(true, StatusCode.OK, "成功", obj, count);
    }

    /**
     * 返回失败消息
     *
     * @return Result
     */
    public static Result error() {
        return new Result(false, StatusCode.ERROR, "失败");
    }

    /**
     * 返回失败消息
     *
     * @return Result
     */
    public static Result error(String msg) {
        return new Result(false, StatusCode.ERROR, msg);
    }

    /**
     * 返回失败消息
     *
     * @return Result
     */
    public static Result error(Integer code, String msg) {
        return new Result(false, code, msg);
    }

    /**
     * 返回登录失败的消息：用户名或密码错误
     *
     * @return Result
     */
    public static Result loginError() {
        return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
    }

    /**
     * 返回权限不足
     *
     * @return Result
     */
    public static Result accessError() {
        return new Result(false, StatusCode.ACCESSERROR, "权限不足");
    }

    /**
     * 返回远程调用失败
     *
     * @return Result
     */
    public static Result remoteError() {
        return new Result(false, StatusCode.REMOTEERROR, "远程调用失败");
    }

    /**
     * 返回重复操作
     *
     * @return Result
     */
    public static Result repError() {
        return new Result(false, StatusCode.REPERROR, "重复操作");
    }
}