/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinoo;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Arduinoo {
    PanamaHitek_Arduino dispositivo;                //Declaro variables
    JTextField campo;
    SerialPortEventListener listener= new SerialPortEventListener() {  //evento para imprimir en "campo", los datos de destancia 

        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if(dispositivo.isMessageAvailable()==true){
                    System.out.println("ok");
                    campo.setText(dispositivo.printMessage());
                }
            } catch (SerialPortException ex) {
                System.out.println(ex);
            } catch (ArduinoException ex2){
                System.out.println(ex2);
            }
        }
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arduinoo ard= new Arduinoo();
        ard.arduino();
    }

    public void arduino() {
        dispositivo=new PanamaHitek_Arduino();
        try {
           dispositivo.arduinoRXTX("COM6", 9600,listener );
        } catch (Exception e) { 
            System.out.println(e);
        }
        JFrame ventanita = new JFrame("Arduino");                  //Se instancia el frame
        JPanel panel = new JPanel();           
        campo = new JTextField();
        JLabel etiqueta = new JLabel("La distancia es de " ); 
        ventanita.setDefaultCloseOperation(3);
        ventanita.setResizable(false); 
        ventanita.add(panel);                                       //Se agrega el panel
        panel.add(etiqueta);
        panel.add(campo);
        panel.setLayout(null);                                       //Se usa el layout en null con el fin de poder enviar las localizaciones exactas y manejar el diseño.
        ventanita.setVisible(true);                                  //Se mustra la ventana con sus caracteristicas
        ventanita.setBounds(700, 100, 800, 800); 
        etiqueta.setBounds(300, 145, 450, 100);
        campo.setBounds(300, 280, 300, 50);
    }
    
    
    
    
    
}
