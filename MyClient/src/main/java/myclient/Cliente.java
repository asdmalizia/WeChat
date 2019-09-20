/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

import Recebedor.WeChat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.*;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author aless
 */
public class Cliente{
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner s = new Scanner(System.in);
        
        System.out.printf("Ip do Servidor: ");
        String ip = s.nextLine();
        
        System.out.printf("Porta do Servidor: ");
        int port = s.nextInt();
  
        new Cliente(ip, port).executa();
    }
    
    private String host;
    private int porta;
    
    public Cliente(String host, int porta){
        this.host = host;
        this.porta = porta;
    }
    
    public void executa() throws UnknownHostException, IOException {
        Socket cliente = new Socket(this.host, this.porta);
        
        System.out.println("Conectado ao servidor");
    
        //Thread para receber as mensagens do servidor
        WeChat r = new WeChat(cliente.getInputStream());
        new Thread(r).start();
        
        //Ler mensagens do teclado e mandar pro chat no servidor
        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        while (teclado.hasNextLine()){
            saida.println(teclado.nextLine());
        }
        saida.close();
        teclado.close();
        cliente.close();
    }
}
