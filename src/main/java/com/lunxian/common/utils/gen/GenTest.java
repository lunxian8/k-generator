package com.lunxian.common.utils.gen;

import com.lunxian.common.utils.DateUtils;
import com.lunxian.common.baseDb.entity.ColumnEntity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Wang
 * \* Date: 2018/5/31
 * \* Time: 13:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class GenTest {


     public static void main(String[] args) {
          String ent="package ${package}.${moduleName}.entity;\n" +
              "\n" +
              "import com.baomidou.mybatisplus.annotations.TableId;\n" +
              "import com.baomidou.mybatisplus.annotations.TableName;\n" +
              "import com.baomidou.mybatisplus.annotations.TableField;\n" +
              "import com.baomidou.mybatisplus.enums.IdType;\n" +
              "import com.baomidou.mybatisplus.activerecord.Model;\n" +
              "\n" +
              "#if(${hasBigDecimal})\n" +
              "import java.math.BigDecimal;\n" +
              "#end\n" +
              "import java.io.Serializable;\n" +
              "import java.util.Date;\n" +
              "\n" +
              "/**\n" +
              " * ${comments}\n" +
              " *\n" +
              " * @author ${author}\n" +
              " * @email ${email}\n" +
              " * @date ${datetime}\n" +
              " */\n" +
              "@TableName(\"${tableName}\")\n" +
              "public class ${className}Entity extends Model<${className}Entity>  {\n" +
              "     private static final long serialVersionUID = 1L;\n" +
              "\n" +
              "#foreach ($column in $columns)\n" +
              "\t/**\n" +
              "\t * $column.comments\n" +
              "\t */\n" +
              "\t#if($column.columnName == $pk.columnName)\n" +
              "@TableId(value=\"${column.columnName}\", type= IdType.AUTO)\n" +
              "\t#else\n" +
              "@TableField(\"${column.columnName}\")\n" +
              "\t#end\n" +
              "private $column.attrType $column.attrname;\n" +
              "#end\n" +
              "\n" +
              "#foreach ($column in $columns)\n" +
              "\n" +
              "\tpublic void set${column.attrName}($column.attrType $column.attrname) {\n" +
              "\t\tthis.$column.attrname = $column.attrname;\n" +
              "\t}\n" +
              "\n" +
              "\tpublic $column.attrType get${column.attrName}() {\n" +
              "\t\treturn $column.attrname;\n" +
              "\t}\n" +
              "#end\n" +
              "\n" +
              "     @Override\n" +
              "     protected Serializable pkVal() {\n" +
              "              return this.id;\n" +
              "     }\n" +
              "\n" +
              "     @Override\n" +
              "     public String toString() {\n" +
              "      return \"${tableName}{\" +\n" +
              "\t #foreach($column in $columns)\n" +
              "\t\t#if($!{velocityCount}==1)\n" +
              " \"${column.attrname}=\" + ${column.attrname} +\n" +
              "\t\t#else\n" +
              "\", ${column.attrname}=\" + ${column.attrname} +\n" +
              "\t    #end\n" +
              "\t #end\n" +
              "     \"}\";\n" +
              "     }\n" +
              "}\n";

          ColumnEntity columnEntity=new ColumnEntity();
          columnEntity.setColumnName("id");
          columnEntity.setDataType("varchar");
          columnEntity.setComments("主键id");
          columnEntity.setAttrname("id");
          columnEntity.setAttrName("Id");
          columnEntity.setAttrType("String");
          columnEntity.setExtra("");
          List<ColumnEntity> getColumns=new ArrayList<>();
          getColumns.add(columnEntity);


          Map<String, Object> map = new HashMap<>();
          map.put("tableName", "test1");
          map.put("comments", "测试表");
          map.put("pk", "");
          map.put("className", "Test1");
          map.put("classname", "test1");
          map.put("pathName", "test1");
          map.put("columns", getColumns);
          map.put("hasBigDecimal", true);
          map.put("mainPath", "com.lunxian");
          map.put("package", "com.lunxian.modules");
          map.put("moduleName", "Test1");
          map.put("author","wangxuek");
          map.put("email", "ss2@qq.com");
          map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
          Properties prop = new Properties();
          prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
          Velocity.init(prop);
          VelocityContext context = new VelocityContext(map);
          StringWriter sw = new StringWriter();
           Velocity.evaluate(context,sw,"标签",ent);

           System.out.println(sw.toString());
     }
}