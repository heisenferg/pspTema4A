/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emisor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Emisor {
static final int PUERTO=2000;
static final String HOST="localhost";
 static Socket sCliente;   
    public static void main(String[] args) throws IOException {
                      
        while(true){
        Socket sCliente = new Socket (HOST,PUERTO);
            DataInputStream entrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(sCliente.getOutputStream());
            try {
                
            
            
            System.out.println("Introduce un número del 1 al 100");
            //Envío de mensaje
            String mensaje;
            Scanner text = new Scanner(System.in);
            mensaje = text.next();
            salida.writeUTF(mensaje);
            //Respuesta para 100 caracteres            
            String respuesta = entrada.readUTF();
            System.out.println(respuesta);
                        
        }catch (UnknownHostException e){
            System.out.println("Desconocido " + e.getMessage());
        } catch(SocketException e) { 
            System.err.println("Socket: " + e.getMessage()); 
        } catch(IOException e) { 
            System.err.println("E/S: " + e.getMessage()); 
        } catch (Exception e){
             System.err.println("Uso emisor: java Emisor.java NUMERO del 1 al 100");
        }
        
    sCliente.close();

        }
    }
}
