package chen.calculator;

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Calculator extends JFrame implements KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = -2212698854993368320L;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Calculator frame = new Calculator();
		    frame.addKeyListener(frame);
		   
		    frame.setVisible(true);
		  
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public Calculator() throws HeadlessException {
	super();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel = new JPanel();
	
	JScrollPane scrollPane = new JScrollPane();
	GroupLayout groupLayout = new GroupLayout(getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(21)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(24, Short.MAX_VALUE))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(293, Short.MAX_VALUE))
	);
	
	JTextArea textArea = new JTextArea();
	scrollPane.setViewportView(textArea);
	
	JButton btnNewButton_2 = new JButton("New button");
	btnNewButton_2.addKeyListener(this);
	panel.add(btnNewButton_2);
	
	JButton btnNewButton_1 = new JButton("New button");
	btnNewButton_1.addKeyListener(this);
	panel.add(btnNewButton_1);
	
	JButton btnNewButton = new JButton("New button");
	btnNewButton.addKeyListener(this);
	panel.add(btnNewButton);
	getContentPane().setLayout(groupLayout);
    }

    public Calculator(GraphicsConfiguration gc) {
	super(gc);
	
    }

    public Calculator(String title, GraphicsConfiguration gc) {
	super(title, gc);
	// TODO 自动生成的构造函数存根
    }

    public Calculator(String title) throws HeadlessException {
	super(title);
	// TODO 自动生成的构造函数存根
    }

    public void inputNum(JButton jb) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO 自动生成的方法存根

    }

    @Override
    public void keyPressed(KeyEvent e) {
	System.out.println("WWWWWWWWW");

    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO 自动生成的方法存根

    }
}
