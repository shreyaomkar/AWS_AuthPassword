package com.sftp.demo.SFTP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main( String[] args ) throws IOException, SftpException
	 {	
		 	Session session = null;
			Channel channel = null;
			ChannelSftp channelSftp = null;
			 JSch jsch = new JSch();
			try {
				//jsch.addIdentity("example_rsa");
				session = jsch.getSession("root", "127.0.0.1", 22);

				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				channel = session.openChannel("sftp");
				channel.connect();
				channelSftp = (ChannelSftp) channel;
				channelSftp.cd("./");
								
				String inputFilePath = "C://Program Files//JSCAPE MFT Server//users//sftpserver//root//test/Pattern.txt";
				InputStream stream=new BufferedInputStream(new FileInputStream(inputFilePath));
				channelSftp.put(stream,"Sample/Check.txt");

			}
			catch (JSchException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally 
			{
				if (channelSftp != null) 
				{
					channelSftp.disconnect();
					channelSftp.exit();
				}
				if (channel != null)
					channel.disconnect();

				if (session != null)
					session.disconnect();
			}

	 }
	
}
