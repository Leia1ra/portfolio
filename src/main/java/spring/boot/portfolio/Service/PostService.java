package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.CategoryModel.LangCollection;
import spring.boot.portfolio.Model.CategoryModel.PostModel.PostCollection;
import spring.boot.portfolio.Repository.LangRepository;
import spring.boot.portfolio.Repository.PostRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final LangRepository categoryRepository;

    public PostCollection postSave(PostCollection data){
        return postRepository.save(data);
    }

    public PostCollection findById(String id){
        return postRepository.findById(id).orElse(PostCollection.emptyData());
    }
    public PostCollection findByName(String name){
        return postRepository.findByName(name).orElse(PostCollection.emptyData());
    }
    public List<PostCollection> findAll(){
        var posts = postRepository.findAll();
//        System.out.println(posts);
        return posts;
    }
    public List<PostCollection> findByNameInclude(String name){
        List<PostCollection> temp = postRepository.findAll();
        return temp.stream().filter(r -> r.getName().contains(name)).toList();
    }
    private LangCollection findCategory(String name){
        LangCollection failed = new LangCollection();
        return categoryRepository.findByName(name).orElse(failed);
    }
    public List<LangCollection> findCategoryAll(){
        return categoryRepository.findAll();
    }
    public List<PostCollection> findByCategory(String name){
        List<PostCollection> categorys = findAll().stream().filter(postCollection ->
                postCollection.getCategory().equals(name)).toList();
//        System.out.println(categorys);
        return categorys;
    }
    public List<PostCollection> sortByDate(List<PostCollection> list, boolean ascending){
        if(ascending){
            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getWrite_day().getTime() - o2.getWrite_day().getTime())
            ).toList();
        }
        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getWrite_day().getTime() - o1.getWrite_day().getTime())
        ).toList();
    }
    public LangCollection addCategory(String name){
        LangCollection result = categoryRepository.save(new LangCollection(name));
        return result;
    }
//    public CategoryCollection CategoryInputPostId(String post_id, String name){
//        Optional<CategoryCollection> c = categoryRepository.findByName(name);
//        c.orElseThrow(() -> new TypeNotPresentException("존재하지 않는 카테고리에 접근하려 했습니다!", new Throwable()));
//        CategoryCollection category;
//        category = c.orElseGet(() -> addCategory(name));
//        ArrayList<String> ids = category.getPosts_id();
//        ids.add(post_id);
//        category.setPosts_id(ids);
//
//        return categoryRepository.save(category);
//    }
//    public List<PostCollection> sortById(List<PostCollection> list, boolean ascending){
//        if(ascending){
//            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())).toList();
//        }
//        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList();
//    }

}
