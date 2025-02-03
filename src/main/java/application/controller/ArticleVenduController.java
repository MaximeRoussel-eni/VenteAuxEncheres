package application.controller;

import application.bo.ArticleVendu;
import application.bo.Categorie;
import application.bo.Retrait;
import application.bo.Utilisateur;
import application.service.CategorieService;
import application.service.UtilisateurService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.service.ArticleVenduService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/encheres")
@SessionAttributes("utilisateurEnSession")
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;
    private UtilisateurService utilisateurService;

    public ArticleVenduController(ArticleVenduService articleVenduService,CategorieService categorieService,UtilisateurService utilisateurService ) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
    }


    @GetMapping()
    public String afficherEncheres(Model model){
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("listeCategories", listCategories);
        List<ArticleVendu> articleVenduList = articleVenduService.getAllArticleVendu();
        model.addAttribute("articleVenduList", articleVenduList);;
        return "auctions";
    }

    @ModelAttribute("utilisateurEnSession")
    public Utilisateur getUtilisateurEnSession() {
        return utilisateurService.getUtilisateur(1);
    }

    @GetMapping("/creer")
    public String afficherCreerArticleVendu(Model model, @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {
        LocalDate date = LocalDate.now();
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("localdate", date);
        model.addAttribute("listeCategories", listCategories);
        model.addAttribute("articleVendu", new ArticleVendu());
        model.addAttribute("retrait", new Retrait());
        return "auction-create";
    }

    @PostMapping("/creer")
    public String creerArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
                                    @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession,
                                    @ModelAttribute("retrait") Retrait retrait,
                                    @RequestParam(name = "noCategorie") String categorie){
        int noCategorie = Integer.parseInt(categorie);
        articleVenduService.addArticleVendu(articleVendu, utilisateurEnSession, retrait, noCategorie);

        return "redirect:/encheres";
    }

    @GetMapping("/detail")
    public String detailArticleVendu(Model model,@RequestParam(name = "noArticleVendu") int noArticleVendu, Principal principal){
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(noArticleVendu);
        //Utilisateur utilisateurEnSession = utilisateurService.getUtilisateurByPseudo(principal.getName());
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("retrait", articleVendu.getRetrait());
        //model.addAttribute("utilisateurEnSession", utilisateurEnSession);
        return "auction-detail";
    }

    @PostMapping("/detail")
    public String updateArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
                                     @ModelAttribute("retrait") Retrait retrait,
                                     @RequestParam(name="noCategorie") String categorie){
        articleVendu.setRetrait(retrait);
        articleVendu.getCategorie().setNoCategorie(Integer.parseInt(categorie));
        articleVenduService.updateArticleVendu(articleVendu);
        return "redirect:/encheres";
    }
}
