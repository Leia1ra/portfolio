package spring.boot.portfolio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.boot.portfolio.Model.CategoryModel.CategoryCollection;
import spring.boot.portfolio.Model.CategoryModel.PostModel.ContentMode;
import spring.boot.portfolio.Model.CategoryModel.PostModel.PostCollection;
import spring.boot.portfolio.Model.CategoryModel.PostModel.PostContent;
import spring.boot.portfolio.Service.PostService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping("/PostList") @ResponseBody
    public List<PostCollection> PrintList(String mode){
        if (mode == null) mode = "all";
        if(mode.equals("include_name"))
            return postService.findByNameInclude("ㅎㅇ");
        else if(mode.equals("category"))
            return postService.findByCategory("Test");
        return postService.findAll();
    }
    @RequestMapping("/PostInsertPage")
    public String PostInsertPage(Model model){
        List<String> categoryList = postService.findCategoryAll().stream().map(
                CategoryCollection::getName).toList();
        model.addAttribute("categoryNames", categoryList);
        //카테고리 이름 모음을 만들어서, 게시글 작성 시 존재하는 카테고리에 데이터를 추가할지 새로 만들지 선택 가능하게
        return "Post/PostInsert";
    }
    @RequestMapping("/PostInsertAction")
    public String PostInsertAction(String post_name, String post_content, String post_category){
        ArrayList<PostContent> contents = new ArrayList<>();
        contents.add(new PostContent(ContentMode.str, post_content));

        postService.postSave(new PostCollection(post_name, contents, post_category));
//        postService.CategoryInputPostId(postService.postSave(temp).getId(),post_category);
        return "redirect:/PostInsertPage";
    }

    @RequestMapping("/AddCategory")
    public String AddCategory(String category_name){
        postService.addCategory(category_name);
        return "redirect:/PostInsertPage";
    }
}
