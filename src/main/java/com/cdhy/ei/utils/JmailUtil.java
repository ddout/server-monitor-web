package com.cdhy.ei.utils;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdhy.commons.utils.SysConfigUtil;

import sun.misc.BASE64Encoder;

public class JmailUtil {
    private static final Logger logger = LoggerFactory.getLogger(JmailUtil.class);

    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    private static final String username; // smtp认证用户名和密码
    private static final String password;
    private static final String SERVICE_SMTP; // 设置SMTP主机
    private Authenticator auth = null;
    private Multipart mp = new MimeMultipart("related"); // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    static {
	username = SysConfigUtil.getInstance().getProperites("email_username");
	password = SysConfigUtil.getInstance().getProperites("email_password");
	SERVICE_SMTP = SysConfigUtil.getInstance().getProperites("service_smtp");
    }

    public JmailUtil() {
	try {
	    if (props == null) {
		props = System.getProperties(); // 获得系统属性对象
	    }
	    props.put("mail.smtp.host", SERVICE_SMTP);
	    auth = new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(username, password);
		}
	    };
	    session = Session.getDefaultInstance(props, auth); // 获得邮件会话对象
	    // session.setDebug(true); //true时 打开debug
	    mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
	} catch (Exception e) {
	    new RuntimeException("邮件初始化失败！", e);
	}
    }

    /**
     * 设置邮件标题
     * 
     * @param sub
     */
    public void setSubject(String sub) {
	try {
	    mimeMsg.setSubject(sub, "GBK");
	} catch (Exception e) {
	    new RuntimeException("邮件标题设置失败！", e);
	}
    }

    /**
     * String image; 多图片cid：image + picPaths[index]
     * 
     * @param text
     * @param type
     * @param picPaths
     */
    public void setContent(String text, String type) {
	try {
	    BodyPart bp = new MimeBodyPart();
	    bp.setContent(text, type);
	    mp.addBodyPart(bp);

	    // TODO 发送指定本地图片
	    // bp = new MimeBodyPart();
	    // DataSource fds = new FileDataSource(picPath);
	    // bp.setDataHandler(new DataHandler(fds));
	    // bp.setHeader("Content-ID", "<image>");
	    // mp.addBodyPart(bp);

	    // if (picPaths.length > 0) {
	    // for (int index = 0; index < picPaths.length; index++) {
	    // bp = new MimeBodyPart();
	    // DataSource fds = new FileDataSource(picPaths[index]);
	    // bp.setDataHandler(new DataHandler(fds));
	    // bp.setHeader("Content-ID", "<image" + index + ">");
	    // mp.addBodyPart(bp);
	    // }
	    // }

	} catch (Exception e) {
	    new RuntimeException("邮件内容设置失败！", e);
	}
    }

    /**
     * 设置邮件内容 文本内容
     * 
     * @param text
     */
    public void setText(String text) {
	try {
	    BodyPart bp = new MimeBodyPart();
	    bp.setText(text);
	    mp.addBodyPart(bp);
	} catch (Exception e) {
	    new RuntimeException("邮件内容设置失败！", e);
	}
    }

    /**
     * 设置附件
     * 
     * @param AttachURL文件路径
     */
    public void addAttach(String AttachURL) {
	try {
	    // 添加附件
	    BodyPart bp = new MimeBodyPart();
	    BASE64Encoder enc = new BASE64Encoder();// 解决附件名乱码问题
	    FileDataSource fileds = new FileDataSource(AttachURL);
	    bp.setDataHandler(new DataHandler(fileds));
	    bp.setFileName("=?GBK?B?" + enc.encode((fileds.getName()).getBytes()) + "?=");
	    mp.addBodyPart(bp);
	} catch (Exception e) {
	    new RuntimeException("邮件附件粘贴失败！", e);
	}
    }

    /**
     * 设置附件
     * 
     * @param AttachURL文件路径
     */
    public void addAttach(File attachFile) {
	try {
	    // 添加附件
	    BodyPart bp = new MimeBodyPart();
	    BASE64Encoder enc = new BASE64Encoder();// 解决附件名乱码问题
	    FileDataSource fileds = new FileDataSource(attachFile);
	    bp.setDataHandler(new DataHandler(fileds));
	    bp.setFileName("=?GBK?B?" + enc.encode((fileds.getName()).getBytes()) + "?=");
	    mp.addBodyPart(bp);
	} catch (Exception e) {
	    new RuntimeException("邮件附件粘贴失败！", e);
	}
    }

    /**
     * 设置附件
     * 
     * @param AttachURL文件路径
     */
    public void addAttach(byte[] data) {
	try {
	    // 添加附件
	    BodyPart mdp = new MimeBodyPart();
	    DataSource obj = new ByteArrayDataSource(data, "application/octet-stream");
	    DataHandler dh = new DataHandler(obj);
	    mdp.setDataHandler(dh);
	    String fileName = System.currentTimeMillis() + ".pdf";
	    mdp.setFileName(MimeUtility.encodeWord(fileName, "GBK", "B"));
	    mdp.setHeader("content-id", fileName);
	    mp.addBodyPart(mdp);
	} catch (Exception e) {
	    new RuntimeException("邮件附件粘贴失败！", e);
	}
    }

    public void clearAttach() {
	try {
	    int n = mp.getCount();
	    System.out.println();
	    for (int i = 0; i <= n - 1; i++) {
		mp.removeBodyPart(0);
	    }
	    mimeMsg.setContent(mp);
	    mimeMsg.saveChanges();
	} catch (Exception e) {
	    new RuntimeException("清除附件失败！", e);
	}
    }

    /**
     * 设置发信人
     * 
     * @param AttachURL文件路径
     */
    public void setFrom(String address) {
	try {
	    mimeMsg.setFrom(new InternetAddress(address)); // 设置发信人
	} catch (Exception e) {
	    new RuntimeException("邮件发件人地址设置失败！", e);
	}
    }

    /**
     * 设置发信人
     * 
     * @param AttachURL文件路径
     */
    public void setFrom(String nick, String address) {
	try {
	    mimeMsg.setFrom(new InternetAddress(
		    javax.mail.internet.MimeUtility.encodeText(nick, "UTF-8", "B") + " <" + address + ">"));
	} catch (Exception e) {
	    new RuntimeException("邮件发件人地址设置失败！", e);
	}
    }

    /**
     * 设置收信人
     * 
     * @param AttachURL文件路径
     */
    public void setRecipients(String address) {
	try {
	    mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address)); // 收信人
	} catch (Exception e) {
	    new RuntimeException("邮件收件人地址设置失败！", e);
	}
    }

    /**
     * 设置多个收信人 应该是抄送 The "TO" (primary) recipients. The "CC" (carbon copy)
     * recipients. The "BCC" (blind carbon copy) recipients.
     * 
     * @param type=TO;CC;BCC
     */
    public void setRecipients(String[] address, String type) {
	try {
	    Address[] add = new Address[address.length];
	    if (address.length - 1 >= 0) {
		for (int i = 0; i <= address.length - 1; i++) {
		    add[i] = new InternetAddress(address[i]);
		}
		if (type == null)
		    type = "TO";
		if (type == "TO")
		    mimeMsg.setRecipients(Message.RecipientType.TO, add);
		else if (type == "CC")
		    mimeMsg.setRecipients(Message.RecipientType.CC, add);
		else if (type == "BCC")
		    mimeMsg.setRecipients(Message.RecipientType.BCC, add);// 收信人
		else
		    new RuntimeException("类型不正确!");
	    }
	} catch (Exception e) {
	    new RuntimeException("邮件收件人地址设置失败！");
	}
    }

    public void setSentDate() {
	try {
	    mimeMsg.setSentDate(new Date());
	} catch (Exception e) {
	    new RuntimeException("时间设置失败！", e);
	}
    }

    /**
     * 发送
     * 
     * @return
     */
    public boolean sendMail() {
	boolean flag = false;
	try {
	    mimeMsg.setContent(mp);
	    mimeMsg.saveChanges();
	    Session mailSession = Session.getInstance(props, auth);
	    Transport transport = mailSession.getTransport("smtp");
	    transport.connect((String) props.get("mail.smtp.host"), username, password);
	    transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
	    // System.out.println("发送邮件成功！");
	    transport.close();
	    flag = true;
	} catch (SendFailedException e1) {
	    e1.printStackTrace();
	    throw new RuntimeException("邮件发送失败SendFailed！", e1);
	} catch (MessagingException e2) {
	    e2.printStackTrace();
	    throw new RuntimeException("邮件发送失败Messaging！", e2);
	} catch (Exception e3) {
	    e3.printStackTrace();
	    throw new RuntimeException("邮件发送失败！", e3);
	}
	return flag;
    }

    public static void main(String[] args) {

	try {

	    JmailUtil ms = new JmailUtil();
	    ms.setSubject("邮箱激活");
	    ms.setContent(new String("zxzzzzzzzzz"), "text/html;charset=GBK");
	    ms.setFrom("hydzfp");
	    ms.setRecipients("ddout@qq.com");
	    // ms.setRecipients(array, "TO"); //抄送多个人
	    ms.setSentDate();
	    ms.sendMail();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
