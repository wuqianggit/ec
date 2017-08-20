package wq;

import java.io.File;

/**
 * ���� �ļ���
 * @author Administrator
 *
 */
public class Ecd {
	/**
	 * ����һ���ļ��У������ļ����������е��ļ��������������һ���ļ���
	 * @param source ԴĿ¼
	 * @param target Ŀ��Ŀ¼
	 * @param subFix ��׺����
	 */
	public void ecripteD(File source,File target,String subFix) throws Exception{
		if(!source.isDirectory()){
			throw new RuntimeException("ԴĿ���ļ�"+source.getAbsolutePath()+"����Ŀ¼!");
		}
		if(!target.isDirectory()){
			throw new RuntimeException("ԴĿ���ļ�"+source.getAbsolutePath()+"����Ŀ¼!");
		}
		//����ԴĿ¼�ļ�
		File[] listFile=source.listFiles();
		System.out.println("Դ�ļ� һ�� "+listFile.length+"���ļ�:");
		for(int i=0;i<listFile.length;i++){
			System.out.println("���ڴ��� �� "+i+"��;");
			File file=listFile[i];
			//Ŀ���ļ�����  = Դ�ļ����� +��׺�� 
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
				throw new RuntimeException("������ ԴĿ¼·��  Ŀ��Ŀ¼·�� Ŀ���׺����");
			}
			String sourcePath=args[0];
			String targetPath=args[1];
			String subFix=args[2];
			
			long startTime=System.currentTimeMillis();
			Ecd ecd=new Ecd();			
			ecd.ecripteD(sourcePath, targetPath, subFix);
			long endTime=System.currentTimeMillis();
			System.out.println("�ķ�ʱ��:"+(endTime-startTime)/1000/60+"���ӣ�");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
