package application.controller;

import application.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.service.UtilisateurService;

@Controller
@RequestMapping("/encheres")
public class UtilisateurController {


    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {this.utilisateurService = utilisateurService;}

    @GetMapping("/profilUtilisateur")
    public String afficherProfilUtilisateur(Model model, @RequestParam(name = "noUtilisateur") int noUtilisateur) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(noUtilisateur);
        model.addAttribute("utilisateur", utilisateur);
        return "profile-detail";
    }

    //TEMPORAIRE A VOIR AVEC SPRING SECURITY
    @GetMapping("/connexion")
    public String afficherConnexion() {
        return "login";
    }


    //TEMPORAIRE A VOIR AVEC SPRING SECURITY
    @PostMapping("/connexion")
    public String connexion(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        return "redirect:/encheres";
    }

    //TEMPORAIRE A VOIR AVEC SPRING SECURITY
    @GetMapping("/inscription")
    public String afficherInscription(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "register";
    }

    //TEMPORAIRE A VOIR AVEC SPRING SECURITY
    @PostMapping("/inscription")
    public String insctiption(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "redirect:/encheres";
    }
}
