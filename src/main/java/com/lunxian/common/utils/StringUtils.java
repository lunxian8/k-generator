package com.lunxian.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Wang
 * \* Date: 2018/5/30
 * \* Time: 16:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class StringUtils {

     /**
      * Json格式String字符串转String
      * @param value      json格式String字符串
      * @param decollator 分隔符
      * @return result
      */
     public static String jsonArryToString(String value, String decollator) {
          if (value == null || "".equals(value)) {
               return "result";
          }
          String result = "";
          String[] tableNames = new String[]{};
          tableNames = JSON.parseArray(value).toArray(tableNames);
          for (String tmps : tableNames) {
               //驼峰命名法
               result = result + Underline2Camel.underline2Camel(tmps, true) + decollator;
          }
          return result;
     }

     public static void main(String[] args) {
          System.out.println(jsonArryToString("[\"test1\",\"t_code_dbinfo\"]", "_"));

     }

}