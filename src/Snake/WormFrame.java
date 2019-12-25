package Snake;

import javax.swing.*;
import java.awt.*;

public class WormFrame extends JFrame {
    private WormStage ws;
    private JLabel label;

    /**
     * 构造方法，初始化整个框架
     */
    public WormFrame(){
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//设置到屏幕中央
        setTitle("贪吃蛇");
        setResizable(false);
        setLayout(new FlowLayout()); //设置窗体布局器


        ws = new WormStage();
        label = new JLabel("单击空格开始游戏");
        add(label);
        add(ws);
        addKeyListener(ws.getKeyControl());
        ws.addKeyListener(ws.getKeyControl());

        setVisible(true);
    }

    public static void main(String[] args){
        new WormFrame();
        System.out.println("Hello World!");
    }
}
