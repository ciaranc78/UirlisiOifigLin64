/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ga.gramadoir.teangai;

/**
 *
 * @author ciaran
 */
public class English extends Languages{
    
    public String[] getSuggestions(String comment, String word){

        String sug;
        String [] suggestions;
        boolean removeSeimhiu=false;


        if (comment.contains("Do you mean")) {
            sug = comment.replace("Do you mean /", "").replace("/?", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Unknown word: /")) {
            sug = comment.replace("Unknown word: /", "").replace("/?", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("You should use")) {
            sug = comment.replace("You should use /", "").replace("/ here instead", "").replaceAll("^\"|\"$", "");
            suggestions = youShouldUse(sug, word);
        } else if (comment.contains("Derived form of common misspelling /")) {
            suggestions = bunaitheAr(comment.replace("Derived form of common misspelling /", "").replace("/", "").replaceAll("^\"|\"$", ""), word);
        } else if (comment.contains("Derived incorrectly from the root")) {
            suggestions = bunaitheArFreamh(comment.replace("Derived incorrectly from the root /", "").replace("/", "").replaceAll("^\"|\"$", ""), word);
        } else if (comment.contains("Valid word but /")) {
            sug= comment.replace("Valid word but /", "").replace("/ is more common", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Non-standard form of")) {
            sug = comment.replace("Non-standard form of /", "").replace("/", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("Prefix /")) {
            String prefix = comment.replace("Prefix /", "").replace("/ missing", "").replaceAll("^\"|\"$", "");
            suggestions = insertPrefix(prefix, word);
        } else if (comment.contains("Unnecessary eclipsis")) {
            suggestions = removeEclipsis(word);
        } else if (comment.contains("causes lenition, but this case is unclear")) {
            suggestions = insertLenition(word);
            
        //} else if (comment.contains("The dependent form of the verb is required here")) {
            // Ní amhain gur chonaic

        } else if (comment.contains("Unnecessary lenition")) {
            removeSeimhiu=true;
            suggestions = removeLenition(word);
        } else if (comment.contains("Lenition missing")) {
            suggestions = insertLenition(word);
        } else if (comment.contains("Eclipsis missing")) {
            suggestions = insertEclipsis(word);
        } else if (comment.contains("Initial mutation missing")) {
            removeSeimhiu=true;
            suggestions = getInitialMutation(word);
        } else if (comment.contains("Ní úsáidtear an focal seo ach san abairtín /")) {
            sug = comment.replace("Ní úsáidtear an focal seo ach san abairtín /", "").replace("/ de ghnáth", "").replaceAll("^\"|\"$", "");
            suggestions = sug.split(", ");
        } else if (comment.contains("The synthetic (combined) form, ending in /-faidís/, is often used here")) {
            sug = word.replace("fadh siad", "faidís");
            suggestions = sug.split(", ");
        } else if (comment.contains("The synthetic (combined) form, ending in /-fidís/, is often used here")) {
            suggestions = word.replace("feadh siad", "fidís").split(", ");
        }
           else
            suggestions = new String[0];

        return parseSuggestions(suggestions, word, removeSeimhiu);
    }

    public String getErrorMessage(){
        return "Problem with Gramadóir:";
    }

}
