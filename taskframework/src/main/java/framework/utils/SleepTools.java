package framework.utils;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhoubing
 * @Date 2021-07-05 10:28
 */
public class SleepTools {
    public static void seconds(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void ms(int ms){
        try {
            TimeUnit.MICROSECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
