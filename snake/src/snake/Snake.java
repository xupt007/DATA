package snake;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame(); // ����һ����Ϸ����Ŀ��
		frame.setBounds(10, 10, 900, 720); // ���ÿ�ܵĴ�С
		frame.setResizable(false); // ���ÿ�ܴ�СΪ���ܸı�
		// ����رհ�ť �ر���Ϸ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel panel = new SnakePanel(); // ��ӻ���
		frame.add(panel); // �����ʱ�����ǿյĿ�����

		frame.setVisible(true); // ������ʾ��Ϸ����
	}
}
