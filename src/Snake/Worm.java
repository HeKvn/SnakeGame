package Snake;


import java.awt.*;
import java.util.Arrays;

/**
 * 贪吃蛇类
 */
public class Worm {
    public static final int UP=1;
    public static final int DOWN=-1;
    public static final int LEFT=2;
    public static final int RIGHT=-2;
    public static final int DEFAULT_LENGTH=6;
    public static final int DEFAULT_DIRECTION=RIGHT;
    public static final int INIT_SPEED=100;
    public image ig;

    private int currentLength;
    private int currentDirection;
    private boolean eat;
    private Cell[] cells;

    /**
     * 初始化方法构造贪吃蛇
     */
    public Worm(){
        cells = new Cell[DEFAULT_LENGTH];
        currentDirection = DEFAULT_DIRECTION;
        currentLength = DEFAULT_LENGTH;

        for(int i = 0;i<DEFAULT_LENGTH;i++){
            cells[i] = new Cell(DEFAULT_LENGTH-i-1,0);
            //初始化贪吃蛇，将蛇头画到Cell[5]上
        }
    }

    /**
     * 获取当前贪吃蛇长度
     */
    public int getCurrentLength() {
        return currentLength;
    }

    /**
     * 获取当前贪吃蛇方向
     */
    public int getCurrentDirection() {
        return currentDirection;
    }

    /**
     * 定义一个方法检查贪吃蛇是否与一个已知结点碰撞
     * 撞上了返回真，没撞上返回假
     */
    public boolean contains(int x,int y){
        for(int i = 0;i<currentLength;i++){
            if(x == cells[i].getX() && y == cells[i].getY()){
                return true;
            }
        }
        return false;
    }
    /**
     * 改变🐍的爬行方向（判断是否是有效的改变方向）
     */
    public void changeDirection(int Direction){
        if(currentDirection == Direction || currentDirection + Direction == 0){
            return;//方向一致或相反,不采取任何操作
        }
        currentDirection = Direction;
    }
    /**
     * 爬行算法，移除尾结点，蛇数组中各点位置后退一个，再将尾结点添加到原来头结点的位置
     * 吃到食物为true，没吃到为false
     */
    public boolean creep(int direction,Cell food){
        eat = false;
        currentDirection = direction;
        Cell head = newHead(currentDirection);

        if(head.getX() == food.getX() && head.getY() == food.getY()){
            cells = Arrays.copyOf(cells,cells.length+1);
            eat = true;
            currentLength++;
        }

        for(int i = cells.length-1;i>0;i--){
            cells[i] = cells[i-1];
        }
        cells[0] = head;
        return eat;
    }

    /**
     * 定义头结点生成算法，爬行过程中的头结点算法
     * @param currentDirection
     * @return
     */

    public Cell newHead(int currentDirection){
        Cell newHead = null;
        switch (currentDirection){
            case UP:
                newHead = new Cell(cells[0].getX(),cells[0].getY()-1);
                break;
            case DOWN:
                newHead = new Cell(cells[0].getX(),cells[0].getY()+1);
                break;
            case RIGHT:
                newHead = new Cell(cells[0].getX()+1,cells[0].getY());
                break;
            case LEFT:
                newHead = new Cell(cells[0].getX()-1,cells[0].getY());
                break;
        }
        return newHead;
    }
    /**
     * 检查贪吃蛇是否撞击的算法
     */
    public boolean hit(int direction){
        Cell nextHead = newHead(currentDirection/*direction*/);
        //是否撞到自身
        if(this.contains(nextHead.getX(),nextHead.getY())){
            return true;
        }
        //检查贪吃蛇是否碰壁
        if(nextHead.getX()<0 || nextHead.getY()<0 || nextHead.getX() >= WormStage.COLUMNS || nextHead.getY() >= WormStage.ROWS){
            return true;
        }

        return false;
    }
    /**
     * 绘制蛇
     */
    public void paint(Graphics g){
        for(int i = 0;i<cells.length;i++){
            cells[i].paintCell(g);
        }
    }

}
