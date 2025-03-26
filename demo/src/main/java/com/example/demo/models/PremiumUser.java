package com.example.demo.models;
import java.time.LocalDate;

/**
 * Cette classe représente un utilisateur premium (spécial) qui a plus d'avantages que les utilisateurs normaux.
 * C'est comme avoir une carte de membre VIP dans un club qui donne des privilèges spéciaux!
 * Cette classe "hérite" de la classe User, ce qui veut dire qu'elle a toutes les caractéristiques
 * d'un utilisateur normal, plus des choses supplémentaires.
 */
public class PremiumUser extends User {
    // Ces variables contiennent les informations spéciales pour les membres premium
    private LocalDate membershipStart;   // La date quand l'abonnement premium a commencé, comme la date sur ta carte de bibliothèque
    private LocalDate membershipEnd;     // La date quand l'abonnement premium se termine, comme la date d'expiration de ta carte
    private String membershipTier;       // Le niveau d'abonnement (SILVER, GOLD ou PLATINUM), comme avoir une carte bronze, argent ou or
    
    /**
     * C'est comme créer une nouvelle carte de membre VIP avec les réglages par défaut.
     * Quand on crée un utilisateur premium sans donner d'informations:
     * - On commence par créer un utilisateur normal (avec super())
     * - On change son type en "PREMIUM"
     * - On définit la date de début à aujourd'hui
     * - On définit la date de fin à un an plus tard
     * - On lui donne le niveau "SILVER" (le niveau de base)
     */
    public PremiumUser() {
        super();                              // On appelle le constructeur de la classe parent (User)
        this.setUserType("PREMIUM");          // On change le type d'utilisateur en "PREMIUM"
        this.membershipStart = LocalDate.now(); // La date d'aujourd'hui comme début d'abonnement
        this.membershipEnd = membershipStart.plusYears(1); // La date de fin est un an après le début
        this.membershipTier = "SILVER";       // Le niveau d'abonnement par défaut est SILVER (argent)
    }
    
    /**
     * C'est comme créer une nouvelle carte de membre VIP avec les informations de base.
     * Quand on crée un utilisateur premium en donnant ses informations principales:
     * - On commence par créer un utilisateur normal avec ses informations (avec super())
     * - On change son type en "PREMIUM"
     * - On définit la date de début à aujourd'hui
     * - On définit la date de fin à un an plus tard
     * - On lui donne le niveau "SILVER" (le niveau de base)
     */
    public PremiumUser(Long id, String username, String email, String password) {
        super(id, username, email, password); // On appelle le constructeur de la classe parent avec les infos
        this.setUserType("PREMIUM");          // On change le type d'utilisateur en "PREMIUM"
        this.membershipStart = LocalDate.now(); // La date d'aujourd'hui comme début d'abonnement
        this.membershipEnd = membershipStart.plusYears(1); // La date de fin est un an après le début
        this.membershipTier = "SILVER";       // Le niveau d'abonnement par défaut est SILVER (argent)
    }
    
    /**
     * Permet de connaître la date de début de l'abonnement premium.
     * Comme demander: "Depuis quand es-tu membre de ce club?"
     */
    public LocalDate getMembershipStart() {
        return membershipStart;
    }
    
    /**
     * Permet de changer la date de début de l'abonnement premium.
     * Comme si on disait: "Ton abonnement a commencé le..."
     */
    public void setMembershipStart(LocalDate membershipStart) {
        this.membershipStart = membershipStart;
    }
    
    /**
     * Permet de connaître la date de fin de l'abonnement premium.
     * Comme demander: "Jusqu'à quand ton abonnement est-il valable?"
     */
    public LocalDate getMembershipEnd() {
        return membershipEnd;
    }
    
    /**
     * Permet de changer la date de fin de l'abonnement premium.
     * Comme si on disait: "Ton abonnement se terminera désormais le..."
     */
    public void setMembershipEnd(LocalDate membershipEnd) {
        this.membershipEnd = membershipEnd;
    }
    
    /**
     * Permet de connaître le niveau d'abonnement premium (SILVER, GOLD ou PLATINUM).
     * Comme demander: "Es-tu un membre argent, or ou platine?"
     */
    public String getMembershipTier() {
        return membershipTier;
    }
    
    /**
     * Permet de changer le niveau d'abonnement premium.
     * Comme si on disait: "Tu es maintenant un membre or!"
     */
    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }
    
    /**
     * Cette méthode vérifie si l'abonnement premium est encore valable aujourd'hui.
     * C'est comme vérifier si ta carte de bibliothèque n'est pas expirée.
     * Elle retourne "vrai" si l'abonnement est actif, "faux" s'il est expiré.
     */
    public boolean isMembershipActive() {
        return LocalDate.now().isBefore(membershipEnd);
    }
    
    /**
     * Cette méthode prolonge l'abonnement premium d'un certain nombre de mois.
     * C'est comme si on disait: "Ta carte de membre est prolongée de 3 mois!"
     */
    public void extendMembership(int months) {
        this.membershipEnd = this.membershipEnd.plusMonths(months);
    }
    
    /**
     * Cette méthode calcule une réduction sur un montant selon le niveau d'abonnement.
     * Elle "remplace" (override) la méthode du même nom dans la classe User.
     * 
     * Les réductions dépendent du niveau d'abonnement:
     * - PLATINUM: 20% de réduction (paie 80% du prix)
     * - GOLD: 15% de réduction (paie 85% du prix)
     * - SILVER: 10% de réduction (paie 90% du prix)
     * - Autre ou pas d'abonnement: pas de réduction (paie 100% du prix)
     * 
     * C'est comme avoir une carte de fidélité qui donne des réductions différentes selon son niveau.
     */
    @Override
    public double applyDiscount(double amount) {
        if ("PLATINUM".equals(membershipTier)) {
            return amount * 0.80;  // 20% de réduction pour le niveau PLATINUM
        } else if ("GOLD".equals(membershipTier)) {
            return amount * 0.85;  // 15% de réduction pour le niveau GOLD
        } else if ("SILVER".equals(membershipTier)) {
            return amount * 0.90;  // 10% de réduction pour le niveau SILVER
        } else {
            return amount;         // Pas de réduction pour les autres niveaux
        }
    }
}