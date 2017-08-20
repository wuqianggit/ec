package wq;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密文件 源代码
 * @author wq
 *
 */
public class Ec {
	/**
	 * @param sourcePath
	 * @param targetPath
	 */
	/**
	 * @param sourcePath
	 * @param targetPath
	 */
	public static void encrypt(String sourcePath,String targetPath){
		System.out.println("源路径"+sourcePath+";目标路径"+targetPath);
		
		File source=new File(sourcePath);
		if(!source.exists()){
			System.out.println(sourcePath+"，源文件不存在！");
			return;
		}
		if(source.isDirectory()){
			System.out.println(sourcePath+"，源文件不存在！");
			return;
		}
		File target=new File(targetPath);
		if(target.exists()){
			System.out.println(targetPath+"，目标文件已存在！");
			return;
		}
		encrypt(source,target);
	}
	
	public static void encrypt(File source,File target){
		BufferedInputStream bis =null;
		BufferedOutputStream bos=null;
		try {
			FileInputStream fis=new FileInputStream(source);
			bis=new BufferedInputStream(fis);
			bos=new BufferedOutputStream(new FileOutputStream(target));
			byte[] buff=new byte[1024];
			int len=0;
			while((len=bis.read(buff))!=-1){
				byte[] ebuff = buff.clone();
				//首字节异或一下
				ebuff[0]=(byte) (ebuff[0]^0xFF);
				bos.write(ebuff, 0, len);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//测试
	public static void main(String[] args) {
		if(args.length!=2){
			System.out.println("请输入源路径与目标路径");
			return;
		}
		String sourcePath=args[0];
		String targetPath=args[1];
		
		long startTime=System.currentTimeMillis();
		encrypt(sourcePath, targetPath);
		long endTime=System.currentTimeMillis();
		
		System.out.println("耗费时间:"+(endTime-startTime)/1000/60+"分钟！");
	}
}
