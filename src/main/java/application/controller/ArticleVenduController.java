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
import org.springframework.security.core.Authentication;
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

    private final ArticleVenduService articleVenduService;
    private final CategorieService categorieService;
    private final UtilisateurService utilisateurService;


    public ArticleVenduController(ArticleVenduService articleVenduService,
                                  CategorieService categorieService,
                                  UtilisateurService utilisateurService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
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

        return "auction-detail";
    }

    @PostMapping("/encheres/detail")
    public String updateArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu,
                                     @ModelAttribute("retrait") Retrait retrait,
                                     @RequestParam(name="noCategorie") String noCategorie){
        articleVendu.setRetrait(retrait);
        articleVendu.getCategorie().setNoCategorie(Integer.parseInt(noCategorie));
        articleVenduService.updateArticleVendu(articleVendu);
        System.out.println("coucou");
        return "redirect:/encheres";
    }
}

