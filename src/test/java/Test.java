import com.sarige.tmall.util.FtpMessage;
import com.sarige.tmall.util.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext  =new ClassPathXmlApplicationContext("applicationContext.xml");
        FtpUtil ftpUtil = applicationContext.getBean(FtpUtil.class);
        FTPClient ftpClient = ftpUtil.getFtpClient();
        boolean connected = ftpClient.isConnected();
        System.out.println(connected);
    }



}
