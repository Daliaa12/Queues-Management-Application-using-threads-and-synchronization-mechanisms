package GUI;

import BusinessLogic.Log;
import BusinessLogic.SimulationManager;
import Model.SelectionPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SimulationFrame {
    private JFrame frame= new JFrame("Queues Management");
    //private JLabel label1= new JLabel("Time limit");
    private JLabel label2 = new JLabel("Number of clients");
    private JLabel label3 = new JLabel("Number of queues");
    private JLabel label4= new JLabel("Arrival time interval");
    private JLabel label5= new JLabel("Service time interval");
    private JLabel label6= new JLabel("Queue selection strategy");
    private JRadioButton rabutton= new JRadioButton("SHORTEST_QUEUE");
    private JRadioButton rabutton2= new JRadioButton("SHORTEST_TIME");
    private JButton buttonStart= new JButton("START");
    private JFormattedTextField txtScreen1=new JFormattedTextField();
    private JFormattedTextField txtScreen2= new JFormattedTextField();
    private JFormattedTextField txtScreen3= new JFormattedTextField();
    private JFormattedTextField txtScreen4= new JFormattedTextField();
    private JFormattedTextField txtScreen5= new JFormattedTextField();
    private JFormattedTextField txtScreen6= new JFormattedTextField();
    private JFormattedTextField txtScreen7= new JFormattedTextField();
    private JScrollPane sp = new JScrollPane();
    JTextArea textArea = new JTextArea();


    public SimulationFrame ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,670);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        createObjects();
        addObjects();
        createActionListener();
        frame.setVisible(true);
        sp.setViewportView(textArea);
    }
    private void createObjects() {
        //label1.setSize(380,50);
        //label1.setLocation(7,40);
        //label1.setFont(new Font ("Serif",Font.ITALIC,15));

        label2.setSize(380,50);
        label2.setLocation(7,80);
        label2.setFont(new Font ("Serif",Font.ITALIC,15));

        label3.setSize(380,50);
        label3.setLocation(7,120);
        label3.setFont(new Font ("Serif",Font.ITALIC,15));

        label4.setSize(380,50);
        label4.setLocation(7,160);
        label4.setFont(new Font ("Serif",Font.ITALIC,15));

        label5.setSize(380,50);
        label5.setLocation(7,200);
        label5.setFont(new Font ("Serif",Font.ITALIC,15));

        label6.setSize(380,50);
        label6.setLocation(7,240);
        label6.setFont(new Font ("Serif",Font.ITALIC,15));


        rabutton.setSize(138,16);
        rabutton.setFont(new Font ("Serif",Font.ITALIC,13));
        rabutton.setLocation(170,260);


        rabutton2.setSize(138,16);
        rabutton2.setFont(new Font ("Serif",Font.ITALIC,13));
        rabutton2.setLocation(340,260);

        buttonStart.setSize(473,30);
        buttonStart.setFont(new Font ("Serif",Font.ITALIC,13));
        buttonStart.setLocation(7,300);

        //txtScreen1.setSize(138,25);
        //txtScreen1.setLocation(168,50);

        txtScreen2.setSize(138,25);
        txtScreen2.setLocation(168,93);

        txtScreen3.setSize(138,25);
        txtScreen3.setLocation(168,136);

        txtScreen4.setSize(138,25);
        txtScreen4.setLocation(168,179);

        txtScreen5.setSize(138,25);
        txtScreen5.setLocation(168,222);

        txtScreen6.setSize(138,25);
        txtScreen6.setLocation(340,179);

        txtScreen7.setSize(138,25);
        txtScreen7.setLocation(340,222);

        sp.setBounds(7, 360, 573, 250);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(textArea);
    }

    private void addObjects() {
        frame.add(label3);
        //frame.add(label1);
        frame.add(label2);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(rabutton);
        frame.add(rabutton2);
        frame.add(buttonStart);
        //frame.add(txtScreen1);
        frame.add(txtScreen2);
        frame.add(txtScreen3);
        frame.add(txtScreen4);
        frame.add(txtScreen5);
        frame.add(txtScreen6);
        frame.add(txtScreen7);
        frame.add(sp);
        Log.setTextArea(textArea);


    }
    private void createActionListener() {
        buttonStart.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                Integer client= Integer.valueOf(txtScreen2.getText());
                Integer minService=Integer.valueOf(txtScreen5.getText());
                Integer maxService=Integer.valueOf(txtScreen7.getText());
                Integer minArrival=Integer.valueOf(txtScreen4.getText());
                Integer maxArrival=Integer.valueOf(txtScreen6.getText());
                Integer queue=Integer.valueOf(txtScreen3.getText());
                if (rabutton.isSelected()) {
                    SimulationManager gen = new SimulationManager(
                            SelectionPolicy.SHORTEST_QUEUE,
                            client,
                            maxService,
                            minService,
                            queue,
                            maxArrival,
                            minArrival
                    );
                    Thread t = new Thread(gen);
                    t.start();
                } else if (rabutton2.isSelected()) {
                    SimulationManager gen = new SimulationManager(
                            SelectionPolicy.SHORTEST_TIME,
                            client,
                            maxService,
                            minService,
                            queue,
                            maxArrival,
                            minArrival
                    );
                    Thread t = new Thread(gen);
                    t.start();
                }
            }
        });

    }
    public static void main(String[] args) {
        new SimulationFrame();
    }
}
