package handle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class AES extends JFrame
{
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JPanel Panel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JButton action1;
	private JButton action2;
	
	public static void main(String[] args)
	{
		try {
	         UIManager
	                 .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	     } catch (Throwable e) {
	         e.printStackTrace();
	     }
	     EventQueue.invokeLater(new Runnable() 
	     {
	         public void run() 
	         {
	             try {
	                 AES frame = new AES();
	                 frame.setVisible(true);
	                 
	             } catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	     });
	}
	
	public AES()
	{
		setTitle("AES加密");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 550, 400);
        Panel = new JPanel();
        Panel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(Panel);
        Panel.setLayout(null);
		
        label1 = new JLabel("输入加密内容：");
        label1.setBounds(10, 10, 120 ,40);
        Panel.add(label1);
		text1 = new JTextField();
		text1.setBounds(120, 10, 350, 40);
		Panel.add(text1);
		
		label2 = new JLabel("输入秘钥：");
        label2.setBounds(10, 70, 120 ,40);
        Panel.add(label2);
		text2 = new JTextField();
		text2.setBounds(120, 70, 350, 40);
		Panel.add(text2);
		
		label3 = new JLabel("加密前的内容：");
        label3.setBounds(10, 130, 120 ,40);
        Panel.add(label3);
		text3 = new JTextField();
		text3.setBounds(120, 130, 350, 40);
		text3.setEditable(false);
		Panel.add(text3);
		
		label4 = new JLabel("加密后的内容：");
        label4.setBounds(10, 190, 120 ,40);
        Panel.add(label4);
		text4 = new JTextField();
		text4.setBounds(120, 190, 350, 40);
		text4.setEditable(false);
		Panel.add(text4);
		
		label5 = new JLabel("解密后的内容：");
        label5.setBounds(10, 250, 120 ,40);
        Panel.add(label5);
		text5 = new JTextField();
		text5.setBounds(120, 250, 350, 40);
		text5.setEditable(false);
		Panel.add(text5);
		
		action1 = new JButton("加密");
		action1.setBounds(130,300,100,50);
		Panel.add(action1);
		action1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				text3.setText(text1.getText());
				text4.setText(encrypt(text1.getText(), text2.getText()));
			}
		});
		
		action2 = new JButton("解密");
		action2.setBounds(290,300,100,50);
		Panel.add(action2);
		action2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text5.setText(decrypt(encrypt(text1.getText(), text2.getText()), text2.getText()));
			}
		});
	}

	protected String decrypt(String content, String key) {
		
		if (content.length() < 1)
            return null;
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteRresult);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
		
	}

	protected String encrypt(String content, String key) {
		 try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");
	            kgen.init(128, new SecureRandom(key.getBytes()));
	            SecretKey secretKey = kgen.generateKey();
	            byte[] enCodeFormat = secretKey.getEncoded();
	            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
	            Cipher cipher = Cipher.getInstance("AES");
	            byte[] byteContent = content.getBytes("utf-8");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	            byte[] byteRresult = cipher.doFinal(byteContent);
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < byteRresult.length; i++) {
	                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
	                if (hex.length() == 1) {
	                    hex = '0' + hex;
	                }
	                sb.append(hex.toUpperCase());
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;		
	}
}
