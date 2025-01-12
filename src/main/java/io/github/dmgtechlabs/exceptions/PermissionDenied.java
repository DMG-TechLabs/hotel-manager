/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.dmgtechlabs.exceptions;

/**
 *
 * @author kostas
 */
public class PermissionDenied extends Exception { 
    public PermissionDenied(){
        super("Permission Denied!");
    }

      // Constructor that accepts a message
      public PermissionDenied(String message)
      {
         super(message);
      }
}
