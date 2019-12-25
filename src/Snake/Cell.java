package Snake;

import java.awt.*;



/**
 * 网格类
 */
public class Cell {
    public static final int CELL_SIZE=10;  //设置每个格子的大小，大小像素为10

    private int x;
    private int y;

    public Cell(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void paintCell(Graphics g){
        g.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
        //x坐标，拿x乘大小获取更大坐标，y同理，最后以相同大小填充
    }
}
