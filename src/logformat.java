

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class logformat {
	public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File f = new File(filePath);
                File[] fileList = f.listFiles();
                for(int x=0;x<fileList.length;x++){
	                File file=fileList[x];
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    File newFile = new File("/Users/unico/Desktop/nginxlog72/2016/"+x+".txt");
	                    newFile.createNewFile();
	                    FileWriter out=new FileWriter(newFile);
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	String key="keyword";
	                    	int a = lineTxt.indexOf(key);
	                    	if(a>=0){
	                    		String url=null;
	                    		String allParam=null;
	                        	String ip=null;
	                        	String date=null;
	                        	String formatTxt=null;
	                    		Pattern p1 = Pattern.compile("((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}))");
	                        	Matcher m1 = p1.matcher(lineTxt);
	                 	       while (m1.find()) {
	                 	           ip=m1.group(1)+"  ";
	                 	       }
	//                 	      Pattern p2 = Pattern.compile("^GET(.*?)HTTP$");
	//                      	Matcher m2 = p2.matcher(lineTxt);
	//               	       while (m2.find()) {
	//               	           System.out.println(m2.group(1));
	//               	       }
	                    		int i=lineTxt.indexOf("["); 
	                    		int j=lineTxt.indexOf(" +0800]"); 
	                    		date = lineTxt.substring(i+1,j)+"  ";
	//                    		int i1=lineTxt.indexOf("\"GET"); 
	//                    		int j1=lineTxt.indexOf("HTTP/1.1"); 
	//                    		url = lineTxt.substring(i1,j1+10);
	//                    		System.out.println(url);
	                    		String u[] = lineTxt.split("GET|HTTP"); 
	                    		url = "\"GET"+u[1]+" HTTP/1.1\"";
	//                    		System.out.println(lineTxt);
	//                            System.out.println(date);
	//                            System.out.println(url);
	                    		String v[] =lineTxt.split("[?]| HTTP");
	                    		allParam = v[1];
	                    		String[] w = v[1].split("[&]");
	                    		String[] z =null;
	                    		String keyWord=null;
	                    		for(int y=0;y<w.length;y++){
	                    			if(w[y].indexOf("keyword")>=0){
	                    				String m=w[y]+" ";
	                    				z = m.split("=");
	                    				keyWord = URLDecoder.decode(z[1], "utf-8");
	                    				break;
	                    			}
	                    			
	                    		}
	                    		
	//                    		Pattern p2 = Pattern.compile("([u4e00-u9fa5]+)");
	//                        	Matcher m2 = p2.matcher("sdaf呵呵dfer1554");
	//                 	       while (m2.find()) {
	//                 	    	  System.out.println(m2.group(0));
	//                 	       }
//	                    		StringBuffer sb = new StringBuffer();
//	                            for (int m = 0; m < keyWord.length(); m++) {
//	                                if ((keyWord.charAt(m)+"").getBytes().length>1) {
//	                                    sb.append(keyWord.charAt(m));
//	                                }
//	                            }
	                 	      formatTxt = ip+date+url+"  "+keyWord;
	                            System.out.println(formatTxt);
	                            out.write(formatTxt);
	                            out.write("\r\n");
	                    	}
	                    }
	                    read.close();
	                    out.close();//强制刷新数据缓冲区(有些Writer会在内部建立缓冲区，提高数据写入效率)
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	                
          }          
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
	public static void main(String argv[]){
        String filePath = "/Users/unico/Downloads/log日志/nginxlog72/opt/app/nginx/logs/2016/";
//      "res/";
        readTxtFile(filePath);
        
    }
}
