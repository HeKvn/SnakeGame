package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;


/**
 * 舞台类
 */
public class WormStage extends JPanel {
    public static final int ROWS = 35;
    public static final int COLUMNS = 35;
    private Cell food;
    private Worm worm;
    private boolean on;
    private Timer timer;
    private Random random = new Random();
    private KeyControl keyListener;

    /**
     * 初始化舞台
     */
    public WormStage(){
        setPreferredSize(new Dimension(ROWS*Cell.CELL_SIZE,COLUMNS*Cell.CELL_SIZE));
        //对象可以调用类里面的方法，类点出来的只有定义好的变量及常量

        worm = new Worm();
        food = randomFood();
        on = false;
        keyListener = new KeyControl();
    }

    /**
     * 每吃一次食物就加速
     * @return
     */
    public double interval(){
        return Worm.INIT_SPEED * Math.pow((double)39/38,Worm.DEFAULT_LENGTH - worm.getCurrentLength());
    }

    public KeyControl getKeyControl(){
        return keyListener;
    }

    /**
     * 建立内部类继承TimerTask,实现贪吃蛇的自动运行
     * @return
     */
    public class Move extends TimerTask{
        public void run(){
            if(worm.hit(worm.getCurrentDirection())){//是否撞击
                JOptionPane.showMessageDialog(null,"你输了");
                timer.cancel();
                on = false;
                worm = new Worm();
                food = randomFood();
            }else if(worm.creep(worm.getCurrentDirection(),food)){
                food = randomFood(); //先生成食物
                timer.cancel();
                timer = new Timer();
                timer.scheduleAtFixedRate(new Move(),0,(long)interval());
            }
            repaint();
        }
    }


    /**
     * 定义一个键盘监听类内部继承KeyAdapyer类
     */
    public class KeyControl extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    worm.changeDirection(Worm.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    worm.changeDirection(Worm.DOWN);
                    break;
                case KeyEvent.VK_RIGHT:
                    worm.changeDirection(Worm.RIGHT);
                    break;
                case KeyEvent.VK_LEFT:
                    worm.changeDirection(Worm.LEFT);
                    break;
                case KeyEvent.VK_SPACE:
                    if(on){
                        timer.cancel();
                        on = false;
                        break;
                    }else {
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new Move(),0,(long)interval());
                        on = true;
                        break;
                    }
            }
        }
    }

    /**
     * 随机生成食物
     */
    public Cell randomFood(){
        int x,y;
        do{
            x = random.nextInt(ROWS);
            y = random.nextInt(COLUMNS);
        }while (worm.contains(x,y));
        return new Cell(x,y);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getBounds().width,getBounds().height);
        /*从0，0开始，到矩形的宽高截止*/

        g.setColor(Color.green);
        worm.paint(g);

        g.setColor(Color.YELLOW);
        food.paintCell(g);
    }
}
