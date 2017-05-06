package utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.SequenceInputStream;
import java.net.Socket;
import java.util.Arrays;

import content.StatueContent;
 
public class FileUploader {
 
    
	public FileUploader(String filePath) {
		// TODO Auto-generated constructor stub
		try {
            Socket s = new Socket(StatueContent.server_ip,StatueContent.server_port);
            File file = new File(filePath);
            FileInputStream fs = new FileInputStream(file);
            //定义一个256字节的区域来保存文件信息。
            byte[] b = file.getName().getBytes();
            byte[] info = Arrays.copyOf(b,256);         
            ByteArrayInputStream bais = new ByteArrayInputStream(info);
            //合并流
            SequenceInputStream sis = new SequenceInputStream(bais,fs);
            BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = sis.read(buf))!=-1){
                bos.write(buf,0,len);
            }
            bos.close();
            sis.close();
            fs.close();
            bais.close();
            s.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        } 
    
	}
}

