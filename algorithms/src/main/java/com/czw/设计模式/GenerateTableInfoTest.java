package com.czw.设计模式;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 16:50
 * @UpdeteTime: 2020-01-16 16:50
 * @Description:
 */
public class GenerateTableInfoTest {
	private static String prefixPath= Objects.requireNonNull(GenerateTableInfoTest.class.getClassLoader().getResource("")).getPath();
	private static Statement statement=null;
	public static void generator(){
		Connection conn=null;
		FileWriter fw=null;
		InputStream is=null;
	//	读取jdbc配置文件
		try {
			is=new FileInputStream(prefixPath+"jdbc.properties");
			Properties jdbcProperties=new Properties();
			jdbcProperties.load(is);
			is.close();
		//	连接数据路
			String driverClassName=jdbcProperties.getProperty("driverClassName");
			String jdbcUrl=jdbcProperties.getProperty("jdbc_url");
			String jdbcUsername=jdbcProperties.getProperty("jdbc_username");
			String jdbcPassword=jdbcProperties.getProperty("jdbc_password");
			Class.forName(driverClassName);
			conn= DriverManager.getConnection(jdbcUrl + "&user=" + jdbcUsername + "&password=" + jdbcPassword);
			statement=conn.createStatement();
			DatabaseMetaData  metaData=conn.getMetaData();
			Properties tableName=new Properties();
			//获取表名
			is=new FileInputStream(prefixPath+"tablename.properties");
			tableName.load(is);
			is.close();
			List<String> tableNameList=new ArrayList<>();
			Set<Object> tableKey=tableName.keySet();
			for (Object key:tableKey){
				if ("moduleName".equals(key))
					continue;
				tableNameList.add(tableName.getProperty(key.toString()));
			}
		//	创建表内字段对比文件
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<tableNameList.size();i++){
				sb.setLength(0);
				String name=tableNameList.get(i);
				File file=new File(prefixPath+"tables/"+name+".properties");
				File parentFile=file.getParentFile();
				if (!parentFile.exists())
					parentFile.mkdirs();
				file.createNewFile();
				fw=new FileWriter(file);
				ResultSet primaryKeySet=metaData.getPrimaryKeys(null,"%",name);
				String realPrimaryKey=null;
				if (primaryKeySet.next())
					realPrimaryKey=primaryKeySet.getString("COLUMN_NAME");
				ResultSet columnSet=metaData.getColumns(null,"%",name,"%");
				String aliasPrimaryKey=realPrimaryKey;
				while (columnSet.next()){
					String columnName=columnSet.getString("COLUMN_NAME");
					if ("create_datetime".equals(columnName)||"update_datetime".equals(columnName)){
						continue;
					}
					sb.append(columnName).append("=").append(columnName).append("\r\n");
				}
				sb.insert(0, "#Primary key mapping:The real table name=The real primary key\r\n"+tableName+"="+aliasPrimaryKey+"\r\n");
				fw.write(sb.toString());
				fw.flush();
				fw.close();
			}
			System.out.println("生成结束");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		generator();
	}
}
