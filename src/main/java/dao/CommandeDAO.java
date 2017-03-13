/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.JpaUtil.obtenirEntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Commande;
import metier.modele.Produit;
import metier.modele.Restaurant;

/**
 *
 * @author Anthony
 */
public class CommandeDAO {
    
    public enum Etat{
        EN_ATTENTE,EN_COURS,TERMINE
    }
    

    /**
     * 
     * @param hm : HashMap Objet Produit / quantité de produit
     * @param c : Objet client
     * @param r : Objet Restaurant
     * @return La commande créer
     */
    public Commande createCommande(HashMap<Produit,Integer> hm,Client c,Restaurant r) {
        Date today = new Date();
        EntityManager em = JpaUtil.obtenirEntityManager();
        // Pour la durée : voir API Google MAP
        // Pour le livreur : voir DAO livreur
        Commande commande = new Commande(Etat.EN_ATTENTE.ordinal(), hm, today, null, r, null, c);
        em.persist(commande);
        return commande;
    }
    
    public Commande findById(long id) throws Exception {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Commande commande = null;
        try {
            commande = em.find(Commande.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return commande;
    }
    
}
