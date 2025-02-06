package application.controller;

import application.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.service.UtilisateurService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/encheres")
@SessionAttributes("utilisateurEnSession")
public class UtilisateurController {


    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {this.utilisateurService = utilisateurService;}



    @ModelAttribute("utilisateurEnSession")
    public Utilisateur getUtilisateurEnSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String pseudo = authentication.getName();

            return utilisateurService.getUtilisateurByPseudo(pseudo);
        }
        return null;
    }

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
    public String inscription(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);

        System.out.println(utilisateur);
        return "auctions";
    }

    @PostMapping("/profilUtilisateur/supprimer")
    public String supprimerUtilisateur(@SessionAttribute ("utilisateurEnSession") Utilisateur utilisateur) {
        utilisateurService.deleteUtilisateur(utilisateur.getNoUtilisateur());
        return "redirect:/encheres/logout";
    }
}
