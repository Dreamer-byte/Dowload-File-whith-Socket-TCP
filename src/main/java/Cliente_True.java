/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */

import java.net.Socket;
import java.io.*;
import javax.swing.JOptionPane;
public class Cliente_True {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String IpServe;
        String NameFile;
        BufferedInputStream ReadFile;
        BufferedOutputStream WriteFile;
        String MensajeServe;
        String MensajeClient;
        
        IpServe = JOptionPane.showInputDialog("****Ingresa la ip del del server****");
        
        try
        {
            Socket Connect = new Socket(IpServe,1099);
            DataInputStream InputCliente = new DataInputStream(Connect.getInputStream());
            DataOutputStream OupoutServe = new DataOutputStream(Connect.getOutputStream());
            
            MensajeClient = InputCliente.readUTF();
            JOptionPane.showMessageDialog(null,"Servidor dice: "+MensajeClient);
            
            NameFile = JOptionPane.showInputDialog("Ingresa el nombre del archivo a descargar");
            MensajeServe = NameFile;
            OupoutServe.writeUTF(MensajeServe);
            
            MensajeClient = InputCliente.readUTF();
            
            ReadFile = new BufferedInputStream(Connect.getInputStream());
            WriteFile = new BufferedOutputStream(new FileOutputStream(NameFile));
            
            int inicioFuente = 0;
            int ByteWiteSZecuence;
            byte[]  Bufer = new byte[1024];

            if(MensajeClient.equals("El archivo no existe"))
            {
                JOptionPane.showMessageDialog(null,"El archivo ya no existe en el server");
            }
            else
            {
                while((ByteWiteSZecuence = ReadFile.read(Bufer)) != -1)
                {
                    WriteFile.write(Bufer, inicioFuente, ByteWiteSZecuence);
                }
                
                int a = Integer.parseInt(JOptionPane.showInputDialog("Desea detener la connecxio?\n1.:Cerrar\nCualquier otro dseguir."));
                
                if(a==1)
                {
                    MensajeServe = "closeconect";
                    OupoutServe.writeUTF(MensajeServe);
                }else
                {
                       JOptionPane.showMessageDialog(null,InputCliente.readUTF());
                }
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Errro: "+ e.getMessage());
        }
        
                
                
    }
    
}
