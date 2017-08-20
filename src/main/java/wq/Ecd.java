package wq;

import java.io.File;

/**
 * 加密 文件夹
 * @author Administrator
 *
 */
public class Ecd {
	/**
	 * 输入一个文件夹，加密文件夹里面所有的文件，并输出到另外一个文件夹
	 * @param source 源目录
	 * @param target 目标目录
	 * @param subFix 后缀名称
	 */
	public void ecripteD(File source,File target,String subFix) throws Exception{
		if(!source.isDirectory()){
			throw new RuntimeException("源目标文件"+source.getAbsolutePath()+"不是目录!");
		}
		if(!target.isDirectory()){
			throw new RuntimeException("源目标文件"+source.getAbsolutePath()+"不是目录!");
		}
		//遍历源目录文件
		File[] listFile=source.listFiles();
		System.out.println("源文件 一共 "+listFile.length+"个文件:");
		for(int i=0;i<listFile.length;i++){
			System.out.println("正在处理 第 "+i+"个;");
			File file=listFile[i];
			//目标文件名称  = 源文件名称 +后缀名 
			String sourceName=file.getName();
			sourceName=sourceName.substring(0, sourceName.lastIndexOf("."));
			
			String targetName=sourceName+"."+subFix;
			
			File tFile=new File(target,targetName);
			
			Ec.encrypt(file, tFile);
		}
	}
	
	public void ecripteD(String source,String target,String subFix) throws Exception{
		File sourceFile=new File(source);
		
		File targetFile=new File(target);
		this.ecripteD(sourceFile, targetFile, subFix);
	}
	
	public static void main(String[] args) {
		try{
			if(args.length!=3){
				throw new RuntimeException("请输入 源目录路径  目标目录路径 目标后缀名称");
			}
			String sourcePath=args[0];
			String targetPath=args[1];
			String subFix=args[2];
			
			long startTime=System.currentTimeMillis();
			Ecd ecd=new Ecd();			
			ecd.ecripteD(sourcePath, targetPath, subFix);
			long endTime=System.currentTimeMillis();
			System.out.println("耗费时间:"+(endTime-startTime)/1000/60+"分钟！");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
