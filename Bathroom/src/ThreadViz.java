import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * ThreadViz - abiklass, millega on mugav visualiseerida lõimede tööd
 * Kuvab kõrvuti lõimede poole saadetud sõnumid
 * 
 * Kasutamine:
 *    lõime lisamine  -  ThreadViz.addThread(t) - argumendina tuleb kaasa anda viit lõimele
 *    sõnumi lisamine -  ThreadViz.addMessage(msg) - kui kutsuda vastava lõime seest välja, lisab teate
 *    UI kuvamine     -  ThreadViz.showUI()
 * 
 * Kasutamise näide - vt. lõpus, klass Ticker
 * 
 * @author Rain Öpik
 */


public class ThreadViz extends JFrame{
	private static final long serialVersionUID = 5100506006872338637L;
	
	private static int width = 1024;
	private static int height = 768;
	
	private static int MAX_MESSAGES_PER_PANEL = 500;
	
	private ButtonPanel buttonPanel;
	private JPanel vizPanel;
	
	private boolean frozen = false;
	

	private AtomicInteger msgCounter = new AtomicInteger();
	
	
	private class ButtonPanel extends JPanel {
		private static final long serialVersionUID = -2897648905282402562L;
		private JToggleButton freeze = new JToggleButton("Freeze");
		public ButtonPanel() {
			setLayout(new FlowLayout());
			
			freeze.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					processMsgQueue();
					frozen = !frozen;
				}
				
			});
			
			add(freeze);
		}
	}
	
	private void processMsgQueue() {
		System.out.println("Proces");
		if (messages != null) {
			for (Thread t : messages.keySet()) {
				if (t != null) {
					ArrayList<String> msgs = messages.get(t);
					
					if (msgs != null && msgs.size() > 0) {
						displayThreadMessages(t, msgs);				
					}
				}
			}
		}
	}

	private class ThreadPanel extends JPanel {
		private static final long serialVersionUID = 5087703384652035716L;
		
		private JTextField tName = new JTextField();
		private JTextArea tMessages = new JTextArea();
		
		private AtomicInteger panelMsgCounter = new AtomicInteger();
		
		public ThreadPanel(String name) {
			setSize(200, 430);
			setPreferredSize(getSize());
			setBackground(Color.DARK_GRAY);
			
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				
			setLayout(null);
			
			int margin = 5;
			tName.setText(name);
			
			tName.setSize(getWidth() - 2*margin, 20);
			tName.setPreferredSize(tName.getSize());
			tName.setLocation(margin, margin);
			add(tName);
			
			
			JScrollPane tScroll = new JScrollPane(tMessages);
		
			tMessages.setLineWrap(true);
			tMessages.setWrapStyleWord(true);
			tMessages.setFont( new Font("Arial", Font.PLAIN, 16));
			
			tScroll.setSize(getWidth() - 2*margin, getHeight() - 2*margin - tName.getHeight());
			tScroll.setPreferredSize(tName.getSize());
			tScroll.setLocation(margin, tName.getHeight() + margin);
			
			add(tScroll);	
		}
		
		public void addMessage(String msg) {
			if (panelMsgCounter.getAndIncrement() > MAX_MESSAGES_PER_PANEL) {
				tMessages.setText("// flushed log //");
				panelMsgCounter.set(0);
			}
			
			tMessages.append(msg);
		}
	}
	
	private static class Starter {
		private static String getTitle(Object o) {
			return o.getClass().getSimpleName();
		}
		
		public static void run(final JFrame frame, int width, int height) {
			frame.setTitle(getTitle(frame));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(width, height);
			frame.setVisible(true);
		}
	}
	

	private Map<Thread, ThreadPanel> threads = new HashMap<Thread, ThreadPanel>();
	private Map<Thread, ArrayList<String>> messages = new HashMap<Thread, ArrayList<String>>();
	private static ThreadViz visualizer = new ThreadViz();
	private boolean ui = false;
	private final Object lock = new Object();
	private final Object msgLock = new Object();
	
	private void add(final Thread t) {

		synchronized(msgLock) {
			messages.put(t, new ArrayList<String>());
		}		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				synchronized(lock) {
					ThreadPanel tpl = new ThreadPanel(t.getName());
					threads.put(t, tpl);
					
					Container c = vizPanel;
					c.add(tpl);
					
					if (ui) {
						c.repaint();
						c.validate();
					}
				}
			}
		});
	}

	
	private void addThreadMessage(final Thread t, final String message) {
		int nextMsgNumber = msgCounter.getAndIncrement();
		String msg = "[" + nextMsgNumber + "] " + message;
		System.out.println(t.getName() + " " + msg);
				
		ArrayList<String> msgs;
		
		synchronized(msgLock) {
			msgs = messages.get(t);
		}
		
		if (msgs == null) {
			add(t);
			synchronized(msgLock) {
				msgs = messages.get(t);
			}			
		}
		
		if (msgs != null) {
			synchronized (msgs) {
				msgs.add(msg);
			}
			if (!frozen) {
				displayThreadMessages(t, msgs);
			}
		}
		
	}
	

	private void displayThreadMessages(final Thread t, ArrayList<String> msgs) {
		if (msgs == null) {
			synchronized (msgLock) {
				msgs = messages.get(t);	
			}
		}
		if (msgs == null) { return; }
		
		final ArrayList<String> umsgs = msgs;
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				ThreadPanel tpl = threads.get(t);
				
				if (tpl != null) {
					synchronized(umsgs) {
						Iterator<String> i = umsgs.iterator();
						while (i.hasNext()) {
							String umsg = i.next();
							i.remove();
							tpl.addMessage(umsg + "\n");
						}
					}
				}
				
			}
		});
	}
	
	private ThreadViz() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		buttonPanel = new ButtonPanel();
		vizPanel = new JPanel();
		c.add(buttonPanel, BorderLayout.PAGE_START);
		c.add(vizPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Kuvab lõimevaatleja kasutajaliidese
	 * NB! Akna sulgemisel lõpetatakse programmi töö
	 */
	public static void showUI() {
		visualizer.ui = true;
		Starter.run(visualizer, width, height);
	}
	
	
	/**
	 * Lisab lõime vaatlejasse
	 * @param t - viit lisatavale lõimele, lõime klassi sees olles sobib:
	 */
	public static void addThread(Thread t) {
		visualizer.add(t);
	}
	
	/**
	 * Lisab uue teate. Seostatakse lõimega, kes meetodi välja kutsub (Thread.currentThread())
	 * NB! Vastav lõim tuleb enne teate saatmist registreerida meetodi addThread() abil
	 * Teade kuvatakse vastava lõime aknas + konsoolil
	 * @param msg - lisatav teade
	 */
	public static void addMessage(String msg) {
		addMessage(Thread.currentThread(), msg);
	}
	
	/**
	 * Lisab uue teate, võimaldab määrata lõime, kellelt teade tuli
	 * @param t - teadet saatev lõim (peab olema eelnevalt registreeritud läbi meetodi addThread()
	 * @param msg - lisatav teade
	 */	
	public static void addMessage(Thread t, String msg) {
		visualizer.addThreadMessage(t, msg);
	}
	
	
}





/*
 * Kasutamise näide
 */

class Ticker extends Thread {
		
	int pause;
		
	public Ticker(ThreadViz v, int pause) {
		this.pause = pause;
		
		ThreadViz.addThread(this);
	}
	public void run() {
		try {
			DateFormat df = new SimpleDateFormat("hh:mm:ss");
			
			while(true) {
				sleep(pause);
				
				String teade = "Kell on:" + df.format(new java.util.Date());
				ThreadViz.addMessage(teade);
			}
			
		}catch (InterruptedException e) {}
	}
}

