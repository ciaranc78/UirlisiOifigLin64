/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ga.gramadoir.teangai;

/**
 *
 * @author ciaran
 */
public class Gaeilge extends Languages{

    public String[] getSuggestions(String comment, String word){

        String sug;
        String [] suggestions;
        boolean removeSeimhiu=false;
        
 
        if (comment.contains("An raibh /")) {
            sug = comment.replace("An raibh /", "").replace("/ ar intinn agat?", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Focal anaithnid: /")) {
            sug = comment.replace("Focal anaithnid: /", "").replace("/?", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Ba chóir duit /")) {
            sug = comment.replace("Ba chóir duit /", "").replace("/ a úsáid anseo", "").replaceAll("^\"|\"$", "");
            suggestions = youShouldUse(sug, word);
        } else if (comment.contains("Bunaithe ar fhocal mílitrithe go coitianta /")) {
            suggestions = bunaitheAr(comment.replace("Bunaithe ar fhocal mílitrithe go coitianta /", "").replace("/", "").replaceAll("^\"|\"$", ""), word);
        } else if (comment.contains("Bunaithe go mícheart ar an bhfréamh /")) {
            suggestions = bunaitheArFreamh(comment.replace("Bunaithe go mícheart ar an bhfréamh /", "").replace("/", "").replaceAll("^\"|\"$", ""),word);
        } else if (comment.contains("Foirm neamhchaighdeánach de /")) {
            sug = comment.replace("Foirm neamhchaighdeánach de /", "").replace("/", "").replaceAll("^\"|\"$", "");//.replaceAll("[\\,()]","");
            suggestions = sug.split(", ");
        } else if (comment.contains("Focal ceart ach tá /")) {
            sug = comment.replace("Focal ceart ach tá /", "").replace("/ níos coitianta", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Bunaithe ar fhoirm neamhchaighdeánach de /")) {
            sug = comment.replace("Bunaithe ar fhoirm neamhchaighdeánach de /", "").replace("/", "").replaceAll("^\"|\"$", "");//.replaceAll("[\\,()]","");
            suggestions = sug.split(", ");
        } else if ( comment.contains("Réamhlitir /")) {
            suggestions=new String[1];
            if(comment.contains("ar iarraidh")){
                String prefix = comment.replace("Réamhlitir /", "").replace("/ ar iarraidh", "").replaceAll("^\"|\"$", "");
                suggestions = insertPrefix(prefix, word);
            } else if (comment.contains("gan ghá")){
                String prefix = comment.replace("Réamhlitir /", "").replace("/ gan ghá", "").replaceAll("^\"|\"$", "");
                suggestions = removeReamhLitir(prefix, word);
            }
        } else if (comment.contains("Urú gan ghá")) {
            suggestions = removeEclipsis(word);
        } else if (comment.contains("Urú nó séimhiú gan ghá")) {
            removeSeimhiu=true;
            suggestions = removeEclipsisOrSeimhiu(word);
        } else if (comment.contains("/ go minic, ach ní léir é sa chás seo")) {
            suggestions = insertLenition(word);
//        } else if (comment.contains("Tá gá leis an fhoirm spleách anseo")) {
            // Ní amhain gur chonaic
//           return suggestions;
        } else if (comment.contains("Séimhiú gan ghá")) {
            removeSeimhiu=true;
            suggestions = removeLenition(word);
        } else if (comment.contains("Séimhiú ar iarraidh")) {
            suggestions = insertLenition(word);
        } else if (comment.contains("Urú ar iarraidh")) {
            suggestions = insertEclipsis(word);
        } else if (comment.contains("Urú nó séimhiú ar iarraidh")) {
            suggestions =  getInitialMutation(word);
        } else if (comment.contains("Ní úsáidtear an focal seo ach san abairtín /")) {
            sug = comment.replace("Ní úsáidtear an focal seo ach san abairtín /", "").replace("/ de ghnáth", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Is an fhoirm tháite, leis an iarmhír /-fidís")) {
            suggestions = new String[1];
            suggestions[0] = word.replace("feadh siad", "fidís");
        }
        else if (comment.contains("Is an fhoirm tháite, leis an iarmhír /-faidís")) {
            suggestions = new String[1];
            suggestions[0] = word.replace("fadh siad", "faidís");
        }


       else
            suggestions = new String[0];

        return parseSuggestions(suggestions, word, removeSeimhiu);

    }

    public String getErrorMessage(){
        return "Fadhb leis an abairt seo a leanas:\n\n";
    }

       
}
