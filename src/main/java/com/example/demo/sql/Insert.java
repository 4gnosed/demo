//package com.example.demo.sql;
//
//import com.example.demo.main.Animal;
//import com.example.demo.main.User;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class Insert {
//    public static void main(String[] args) throws Exception {
//        User user = new User();
//        String address = user.getAddress();
//        user.setAddress(address);
//        System.out.println(address);
//        System.out.println(user);
//
//    }
//
//    /**
//     * 删除id值
//     * @throws IOException
//     */
//    private static void insertNoId() throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\DELL\\Desktop\\上线脚本\\bankcardbininfo.sql"));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//        ArrayList<String> list = new ArrayList<>();
//        String s;
//        while ((s = bufferedReader.readLine()) != null) {
////            System.out.println(s);
//            String regex = ", 0, ";
//            String[] split = s.split(regex);
////            System.out.println(split[0]);
////            System.out.println(split[1]);
//            int i = split[0].indexOf("VALUES (");
//            String substring = split[0].substring(0, i + 8);
//            String sql = substring + " 0, " + split[1];
//            System.out.println(sql);
//            list.add(sql);
//        }
//        FileWriter fileWriter = new FileWriter(new File("C:\\Users\\DELL\\Desktop\\上线脚本\\bankcardbininfoNew.sql"));
//        for (int i = 0; i < list.size(); i++) {
//            fileWriter.write(list.get(i));
//            fileWriter.write(System.getProperty("line.separator"));//在段落后添加一个换行符
//            fileWriter.close();
//        }
//    }
//}
