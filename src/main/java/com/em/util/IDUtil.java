package com.em.util;
import java.util.UUID;

/**
 * @ClassName: IDUtil
 * @Description: ID生成类，用于获取主键ID
 */
public class IDUtil {
    /**
     * @Title: getID
     * @Description: 获取随机ID（32位）
     * @return String ID（32位）
     */
    public static String getID() {
    // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
    // 得到对象产生的ID
        String a = uuid.toString();
    // 转换为大写
     a = a.toUpperCase();
        //这里8位数就够了
//        a = a.substring(23);
    // 替换 -
        a = a.replaceAll("-", "");
        return a;
    }
}