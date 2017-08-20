package wq;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * �����ļ� Դ����
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
		System.out.println("Դ·��"+sourcePath+";Ŀ��·��"+targetPath);
		
		File source=new File(sourcePath);
		if(!source.exists()){
			System.out.println(sourcePath+"��Դ�ļ������ڣ�");
			return;
		}
		if(source.isDirectory()){
			System.out.println(sourcePath+"��Դ�ļ������ڣ�");
			return;
		}
		File target=new File(targetPath);
		if(target.exists()){
			System.out.println(targetPath+"��Ŀ���ļ��Ѵ��ڣ�");
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
				//���ֽ����һ��
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
	
	//����
	public static void main(String[] args) {
		if(args.length!=2){
			System.out.println("������Դ·����Ŀ��·��");
			return;
		}
		String sourcePath=args[0];
		String targetPath=args[1];
		
		long startTime=System.currentTimeMillis();
		encrypt(sourcePath, targetPath);
		long endTime=System.currentTimeMillis();
		
		System.out.println("�ķ�ʱ��:"+(endTime-startTime)/1000/60+"���ӣ�");
	}
}
