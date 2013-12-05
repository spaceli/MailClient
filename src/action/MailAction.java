package action;
import dialog.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

import sun.misc.BASE64Encoder;
import data.*;

/**
 * 邮件处理类
 * @author spacelis
 *
 */
public class MailAction {
	
	/**
	 * 邮件发送方法
	 * @param user 用户信息
	 * @param mail 邮件实例
	 * @return 返回邮件发送状态
	 * @throws IOException
	 */
	public static String send(User user,Mail mail) throws IOException{
	
		String from = mail.getFrom();
		String to = mail.getTo();
		String cc = mail.getCc();
		String bcc = mail.getBcc();
		
		String mailContent = new MailEncoder().transformMIME(mail,1);
		
		BASE64Encoder encoder = new BASE64Encoder();//BASE64转换器		
		String encodedUser = encoder.encode(user.getUsername().getBytes());
		String encodedPswd = encoder.encode(user.getPassword().getBytes());		
		String hostName = InetAddress.getLocalHost().getHostName();
		
		Socket s = new Socket(user.getSmtpServer(),25);
		
		BufferedReader inFromServer = new BufferedReader(//服务器应答流
				new InputStreamReader(s.getInputStream()));		
		PrintWriter outToServer = new PrintWriter(//客户端访问流
				s.getOutputStream(),true);
		
		String response = inFromServer.readLine();		
		System.out.println(response);
		if(response.indexOf("220")==-1) return "连接失败";
					
		response = exchange("EHLO "+hostName, inFromServer, outToServer);
		System.out.println(response);
		if(response.indexOf("250")==-1) return "无法连接服务器";
		
		response = exchange("AUTH LOGIN", inFromServer, outToServer);
		while(response.indexOf("250")!=-1){
			System.out.println(response);
			response = inFromServer.readLine();
		}
		System.out.println(response);
		if(response.indexOf("334")==-1) return "无法验证";			
			
		response = exchange(encodedUser, inFromServer, outToServer);
		System.out.println("3.MailServer:"+response+"用户名");   //Password:
		if(response.indexOf("334")==-1) return "用户名非法";
		
		response = exchange(encodedPswd, inFromServer, outToServer);
		System.out.println("4.MailServer:"+response+"密码");
		if(response.indexOf("235")==-1) return "密码错误";
		
		response = exchange("MAIL FROM:<"+from+"> BODY=8BITMIME", inFromServer, outToServer);
		System.out.println("5.MailServer:"+response);
		if(response.indexOf("250")==-1) return "发件人非法";

		
		System.out.println("6.Client:"+"RCPT TO:"+to);	
		response = exchange("RCPT TO:<"+to+">", inFromServer, outToServer);
		System.out.println("6.MailServer:"+response);
		if(response.indexOf("250")==-1) return "收件人非法";
		
		if(!"".equals(cc)){
			response = exchange("RCPT TO:<"+cc+">", inFromServer, outToServer);
			System.out.println("6.MailServer:"+response);
			if(response.indexOf("250")==-1) return "抄送人非法";
		}
		if(!"".equals(bcc)){
			response = exchange("RCPT TO:<"+bcc+">", inFromServer, outToServer);
			System.out.println("6.MailServer:"+response);
			if(response.indexOf("250")==-1) return "密送人非法";
		}
		
		response = exchange("DATA", inFromServer, outToServer);	
		System.out.println("7.MailServer:"+response);
		if(response.indexOf("354")==-1) return "无法发送邮件";

		outToServer.println(mailContent);		
		response = exchange(".", inFromServer, outToServer);
		System.out.println("MailServer:"+response);
		if(response.indexOf("250")==-1) return "发送失败";
		System.out.println("8.MailServer:"+response);
		
		response = exchange("QUIT", inFromServer, outToServer);
		System.out.println("8.MailServer:"+inFromServer.readLine());
		s.close();
		return "发送成功";
	}
	
	/**
	 * 从服务器下载邮件，保存到文件。不删除服务器上的内容。
	 * @param user 用户信息
	 * @param mailList 下载邮件列表
	 * @return 返回下载状态
	 * @throws IOException
	 */
	public static String receive(User user,List<Mail> mailList)throws IOException{
		int mCount = 0;
		String response = "";
		
		Socket s = new Socket(user.getPopServer(),110);
		
		BufferedReader inFromServer = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
		PrintWriter outToServer = new PrintWriter(
				new OutputStreamWriter(s.getOutputStream()));
		response = inFromServer.readLine();
		System.out.println(response);
		if(response.indexOf("+OK")==-1) return "无法连接服务器";
		
		response = exchange("user "+user.getUsername(), inFromServer, outToServer);
		System.out.println(response);
		if(response.indexOf("+OK")==-1) return "用户名不存在";
		
		
		response = exchange("pass "+user.getPassword(), inFromServer, outToServer);
		System.out.println(response);
		if(response.indexOf("+OK")==-1) return "密码错误";
		
		
		response = exchange("list", inFromServer, outToServer);
		System.out.println(response);
		if(response.indexOf("+OK")==-1) return "无法下载邮件";		
		for(mCount=0;!".".equals(response=inFromServer.readLine());mCount++){
			System.out.println(response);
		}
		MailDecoder mDecoder = new MailDecoder();
		List<Mail> keptList = new ArrayList<Mail>();
		
		regain(keptList, "user/"+user.getUsername()+"/recvBox.dat");
		int downCount = 0;
		for(int i=1;i<=mCount;i++){
			StringBuffer original = new StringBuffer();
			response = exchange("retr "+i, inFromServer, outToServer);
			if(response.indexOf("+OK")==-1) continue;
			response = inFromServer.readLine();
			while(!".".equals(response)){
				original.append(response+"\n");
				response = inFromServer.readLine();
			}
			Mail aMail = new Mail();
			
			MailClient.stateTextArea.setText("服务器共有"+mCount+
					"封邮件，\n正在下载第"+i+"封邮件...");		
			
			mDecoder.packMail(aMail, original.toString());
			Iterator<Mail> it = keptList.iterator();
			int j=0;
			for(;it.hasNext();j++){
				if(aMail.equals(it.next())) break;
			}
			if(j==keptList.size()){//文件中无该记录
				save(aMail,"user/"+user.getUsername()+"/recvBox.dat");
				mailList.add(aMail);
				downCount++;
			}
			
		}
		response = exchange("quit", inFromServer, outToServer);
		System.out.println(response);
		s.close();
		
		return "成功下载"+downCount+"封新邮件";
	}
	
	/**
	 * 将单个邮件保存到文件
	 * @param mail 邮件实例
	 * @param filename 文件名
	 * @throws IOException
	 */
	public static void save(Mail mail,String filename) throws IOException{
		if(mail==null) return;
		File mfile = new File(filename);
		if(!mfile.exists()){
			mfile.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mfile));
			oos.writeObject(mail);
			oos.flush();
			oos.close();
		}else{
			ObjectWriter oos = new ObjectWriter(new FileOutputStream(mfile,true));
			oos.writeObject(mail);
			oos.flush();
			oos.close();
		}		
	}
	
	/**
	 * 保存邮件链表到相应文件
	 * @param mailList 邮件链表
	 * @param filename 文件名
	 * @throws IOException
	 */
	public static void save(List<Mail> mailList,String filename) throws IOException{
		if(!(mailList.size()>0)) return ;
		File mfile = new File(filename);
		if(!mfile.exists()) mfile.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mfile));
		for(Iterator<Mail> it=mailList.iterator();it.hasNext();)
			oos.writeObject(it.next());
		oos.flush();
		oos.close();
	}
	
	/**
	 * 从文件查询邮件列表
	 * @param mailList 邮件列表
	 * @param filename 文件名
	 * @return 返回查询状态
	 * @throws IOException
	 */
	public static String regain(List<Mail> mailList,String filename) throws IOException{
		File mfile = new File(filename);	
		if(!mfile.exists()) return "无记录";

		Mail aMail = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mfile));		
		try{
			while((aMail=(Mail)ois.readObject())!=null){
				mailList.add(0, aMail);
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
	

	/**
	 * 与服务器交换信息
	 * @param toServer 发送给服务器的信息串
	 * @param in 输入流
	 * @param out 输出流
	 * @return 返回服务器回应信息
	 */
	public static String exchange(String toServer,BufferedReader in,PrintWriter out){
		out.println(toServer);
		out.flush();
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
/**
 * ObjectOutputStream的子类，重写其writeStreamHeader()方法
 * @author spacelis
 *
 */
class ObjectWriter extends ObjectOutputStream{
	public ObjectWriter(OutputStream out) throws IOException {
		super(out);
	}
	/**
	 * 以追加方式写入对象不需要重新写StreamHeader，故重写此方法
	 */
	@Override
	protected void writeStreamHeader() throws IOException {
		super.reset();
	}		
}

