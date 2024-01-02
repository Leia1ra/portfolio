package spring.boot.portfolio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String PostInsertPage(){
        return "Post/PostInsert";
    }
    @RequestMapping("/PostInsertAction")
    public String PostInsertAction(String post_name, String post_content, String post_category){
        ArrayList<PostContent> contents = new ArrayList<>();
        contents.add(new PostContent(ContentMode.str, post_content));

        PostCollection temp = new PostCollection(post_name, contents, "test");

        postService.CategoryInputPostId(postService.postSave(temp).getId(),post_category);
        return "redirect:/PostInsertPage";
    }
}
