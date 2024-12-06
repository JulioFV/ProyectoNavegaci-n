package com.fvjulio.navegacion.correo;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fvjulio.navegacion.volley.API;
import com.fvjulio.navegacion.volley.VolleySingleton;

public class correo extends Worker {


        public correo(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);

        }

        @NonNull
        @Override
        public Result doWork() {
            String email = getInputData().getString("email");
           String password = getInputData().getString("password");
            //this.actualizaEnBD(password,email);
            boolean isSent = enviar(email, password);
            return isSent ? Result.success() : Result.failure();
        }



        private boolean enviar(String recipientEmail, String newPassword) {
            final String senderEmail = "checkme.peat@gmail.com";
            final String senderPassword = "dcgp bauo whlh gfae";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(senderEmail));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                msg.setSubject("Su contraseña ha sido actualizada");
                msg.setText("Su nueva contraseña es: " + newPassword);

                Transport.send(msg);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

