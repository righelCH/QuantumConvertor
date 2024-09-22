package paquete;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@FunctionalInterface
public interface DocumentListenerSimplificado extends DocumentListener {

 void update(DocumentEvent e);
	
 default void insertUpdate(DocumentEvent e) {
     update(e);
 }
 default void removeUpdate(DocumentEvent e) {
     update(e);
 }

 
 default void changedUpdate(DocumentEvent e) {
     update(e);
 }
}
