package application.controller;

import application.bo.*;
import application.service.*;
import application.bo.ArticleVendu;
import application.bo.Categorie;
import application.bo.Retrait;
import application.bo.Utilisateur;
import application.service.CategorieService;
import application.service.UtilisateurService;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/encheres")
@SessionAttributes("utilisateurEnSession")
public class ArticleVenduController {

    private final ArticleVenduService articleVenduService;
    private final CategorieService categorieService;
    private final UtilisateurService utilisateurService;
    private final EnchereService enchereService;

    // Constructor injection
    public ArticleVenduController(ArticleVenduService articleVenduService,
                                  CategorieService categorieService,
                                  UtilisateurService utilisateurService,
                                  EnchereService enchereService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
        this.enchereService = enchereService;
    }

    @ModelAttribute("utilisateurEnSession")
    public Utilisateur getUtilisateurEnSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String pseudo = authentication.getName();
            return utilisateurService.getUtilisateurByPseudo(pseudo);
        }
        return null;
    }

    @GetMapping()
    public String afficherEncheres(@RequestParam(name = "nomArticle", required = false) String nomArticle,
                                   @RequestParam(name = "categorie", required = false) Integer noCategorie,
                                   Model model) {
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("listeCategories", listCategories);
        List<ArticleVendu> articleVenduList = articleVenduService.getAllArticleVendu();
       if ((nomArticle != null && !nomArticle.isEmpty()) || noCategorie != null) {
            articleVenduList = articleVenduService.getArticlesFiltres(nomArticle, noCategorie);
        } else {
            // Aucun filtre -> afficher tous les articles
         articleVenduList = articleVenduService.getAllArticleVendu();
       }

        model.addAttribute("articleVenduList", articleVenduList);
        return "auctions";
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
                                    @RequestParam(name = "noCategorie") String categorie) {
        int noCategorie = Integer.parseInt(categorie);
        articleVenduService.addArticleVendu(articleVendu, utilisateurEnSession, retrait, noCategorie);

        return "redirect:/encheres";
    }

    @GetMapping("/detail")
    public String detailArticleVendu(Model model, @RequestParam(name = "noArticleVendu") int noArticleVendu) {
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(noArticleVendu);
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("retrait", articleVendu.getRetrait());
        model.addAttribute("listeCategories", listCategories);
        model.addAttribute("enchere", new Enchere());
        return "auction-detail";
    }

    @PostMapping("/detail/modifier")
    public String updateArticleVendu(@ModelAttribute (name = "articleVendu") ArticleVendu articleVendu,
                                     @ModelAttribute("retrait") Retrait retrait,
                                     @RequestParam(name="noCategorie") String noCategorie,
                                     @RequestParam(name="noArticleVendu") int noArticleVendu,
                                     @SessionAttribute(name="utilisateurEnSession") Utilisateur utilisateurEnSession) {
        articleVendu.setNoArticle(noArticleVendu);
        System.out.println(retrait);
        articleVendu.setRetrait(retrait);
        var categorie = new Categorie();
        categorie.setNoCategorie(Integer.parseInt(noCategorie));
        articleVendu.setCategorie(categorie);
        articleVendu.setUtilisateurVendeur(utilisateurEnSession);
        articleVenduService.updateArticleVendu(articleVendu);
        return "redirect:/encheres";
    }

    @PostMapping("/detail/offre")
    public String nouvelleOffre(@RequestParam (name = "noArticleVendu") int noArticleVendu,
                                @ModelAttribute (name = "enchere") Enchere enchere,
                                @SessionAttribute(name="utilisateurEnSession") Utilisateur utilisateurEnSession){

        var article = new ArticleVendu();
        article.setNoArticle(noArticleVendu);
        enchere.setDateEnchere(LocalDate.now());
        enchere.setArticleVendu(article);
        enchere.setUtilisateur(utilisateurEnSession);
        enchereService.addEnchere(enchere);

        return "redirect:/encheres";
    }
}

