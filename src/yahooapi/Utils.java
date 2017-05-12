/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahooapi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

/**
 *
 * @author csc190
 */
public class Utils {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/menu_db";
    static final String USER = "root";
    static final String PASS = "goodyear123!@#";

    /**
     *
     * @param obj
     * @return base64 encode + url encoded
     */
    public static String toStr(Serializable obj) {
        String sRet = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] barr = baos.toByteArray();
            sRet = Base64.getEncoder().encodeToString(barr);
            sRet = URLEncoder.encode(sRet, "UTF-8");

        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            return sRet;
        }

    }

    public static Object toObj(String str) {
        Object obj = null;
        try {
            String sDecoded = URLDecoder.decode(str, "UTF-8");
            byte[] barr = Base64.getDecoder().decode(sDecoded);
            ByteArrayInputStream bios = new ByteArrayInputStream(barr);
            ObjectInputStream ois = new ObjectInputStream(bios);
            obj = ois.readObject();
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            return obj;
        }
    }

    /**
     * Send https post request
     *
     * @param url - server name ... php
     * @param datastr - post conents
     * @return returned string
     */
    public static String httpsPost(String url, String datastr) throws Exception {
        //1. create url and request object
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-length", String.valueOf(datastr.length()));
        con.setRequestProperty("Content-Type", "application/x-www- form-urlencoded");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
        con.setDoOutput(true);
        con.setDoInput(true);

        //2. send request out
        DataOutputStream oos = new DataOutputStream(con.getOutputStream());
        oos.writeChars(datastr);
        oos.close();

        //3. collect the https response
        DataInputStream iis = new DataInputStream(con.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(iis));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = br.readLine();
        }
        String sRet = sb.toString();
        return sRet;
    }

    /**
     * Return the first string in the last record in the query
     *
     * @param qry
     * @return
     */
    public static String execQuery(String qry) {
        String res = null;
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                res = rs.getString(1);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static void execNonQuery(String qry) {

        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate(qry);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}