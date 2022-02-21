/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fernando
 */
public class Receptor extends Thread {
    
    Socket dSocket;  
    public static final int PUERTO = 2000;
    String numeroRecibido=null;
    static Random random = new Random();
static int numeroSecreto;
    
  public Receptor(Socket sCliente){
      dSocket = sCliente;    
  }
          
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       numeroSecreto = random.nextInt(100);  

       
       try {
           //Creación del socket
           ServerSocket skSocket = new ServerSocket(PUERTO);
           System.out.println("Escuchando puerto..."  + PUERTO);
           
           //Acciones al recibir
           while (true){

               Socket skCliente = skSocket.accept();
               System.out.println("Cliente conectado con éxito.");
               //Nuevo hilo
               new Receptor (skCliente).start();
           }
           
       } catch (SocketException e){
           System.out.println("Socket " + e.getMessage());
       } catch (IOException e){
           System.out.println("Error de lectura/escritura " + e.getMessage());
       }catch (Exception e){
             System.err.println("El emisor no introdujo un carácter válido.\nUso emisor: java Emisor.java NUMERO del 1 al 100");
        }
    }

    
public void run(){
    
        try {
            
            DataInputStream entrada = new DataInputStream (dSocket.getInputStream());
            DataOutputStream salida = new DataOutputStream (dSocket.getOutputStream());
            System.out.println("Esperando número...");
            
            numeroRecibido= entrada.readUTF();
            System.out.println("Número recibido: " + numeroRecibido);
            
            
            System.out.println("ES: " + numeroSecreto);

            int recibido = Integer.valueOf(numeroRecibido);
            String info;
            
            if(recibido>numeroSecreto){
                info ="El número secreto es inferior a " + recibido;
                salida.writeUTF(info);
            } else if (recibido<numeroSecreto){
                info = "El número secreto es superior a " + recibido;
                salida.writeUTF(info);
            } else if (recibido==numeroSecreto){
                info = "Correcto. El número secreto es " + recibido;
                salida.writeUTF(info);
                System.out.println("Número secreto Adivinado");
                dSocket.close();
            }
            dSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}


    