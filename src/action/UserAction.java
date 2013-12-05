package action;

import java.io.*;
import java.util.*;

import data.Mail;
import data.User;

/**
 * 用户链表处理类
 * @author spacelis
 *
 */
public class UserAction {
	
	/**
	 * 保存用户链表到相应文件（覆盖方式）
	 * @param mailList 用户链表
	 * @throws IOException
	 */
	public static void save(List<User> userList) throws IOException{
		File mfile = new File("user.dat");
		if(!mfile.exists()) mfile.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mfile));
		for(Iterator<User> it=userList.iterator();it.hasNext();)
			oos.writeObject(it.next());
		oos.flush();
		oos.close();
	}
	
	public static String regin(List<User> userList) throws IOException{
		File mfile = new File("user.dat");	
		if(!mfile.exists()) return "无记录";
		
		User user = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mfile));		
		try{
			while((user=(User)ois.readObject())!=null){
				userList.add(user);
			}
		}catch(EOFException e){
			System.out.println("文件读取完毕");
		} catch (ClassNotFoundException e) {
			System.out.println("格式错误");
			e.printStackTrace();
		}
		ois.close();
		return "查询完毕";
	}
	
}
