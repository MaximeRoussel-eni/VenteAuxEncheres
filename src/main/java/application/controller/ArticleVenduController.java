package application.controller;

import application.bo.ArticleVendu;
import application.bo.Categorie;
import application.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.service.ArticleVenduService;

import java.util.List;


@Controller
@RequestMapping("/encheres")
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;

    public ArticleVenduController(ArticleVenduService articleVenduService,CategorieService categorieService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
    }

    @GetMapping()
    public String afficherEncheres(Model model){
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("listeCategories", listCategories);
        //List<ArticleVendu> articleVenduList = articleVenduService.getAllArticleVendu();
        //model.addAttribute("articleVenduList", articleVenduList);
        return "auctions";
    }


    @GetMapping("/creer")
    public String afficherCreerArticleVendu(Model model){
        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("listeCategories", listCategories);
        model.addAttribute("articleVendu", new ArticleVendu());

        return "auction-create";
    }

    @PostMapping("/creer")
    public String creerArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu){
        articleVenduService.addArticleVendu(articleVendu);
        return "redirect:/encheres";
    }

    @GetMapping("/detail")
    public String detailArticleVendu(Model model, @RequestParam(name = "noArticleVendu") int noArticleVendu){
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(noArticleVendu);
        model.addAttribute("articleVendu", articleVendu);
        return "auction-detail";
    }
}
