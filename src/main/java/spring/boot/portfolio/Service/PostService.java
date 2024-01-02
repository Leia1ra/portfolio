package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.CategoryModel.CategoryCollection;
import spring.boot.portfolio.Model.CategoryModel.PostModel.PostCollection;
import spring.boot.portfolio.Repository.CategoryRepository;
import spring.boot.portfolio.Repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

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
        return postRepository.findAll();
    }
    public List<PostCollection> findByNameInclude(String name){
        List<PostCollection> temp = postRepository.findAll();
        return temp.stream().filter(r -> r.getName().contains(name)).toList();
    }
    private CategoryCollection findCategory(String name){
        CategoryCollection failed = new CategoryCollection();
        return categoryRepository.findByName(name).orElse(failed);
    }
    public List<CategoryCollection> findCategoryAll(){
        return categoryRepository.findAll();
    }
    public List<PostCollection> findByCategory(String name){
        CategoryCollection category = findCategory(name);
        ArrayList<PostCollection> result = new ArrayList<>();
        category.getPosts_id().forEach(id -> {
            result.add(findById(id));
        });

        return result;
    }
    public List<PostCollection> sortByDate(List<PostCollection> list, boolean ascending){
        if(ascending){
            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getWrite_day().getTime() - o2.getWrite_day().getTime())
            ).toList();
        }
        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getWrite_day().getTime() - o1.getWrite_day().getTime())
        ).toList();
    }
    public CategoryCollection addCategory(String name){
        CategoryCollection result = categoryRepository.save(new CategoryCollection(new ArrayList<>(), name));
        return result;
    }
    public CategoryCollection CategoryInputPostId(String post_id, String name){
        Optional<CategoryCollection> c = categoryRepository.findByName(name);
        CategoryCollection category;
        category = c.orElseGet(() -> addCategory(name));
        ArrayList<String> ids = category.getPosts_id();
        ids.add(post_id);
        category.setPosts_id(ids);

        return categoryRepository.save(category);
    }
//    public List<PostCollection> sortById(List<PostCollection> list, boolean ascending){
//        if(ascending){
//            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())).toList();
//        }
//        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList();
//    }

}
