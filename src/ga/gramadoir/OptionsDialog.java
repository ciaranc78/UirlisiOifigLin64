/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ga.gramadoir;

import javax.swing.JOptionPane;

/**
 *
 * @author ciaran
 */
public class OptionsDialog extends Thread{
  
  private ga.gramadoir.Main mainThread;

  
  OptionsDialog(final ga.gramadoir.Main main) {

      mainThread = main;
    
  }

 
  @Override
  public void run() {
    try {
      //Object[] options = new String[]{"op1", "op2"};
      //JOptionPane.showOptionDialog(null, this, "An Gramadóir - Roghanna", MIN_PRIORITY, MIN_PRIORITY, null, options, mainThread);

        Object[] possibilities = {"Gaeilge", "Bearla", "Fraincis"};

        String language = (String) JOptionPane.showInputDialog( null, "Roghnaigh do theanga don aschur",
                                        "Uirlisí Oifig - An Gramadóir", JOptionPane.PLAIN_MESSAGE,
                                         null, possibilities, mainThread.getLanguage());
        String languageDesc="";
//If a string was returned, say so.
        if ((language != null) && (language.length() > 0)) {
           if (mainThread != null) {
               if(language.equals("Gaeilge"))
                   languageDesc="ga";
               else if (language.equals("Bearla"))
                   languageDesc="en";
               else if (language.equals(("Fraincis")))
                   languageDesc="fr";
               else
                   languageDesc="ga";

              mainThread.setLanguage(language);
              mainThread.resetOptions(languageDesc);
           }
      }
    } catch (Throwable e) {
      Main.showError(e);
    }
  }

}
