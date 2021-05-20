package com.example.trabajopractico2lab.Request;

import android.content.Context;

import com.example.trabajopractico2lab.Model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static File archivo;
    private static Usuario usuario;

    private static File conectar(Context context) {
        if (archivo == null)
        {
            archivo = new File(context.getFilesDir(), "datos.dat");
        }
        return archivo;
    }

    public String guardar(Context context, Usuario u) {
        String mensaje;
        usuario = u;
        archivo = conectar(context);
        try {
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream ou = new ObjectOutputStream(bo);
            ou.writeObject(usuario);
            bo.flush();
            fo.close();
            return mensaje = "Exito";
        }
        catch (FileNotFoundException e)
        {
            return mensaje = e.getMessage();
        }
        catch (IOException e)
        {
            return mensaje = e.getMessage();
        }
    }

    public static Usuario leer(Context context) {
        archivo = conectar(context);
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream oi = new ObjectInputStream(bi);
            usuario = (Usuario) oi.readObject();
            fi.close();
            return usuario;
        }
        catch (FileNotFoundException e)
        {
            return usuario = null;
        }
        catch (IOException e)
        {
            return usuario = null;
        }
        catch (ClassNotFoundException e)
        {
            return usuario = null;
        }
    }

    public static Usuario login(Context context, String mail, String pass) {
        Usuario u = leer(context);
        if (u != null) {
            String email = u.getEmail();
            String password = u.getPassword();

            if (email.equals(mail) && password.equals(pass)) {
                usuario= u;
            }
            else
            {
                usuario = null;
            }
        }
        else
        {
            usuario = null;
        }
        return usuario;
    }
}
