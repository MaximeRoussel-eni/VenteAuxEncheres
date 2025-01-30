package application.controller;

import application.bo.ArticleVendu;
import application.bo.Categorie;
import application.bo.Utilisateur;
import application.service.CategorieService;
import application.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.service.ArticleVenduService;

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
        //List<ArticleVendu> articleVenduList = articleVenduService.getAllArticleVendu();
        //model.addAttribute("articleVenduList", articleVenduList);
        return "auctions";
    }

    @ModelAttribute("utilisateurEnSession")
    public Utilisateur getUtilisateurEnSession() {
        return utilisateurService.getUtilisateur(1);
    }

    @GetMapping("/creer")
    public String afficherCreerArticleVendu(Model model, @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession){
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("listeCategories", listCategories);
        model.addAttribute("articleVendu", new ArticleVendu());
        return "auction-create";
    }

    @PostMapping("/creer")
    public String creerArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession){
        articleVenduService.addArticleVendu(articleVendu, utilisateurEnSession);
        return "redirect:/encheres";
    }

    @GetMapping("/detail")
    public String detailArticleVendu(Model model, @RequestParam(name = "noArticleVendu") int noArticleVendu){
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(noArticleVendu);
        model.addAttribute("articleVendu", articleVendu);
        return "auction-detail";
    }
}
