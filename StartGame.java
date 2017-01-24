import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.Random;

public class StartGame
{
   private JFrame frame;
   private JPanel panel;
   private int n;
   
   private JButton[][] button;
   private int[][] sit;
   private int[][] prev;
   private int SizeOfButton;
   private int Size = 800;
   
   public StartGame(int num)
   {
      n = num;
      frame = new JFrame();
      panel = new JPanel();
      
      frame.setTitle("Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(Size, Size);
      
      panel.setLayout(new GridLayout(num, num));
      
      SizeOfButton = (int)(Size / num );
      
      button = new JButton[num][num];
      sit = new int[num][num];
      prev = new int[num][num];
      Random randomNumbers = new Random();
   
      for (int i = 0; i < button.length; i++)
      {
         for (int j = 0; j < button.length; j++)
         {
            sit[i][j] = randomNumbers.nextInt(2);
            prev[i][j] = sit[i][j];
            button[i][j] = new JButton();
            button[i][j].setPreferredSize(new Dimension(SizeOfButton, SizeOfButton));
            button[i][j].addActionListener(new ButtonListener());
            
            panel.add(button[i][j]);
         }
      }
      CheckUpdate();
      
      frame.add(panel);
      frame.setVisible(true);
      
      long start = System.nanoTime();
      long stop = System.nanoTime();
      double elapsed;
              
      while(true)
      {
         stop = System.nanoTime();
         elapsed = (stop-start)/1e9;
         if (elapsed > 0.5)
         {
            start = stop;
            ButtonClicked();
         }
      }
   }
   
   public void CheckUpdate()
   {
      for (int i = 0; i < button.length; i++)
      {
         for (int j = 0; j < button.length; j++)
         { 
            if (sit[i][j] == 0)
            {
               button[i][j].setBackground(Color.BLACK);
            }
            else if (sit[i][j] == 1)
            {
               button[i][j].setBackground(Color.WHITE);
            }
         }
      }
   }
   
   //function not used
   public class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //ButtonClicked();
      }
   }
   
   public void ButtonClicked()
   {
      for (int i = 0; i < sit.length; i++)
      {
         for (int j = 0; j < sit[i].length; j++)
         {
            prev[i][j] = sit[i][j];
         }
      }
      
      for (int i = 0; i < sit.length; i++)
      {
         for (int j = 0; j < sit[i].length; j++)
         {
            int count = 0;
         
            count += prev[(i - 1 + prev.length) % prev.length][(j - 1 + prev.length) % prev.length];
            count += prev[(i - 1 + prev.length) % prev.length][j];
            count += prev[(i - 1 + prev.length) % prev.length][(j + 1) % prev.length];
            count += prev[i][(j - 1 + prev.length) % prev.length];
            count += prev[i][(j + 1) % prev.length];
            count += prev[(i + 1) % prev.length][(j - 1 + prev.length) % prev.length];
            count += prev[(i + 1) % prev.length][j];
            count += prev[(i + 1) % prev.length][(j + 1) % prev.length];
            
            if (prev[i][j] == 1)
            {
               if (count < 2)
               {
                  sit[i][j] = 0;
               }
               else if (count > 3)
               {
                  sit[i][j] = 0;
               }
            }
            else if (prev[i][j] == 0)
            {
               if (count == 3)
               {
                  sit[i][j] = 1;
               }
            }
         }
      }
      
      CheckUpdate();
   }   
}