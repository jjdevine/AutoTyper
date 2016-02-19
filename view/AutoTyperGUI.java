package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class AutoTyperGUI extends JFrame
{

	private static final long serialVersionUID = -377325058131005672L;
	private int sWidth = 820, sHeight = 380;
	private JPanel panelInitDelay, panelDelay;
	private JTextArea textArea;
	private JButton bGo, bClear;
	private JLabel lDelay, lInitDelay;
	private JTextField tfDelay, tfInitDelay;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem miSave, miLoad, miMsg, miPad;
	private Dimension panelDim = new Dimension(sWidth-20, 25);
	
	public AutoTyperGUI()
	{
		super("Enter Text");	//form heading
		//create container to place components in:
		Container container = getContentPane();
		container.setLayout(new FlowLayout());	//set flow layout
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * create components
		 */
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		
		miSave = new JMenuItem("Save");
		miLoad = new JMenuItem("Load");
		miMsg = new JMenuItem("Message..");
		miPad = new JMenuItem("Pad");
		
		miSave.setActionCommand("miSave");
		miLoad.setActionCommand("miLoad");
		miMsg.setActionCommand("miMsg");
		miPad.setActionCommand("miPad");
		
		fileMenu.add(miSave);
		fileMenu.add(miLoad);
		fileMenu.add(miMsg);
		fileMenu.add(miPad);
		
		menuBar.add(fileMenu);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		
		panelDelay = new JPanel();
		panelInitDelay = new JPanel();
		
		panelDelay.setPreferredSize(panelDim);
		panelInitDelay.setPreferredSize(panelDim);
		
		lInitDelay = new JLabel("Insert Starting Delay (milliseconds)");
		tfInitDelay = new JTextField(20);
		tfInitDelay.setText("2000");
		
		lDelay = new JLabel("Insert Line Delay (milliseconds)");
		tfDelay = new JTextField(20);
		tfDelay.setText("1500");
		
		bGo = new JButton("GO");
		bGo.setPreferredSize(new Dimension((sWidth-25)/2, 50));
		
		bClear = new JButton("Clear");
		bClear.setPreferredSize(new Dimension((sWidth-25)/2, 50));
		
		bGo.setActionCommand("bGo");
		bClear.setActionCommand("bClear");
		
		/*
		 * add components to container
		 */
		
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBorder(new LineBorder(Color.BLACK));
		jsp.setPreferredSize(new Dimension(sWidth-20, 200));
		
		container.add(jsp);
		
		panelInitDelay.add(lInitDelay);
		panelInitDelay.add(tfInitDelay);
		
		panelDelay.add(lDelay);
		panelDelay.add(tfDelay);
		
		container.add(panelInitDelay);
		container.add(panelDelay);
		
		container.add(bGo);
		container.add(bClear);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();	//get screen resoloution
		setLocation((d.width-sWidth)/2, (d.height-sHeight)/2);	//centre form
		
		setSize(sWidth,sHeight);	//set form size
		setVisible(true);//display screen
	}
	
	public String getText()
	{
		return textArea.getText();
	}
	
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	public void registerListener(ActionListener listener)
	{
		bGo.addActionListener(listener);
		bClear.addActionListener(listener);
		miLoad.addActionListener(listener);
		miSave.addActionListener(listener);
		miMsg.addActionListener(listener);
		miPad.addActionListener(listener);
	}
	
	public String getDelay()
	{
		return tfDelay.getText();
	}
	
	public String getInitDelay()
	{
		return tfInitDelay.getText();
	}
}
