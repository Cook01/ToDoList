package Model;

import java.util.Date;

/**
 * <h1>Class Tache</h1>
 *
 * @author  gkueny
 */
public class Tache {

    /**
     * Titre de la Tache
     */
    private String title;

    /**
     * Boolean permettant de savoir si la tache est terminé ou pas
     */
    private Boolean achieve;

    /**
     * Pourcentage de réalisation d'une Tache "au long cours"
     */
    private int percentage;

    /**
     * Début de la Tache
     */
    private Date begin;

    /**
     * Fin de la Tache
     */
    private Date end;

    /**
     * Type de la Tache ( {@link Type} )
     */
    private Type type;

    /**
     * Constructeur de la class sans begin
     *
     * @param title titre de la Tache
     * @param type  type de la Tache
     * @param end   fin de la Tache
     */
    public Tache(String title, Type type, Date end){

        this.title      = title;
        this.achieve    = false;
        this.percentage = 0;
        this.begin      = new Date(System.currentTimeMillis());
        this.end        = end;
        this.type       = type;

    }

    /**
     * Constructeur de la class avec begin (Le {@link Type} sera obligatoirement Type.AuLongCours
     *
     * @param title titre de la Tache
     * @param begin debut de la Tache
     * @param end   fin de la Tache
     */
    public Tache(String title, Date begin, Date end){

        this.title      = title;
        this.achieve    = false;
        this.percentage = 0;
        this.begin      = begin;
        this.end        = end;
        this.type       = Type.AuLongCours;

    }

    /**
     * Setter de l'attribut  title
     *
     * @param title nouveau titre de la Tache
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter de l'attribut achieve
     *
     * @param achieve boolean indiquant si la Tache est terminé ou pas
     */
    public void setAchieve(Boolean achieve) {
        this.achieve = achieve;
    }

    /**
     * Setter de l'attribut percentage
     *
     * @param percentage avancement en pourcentage de la Tache
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Sette de l'attribut begin
     *
     * @param begin date de début de la Tache
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * Setter de l'attribut end
     *
     * @param end date de fin de la tache
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Setter de l'attribut type
     *
     * @param type type de la Tache
     */
    public void setType(Type type) {
        this.type = type;
    }


    /**
     * Getter de l'attribut title
     *
     * @return String title
     */
    public String getTitle() {

        return title;
    }

    /**
     * Getter de l'attribut achieve
     *
     * @return Boolean achieve
     */
    public Boolean getAchieve() {
        return achieve;
    }

    /**
     * Getter de l'atribut percentage
     *
     * @return int percentage
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Getter de l'attribut begin
     *
     * @return {@link Date} begin
     */
    public Date getBegin() {

        return begin;
    }

    /**
     * Getter de l'attribut end
     *
     * @return {@link Date} end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate() {

        if ( new Date(System.currentTimeMillis()).compareTo(this.getEnd()) > 0 ) {
            return true;
        }

        return false;

    }

    /**
     * Getter de l'attribut type
     *
     * @return type de la Tache
     */
    public Type getType() {

        return type;
    }
}
