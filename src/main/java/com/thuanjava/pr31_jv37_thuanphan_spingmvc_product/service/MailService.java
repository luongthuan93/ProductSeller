/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartInfo;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartItem;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author NO1
 */
@Service
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    public void sentHtmlMail(String to, String subject,CartInfo cartInfo) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String mailContent ="<head>\n" +
                            "        <style>\n" +
                            "            table, th, td {\n" +
                            "                border: 1px solid black;\n" +
                            "                border-collapse: collapse;\n" +
                            "            }\n" +
                            "            th, td {\n" +
                            "                padding: 5px;\n" +
                            "                text-align: left;\n" +
                            "            }\n" +
                            "        </style>\n" +
                            "    </head>";
        mailContent += "<p>Dear Mr/Mrs "+"<b>"+ cartInfo.getOrder().getName()+"</b>,"+"</p>";
        mailContent += "<h2><b>Thank You For Your Order!</b></h2>";
        mailContent +="<hr>\n" +
                    "        <h3>"+subject+"</h3>\n" +
                    "        <hr>";
        mailContent += "<table>\n" +
                        "            <tr>\n" +
                        "                <th>Name: </th>           \n" +
                        "                <th>Price: </th>\n" +
                        "                <th>Quantity: </th>\n" +
                        "            </tr>";
        double total = 0;
        for(CartItem item:cartInfo.getCartItems()){
            total += item.getPrice()*item.getQuantity();
            mailContent += "<tr>\n" +
                        "                <td>"+item.getName()+"</td>\n" +
                        "                <td>"+item.getPrice()+"$</td>\n" +
                        "                <td>"+item.getQuantity()+"</td>\n" +
                        "            </tr>";
        }
        
        mailContent += "<tr>\n" +
                    "                <td colspan=\"2\">Total</td>\n" +
                    "                <td>"+total+" $</td>\n" +
                    "                \n" +
                    "            </tr>";
        mailContent +="</table>\n" +
"        \n" +
"        <p><b>Shipping Address: "+cartInfo.getOrder().getAddress()+" </b></p>\n" +
"        \n" +
"        <hr>\n" +
"        \n" +
"        <p>Best regard, </p>\n" +
"        <p>Thuan Phan</p>";
        
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        
        mailSender.send(message);
    }
    
}
