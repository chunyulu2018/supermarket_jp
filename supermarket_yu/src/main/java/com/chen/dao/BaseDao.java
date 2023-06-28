package com.chen.dao;/*
 *

 オペレーション数据库的公共类
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静的コードブロッククラスのロード時に初期化
    static {

        Properties properties = new Properties();

        //クラスローダによる対応するリソース反射の読み取り
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        //propertiesファイルの内容を読み込む
        try {

            properties.load(is);//ロード

        } catch (IOException e) {

            e.printStackTrace();

        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    //データベース接続の取得
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //クエリー公開メソッドの作成
    public static ResultSet executeQuery(Connection conn,String sql,PreparedStatement preparedStatement,Object[] params,ResultSet resultSet) throws SQLException {

        //プリコンパイルされたsqlは後で直接実行すればよい
        preparedStatement = conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            //setObjectプレースホルダは1から始まり、数字は0から始まる
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();//ResultSet，データベースの結果セットのデータテーブル。通常、クエリー・データベースの文を実行することによって生成されます。
        return resultSet;
    }
    //添削・改査のパブリックメソッドの作成
    public static int execute(Connection conn,String sql,PreparedStatement preparedStatement,Object[] params) throws SQLException {
    	//プリコンパイルされたsqlは後で直接実行すればよい
        int updateRow;
        preparedStatement = conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
        	//setObjectプレースホルダは1から始まり、数字は0から始まる
            preparedStatement.setObject(i+1,params[i]);
        }
        updateRow = preparedStatement.executeUpdate();
        return updateRow;
    }
    //リソースの解放
    public static boolean closeResource(Connection conn,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if(conn!=null){
            try {
                conn.close();
                //GC回収
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
                //GC回収
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(resultSet!=null){
            try {
                resultSet.close();
                //GC回収
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
