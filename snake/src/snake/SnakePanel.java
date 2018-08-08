package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{

	private static final long serialVersionUID = -2041990567211340763L;
	// �����߸�ͼƬ��������������ͼƬ
    ImageIcon up = new ImageIcon("up.png"); // ���ϵ���ͷ
    ImageIcon down = new ImageIcon("down.png"); // ���µ���ͷ
    ImageIcon left = new ImageIcon("left.png"); // �������ͷ
    ImageIcon right = new ImageIcon("right.png"); // ���ҵ���ͷ
    ImageIcon food = new ImageIcon("food.png");  // ʳ��
    ImageIcon body = new ImageIcon("body.png");  // �ߵ�����
    ImageIcon title = new ImageIcon("title.jpg"); // ��Ϸ���������
    
    // �ߵ�ÿһ����
    int[] snakex = new int [750];
    int[] snakey = new int [750];
    
    // �������ʳ��
    Random rand = new Random();
    int foodx = rand.nextInt(34)*25+25; //�˴�����ֵ�����Լ���Ƶ���Ϸ����Ĵ�С��ȷ��
    int foody = rand.nextInt(24)*25+75;
    
    // ������Ϸ��Ĭ������
    int len = 3;
    int score = 0;
    String direction = "R"; // U�� D�� L�� R��
    
    boolean isStarted = false; // �ж���Ϸ�Ƿ�ʼ
    boolean isFailed = false; // �ж���Ϸ�Ƿ����
    
    Timer timer = new Timer(200,this); // ÿ100�������һ��ActionPerformed
    
    
    public SnakePanel() { // ���컭���Ĺ��캯��
        this.setFocusable(true);  // ��ȡ����
        this.addKeyListener(this); // ���������¼�
        setup();
        timer.start();
    }
    
    public void paint(Graphics g) { // Graphics ����
        
        this.setBackground(Color.BLACK); // ���û���������ɫ
        title.paintIcon(this, g, 25, 11);// ��������ͼƬ
        g.fillRect(25, 75, 850, 650); // �û���������Ϸ����
        
        // ����ͷ��ע���ж���ͷ�ķ���
        if (direction.equals("R")) 
            right.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("L")) 
            left.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("U")) 
            up.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("D")) 
            down.paintIcon(this, g, snakex[0], snakey[0]);
        
        // ���ߵ�����
        for(int i = 1; i < len; i ++)
            body.paintIcon(this, g, snakex[i], snakey[i]);
        
        // �ж������Ϸû��ʼ��ʾ������
        if (!isStarted){ 
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD, 30));
            g.drawString("Press Space to start / pause", 200, 300);
        }
        
        // �ж������Ϸ������ʾ������
        if (isFailed){
            g.setColor(Color.WHITE); 
            g.setFont(new Font("arial",Font.BOLD, 30));
            g.drawString("Game Over ! Press space to restart", 200, 300);
        }
        
        // ��ʾʳ��
        food.paintIcon(this, g, foodx, foody);
        
        // ���÷������ߵĳ���
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,15));
        g.drawString("Score : "+score, 650, 37);
        g.drawString("Len :"+len, 650, 57);
    }
    
    public void setup() { // ��Ϸ��ʼ��
        isStarted = false;
        isFailed = false;
        len = 3;
        score = 0;
        snakex[0] = 100; snakex[1] = 75; snakex[2] = 50;
        snakey[0] = 100; snakey[1] = 100; snakey[2] = 100;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        //ʵ�ּ�����Ӧ 
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_SPACE){ // �û��ո���ʵ/������ʾ��Ϣ
            if (isFailed){
//                isStarted = false;  // ���Խ������з���setup��
//                isFailed = false;
                setup();
            }else 
                isStarted = !isStarted;
        } else if (KeyCode ==  KeyEvent.VK_UP && direction != "D")
                direction = "U";
          else if (KeyCode ==  KeyEvent.VK_DOWN && direction != "U" )
                direction = "D";
          else if (KeyCode ==  KeyEvent.VK_RIGHT && direction != "L")
                direction = "R";
          else if (KeyCode ==  KeyEvent.VK_LEFT && direction != "R")
                direction = "L";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. �ٶ���һ������
        timer.start();
                
        // 2. �ƶ�����
        if (isStarted && !isFailed){
            // �ƶ�����
        for (int i = len; i>0; i--){
            snakex[i] = snakex[i-1];
            snakey[i] = snakey[i-1];
        }
        // �ƶ�ͷ
        if (direction.equals("R")){
            snakex[0] = snakex[0] + 25;
            if(snakex[0] > 850) snakex[0] = 25;
            }else if (direction.equals("L")){
                snakex[0] = snakex[0] - 25;
                if(snakex[0] < 25) snakex[0] = 850;
            }else if (direction.equals("U")){
                snakey[0] = snakey[0] - 25;
                if (snakey[0] < 75) snakey[0] = 650;
            }else if (direction.equals("D")){
                snakey[0] = snakey[0] + 25;
                if (snakey[0] > 650) snakey[0] = 75;
            }
                    
            if (snakex[0] == foodx && snakey[0] == foody){  // ��ʳ��
                    len ++;
                    score ++;
                    foodx = rand.nextInt(34)*25+25;
                    foody = rand.nextInt(24)*25+75;
            }
                    
            for (int i = 1; i < len; i ++){  // �����ͷ�����Լ���������Ϸ����
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]){
                    isFailed = true;
                }
            }
                    
        }
        // 3. repaint����
            repaint();
    }
    
    @Override 
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
