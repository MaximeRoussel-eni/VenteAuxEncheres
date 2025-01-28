package controller;

import bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ArticleVenduService;

import java.util.List;


@Controller
@RequestMapping("/encheres")
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;

    public ArticleVenduController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }

    @GetMapping("/")
    public String afficherEncheres(Model model){
        List<ArticleVendu> articleVenduList = articleVenduService.getAllArticleVendu();
        model.addAttribute("articleVenduList", articleVenduList);
        return "auctions";
    }


    @GetMapping("/creer")
    public String afficherCreerArticleVendu(Model model){
        model.addAttribute("articleVendu", new ArticleVendu());
        return "auction-create";
    }

    @PostMapping("/creer")
    public String creerArticleVendu(@ModelAttribute("articleVendu") ArticleVendu articleVendu){
        articleVenduService.addArticleVendu(articleVendu);
        return "redirect:/auctions";
    }

    @GetMapping("/detail")
    public String detailArticleVendu(Model model, @RequestParam(name = "noArticleVendu") int noArticleVendu){
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(noArticleVendu);
        model.addAttribute("articleVendu", articleVendu);
        return "auction-detail";
    }
}
