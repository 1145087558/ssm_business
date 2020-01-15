package com.qst.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyUtil {

public static void copyFile(File src , File target){
		
		try {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(target);
			byte[] arr = new byte[1024];
			int len = 1;
			while((len=in.read(arr))!=-1){
				out.write(arr, 0, len);
			}
			in.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
