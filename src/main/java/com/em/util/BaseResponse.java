package com.em.util;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//统一的response封装
@ApiModel(value = "统一返回封装")
public class BaseResponse<T> {
    @ApiModelProperty(value = "返回码：正确0, 错误-1，其他错误为自定义码")
    private int responseCode;
    @ApiModelProperty(value = "返回消息")
    private String responseMsg;
    @ApiModelProperty(value = "返回具体内容")
    private T data;
    public BaseResponse(int responseCode, String responseMsg, T data) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.data = data;
    }
    //请求成功(无参)
    public static ResponseEntity generateOKResponseEntity() {
        Map<String, Object> obj = new HashMap<String, Object>();
        return ResponseEntity.ok().body(new BaseResponse<>(0, "Success", obj));
    }
    //请求成功(带成功信息和数据)
    public static ResponseEntity generateOKResponseEntity(String responseMsg,Object object)
    {
//        Map<String, Object> obj = new HashMap<String, Object>();
        return ResponseEntity.ok().body(new BaseResponse<>(0, responseMsg, object));
    }
    //请求成功(带数据)
    public static ResponseEntity generateOKResponseEntity(Object object) {
        return ResponseEntity.ok().body(new BaseResponse<>(0, "Success", object));
    }
    //请求成功(List专用)
    public static ResponseEntity generateOKListResponseEntity(List list) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        if (list instanceof Page) {
            Page page = (Page) list;
            data.put("total", page.getTotal());
            data.put("page", page.getPageNum());
            data.put("limit", page.getPageSize());
            data.put("pages", page.getPages());
        } else {
            data.put("total", list.size());
            data.put("page", 1);
            data.put("limit", list.size());
            data.put("pages", 1);
        }
        return ResponseEntity.ok().body(new BaseResponse<>(0, "Success", data));
    }
    public static ResponseEntity generateOKListResponseEntity(List list, List pagedList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        if (pagedList instanceof Page) {
            Page page = (Page) pagedList;
            data.put("total", page.getTotal());
            data.put("page", page.getPageNum());
            data.put("limit", page.getPageSize());
            data.put("pages", page.getPages());
        } else {
            data.put("total", pagedList.size());
            data.put("page", 1);
            data.put("limit", pagedList.size());
            data.put("pages", 1);
        }
        return ResponseEntity.ok().body(new BaseResponse<>(0, "Success", data));
    }
    //请求错误
    public static ResponseEntity generateBadResponseEntity() {
        Map<String, Object> obj = new HashMap<String, Object>();
        return ResponseEntity.badRequest().body(
                new BaseResponse<>(-1, "Fail", obj));
    }
    public static ResponseEntity generateBadResponseEntity(String message, Object object) {
        return ResponseEntity.badRequest().body(
                new BaseResponse<>(-1, message, object));
    }
    public static ResponseEntity generateBadResponseEntity(int code, String message, Object
            object) {
        return ResponseEntity.badRequest().body(
                new BaseResponse<>(code, message, object));
    }
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseMsg() {
        return responseMsg;
    }
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
    public T getData() {
        return data;
    }
    public static ResponseEntity badArgument() {
        return generateBadResponseEntity(401, "参数不对","");
    }
    public static ResponseEntity badArgumentValue() {
//return fail(402, "参数值不对");
        return generateBadResponseEntity(402, "参数值不对","");
    }
    public static ResponseEntity unlogin() {
// return fail(501, "请登录");
        return generateBadResponseEntity(501, "请登录","");
    }
    public static ResponseEntity serious() {
// return fail(502, "系统内部错误");
        return generateBadResponseEntity(502, "系统内部错误","");
    }
    public static ResponseEntity unsupport() {
//return fail(503, "业务不支持");
        return generateBadResponseEntity(503, "业务不支持","");
    }
    public static ResponseEntity updatedDateExpired() {
//return fail(504, "更新数据已经失效");
        return generateBadResponseEntity(504, "更新数据已经失效","");
    }
    public static ResponseEntity updatedDataFailed() {
//return fail(505, "更新数据失败");
        return generateBadResponseEntity(505, "更新数据失败","");
    }
    public static ResponseEntity unauthz() {
// return fail(506, "无操作权限");
        return generateBadResponseEntity(506, "无操作权限","");
    }
}
