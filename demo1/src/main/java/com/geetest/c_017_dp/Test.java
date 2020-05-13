package com.geetest.c_017_dp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-05-09 10:59
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String path = "C:/Program Files (x86)/Tencent/TIM/Bin/QQScLauncher.exe";

        startProgram(path);
    }

    public static void startProgram(String programPath) throws IOException {
        System.out.println("启动应用程序：" + programPath);

        try {
            String programName = programPath.substring(programPath.lastIndexOf("/") + 1, programPath.lastIndexOf("."));
            List<String> list = new ArrayList<String>();
            list.add("cmd.exe");
            list.add("/c");
            list.add("start");
            list.add("\"" + programName + "\"");
            list.add("\"" + programPath + "\"");
            ProcessBuilder pBuilder = new ProcessBuilder(list);
            pBuilder.start();
            // Desktop.getDesktop().open(new File(programPath));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("应用程序：" + programPath + "不存在！");
        }

    }
}
