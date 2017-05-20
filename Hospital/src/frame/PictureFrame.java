package frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureFrame extends JFrame implements ChangeListener{

    Image   myImage;
    String  sMsg;
    boolean loadFinished;
    JProgressBar progressBar;
    float progressCount = 0;
    
//    public static void main(String[] av) {
//
//        Test r = new Test(800, 600);
//        r.setVisible(true);
//         r.loadURLImage("http://123.206.13.45/dashboard/img/whf_shuai.jpg",r.getGraphics(),((JFrame)r));
//
//         
//         Test r1 = new Test(800, 600);
//         r1.setVisible(true);
//         r1.loadURLImage("http://123.206.13.45/dashboard/img/whf_shuai.jpg",r.getGraphics(),((JFrame)r));
//         
//     }
    
    /** Construct the object */
    public PictureFrame(int width, int height) {
       super();
       this.setVisible(true);
       sMsg = "Loading...";
       setSize(width, height);
       progressBar = new JProgressBar();
       progressBar.setOrientation(JProgressBar.HORIZONTAL);
       progressBar.setMinimum(0);
       progressBar.setMaximum(100);
       progressBar.setValue(progressBar.getMinimum());
       progressBar.addChangeListener(this);
       progressBar.setPreferredSize(new Dimension(300, 20));
       progressBar.setBounds(0, 0, 300, 20);    
    }
    //加载网络上图片
    public void loadURLImage(String sUrl,Graphics g,JFrame frame){
       Toolkit toolkit;
       loadFinished = false;
       toolkit      = Toolkit.getDefaultToolkit();
       try {
           URL url = new URL(sUrl);
           myImage = toolkit.createImage(url);
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }
       g.drawImage(myImage, 0, 0, frame);
    }
    //加载本地图片
    public void loadLoaclImage(String sFile){
       Toolkit toolkit;
       loadFinished = false;
       toolkit    = Toolkit.getDefaultToolkit();
       myImage    = toolkit.getImage(sFile);
       Graphics g = this.getGraphics();
       g.drawImage(myImage, 6, 36, 100, 100, this);
    }
    //重写父类方法，由父类负责调起
    public void paint(Graphics g) {
       //判断是否加载完成
       if ( loadFinished == true ){
           g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
       }else{
           g.drawString(sMsg, 100, 100);
       }
    }
    int aa = 0;
    //图片加载状态通知函数
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
    	float val = infoflags;
    	if ( infoflags == ALLBITS ) {
           loadFinished = true;
           repaint();
           //System.out.println(val + " " + (++aa));
           progressCount = val;
           return false;
       } else
       {
    	  // System.out.println(val + " " + (++aa));
    	   progressCount = val;
           return true;
       }
    }

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == progressBar) {
			int value = (int) (progressCount / ((float)ALLBITS) * progressBar.getMaximum());
			progressBar.setValue(value);
		}
	}
}
