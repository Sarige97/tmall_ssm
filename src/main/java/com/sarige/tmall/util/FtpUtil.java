package com.sarige.tmall.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class FtpUtil {

    FTPClient ftpClient = new FTPClient();
    FtpMessage ftpMessage;

    public FtpUtil(FtpMessage ftpMessage) throws IOException {
        this.ftpMessage = ftpMessage;
        ftpClient.connect(ftpMessage.getFTP_URL(), ftpMessage.getFTP_PORT());
        ftpClient.login(ftpMessage.getFTP_USERNAME(), ftpMessage.getFTP_PASSWORD());
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }
}
