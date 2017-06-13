import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawPanel extends JPanel implements MouseListener, BoardImageInteface, BoardGameListener {

    private BufferedImage img;
    private String name;
    private int[] border=new int[21];
    private Game game;
    private GameThread gameThread;

    public DrawPanel(String name) {
        this.name=name;
        img=new BufferedImage(422,422,BufferedImage.TYPE_INT_ARGB);

        addMouseListener(this);
        drawBackground();
        game=new Game();
    }

    public BufferedImage getImg() {
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.drawImage(img,0,0,this);
    }

    public void drawPoint(int x, int y, Color color){
        img.setRGB(x,y,color.getRGB());
        this.repaint();
    }

    public void drawWhitePoint(int x, int y){
        img.setRGB(x,y,Color.WHITE.getRGB());
        this.repaint();
    }

    public void drawBackground() {
        for (int i = 1; i < img.getHeight(); i++) {
            for (int j = 1; j < img.getWidth(); j++) {
                drawWhitePoint(i, j);
            }
        }
    }

    public void updateBoard(Game g){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(!g.isBlockFree(i,j)){
                }
                else{

                }
            }
        }
    }

    @Override
    public void clearBoard(){
        drawBackground();
        game.newGame();
    }

    @Override
    public void startGame() {
        gameThread=new GameThread(game);
        gameThread.setBoardGameListener(this);
        gameThread.start();
    }

    public int calculatePositionImage(int n){
        int result, position=0;

        for(int i=0;i<border.length;i++){
            position=i;
            if(i+1>=border.length || n<border[i+1]) break;
        }
        result=border[position]+1;
        return result;
    }

    public int calculatePositionBoard(int n){
        int position=0;
        for(int i=0;i<border.length;i++){
            position=i;
            if(i+1>=border.length || n<border[i+1]) break;
        }
        return position;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        game.setBlock(posX,posY);
        drawPoint(posX,posY,);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void fillRandPoints(int num)  {
        Random random=new Random();

        for(int i=0;i<num;i++){
            int posX=random.nextInt(20);
            int posY=random.nextInt(20);
            if(game.isBlockFree(posX,posY)){
                fillBlock(posX,posY);
                game.setBlock(posX,posY);
            }
        }
    }

    @Override
    public void onAreaCompute(Game game) {
        updateBoard(game);
    }
}
