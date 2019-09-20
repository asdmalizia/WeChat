/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tratamento;

import java.io.InputStream;
import java.util.Scanner;
import myservidor.Servidor;

/**
 *
 * @author aless
 */
//classe de tratamento executada numa thread
public class WeChat implements Runnable {
    
    private InputStream cliente;
    private Servidor servidor;
    
    public WeChat(InputStream cliente, Servidor servidor){
        this.cliente = cliente;
        this.servidor = servidor;
    }
    
    public void run(){
       Scanner s = new Scanner(this.cliente);
       while(s.hasNextLine()){
           servidor.distribuiMensagem(s.nextLine());
       }
       s.close();
    }   
    
}
