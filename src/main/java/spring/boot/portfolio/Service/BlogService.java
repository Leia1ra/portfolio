package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.CategoryModel.LangCollection;
import spring.boot.portfolio.Model.CategoryModel.PostModel.BlogCollection;
import spring.boot.portfolio.Model.CategoryModel.SkillCollection;
import spring.boot.portfolio.Repository.LangRepository;
import spring.boot.portfolio.Repository.BlogRepository;
import spring.boot.portfolio.Repository.SkillRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class BlogService {
    @Autowired
    private final BlogRepository postRepository;
    @Autowired
    private final LangRepository langRepository;
    @Autowired
    private final SkillRepository skillRepository;

    public BlogCollection postSave(BlogCollection data){
        data.setLang_id(data.getLang_id().stream().map(name -> findLang(name).getId()).toList());
        data.setSkill_id(data.getSkill_id().stream().map(name -> findSkill(name).getId()).toList());
        return postRepository.save(data);
    }

    public BlogCollection findById(String id){
        return postRepository.findById(id).orElse(BlogCollection.emptyData());
    }
    public BlogCollection findByName(String name){
        return postRepository.findByName(name).orElse(BlogCollection.emptyData());
    }
    public List<BlogCollection> findAll(){
        //        System.out.println(posts);
        return postRepository.findAll();
    }
    public List<BlogCollection> findByNameInclude(String name){
        List<BlogCollection> temp = postRepository.findAll();
        return temp.stream().filter(r -> r.getName().contains(name)).toList();
    }

    private LangCollection findLang(String name){
        LangCollection failed = new LangCollection();
        return langRepository.findByName(name).orElse(failed);
    }
    public List<LangCollection> findLangAll(){
        return langRepository.findAll();
    }
    public List<BlogCollection> findByLang(String name){
        String find_id = findLang(name).getId();
        return findAll().stream().filter(postCollection ->
                !postCollection.getLang_id().stream().filter(str -> str.equals(find_id)).toList().isEmpty()).toList();
    }
    private SkillCollection findSkill(String name){
        SkillCollection failed = new SkillCollection();
        return skillRepository.findByName(name).orElse(failed);
    }
    public List<BlogCollection> findBySkill(String name){
        String find_id = findSkill(name).getId();
        return findAll().stream().filter(postCollection ->
                !postCollection.getSkill_id().stream().filter(str -> str.equals(find_id)).toList().isEmpty()).toList();
    }
    public List<BlogCollection> sortByDate(List<BlogCollection> list, boolean ascending){
        if(ascending){
            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getWrite_day().getTime() - o2.getWrite_day().getTime())
            ).toList();
        }
        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getWrite_day().getTime() - o1.getWrite_day().getTime())
        ).toList();
    }
    public LangCollection saveLang(String name, String img){
        return langRepository.save(new LangCollection(name, img));
    }
    public LangCollection updateLang(String id, String name, String img){
        LangCollection lang = langRepository.findById(id).orElseThrow();
        lang.setName(name);
        lang.setImg(img);
        return langRepository.save(lang); //생성 날짜와 수정날짜가 변동되지 않을 수 있음 확인바람
    }
    public void deleteLang(String id){
        langRepository.deleteById(id);
        List<BlogCollection> posts = postRepository.findAll();
        posts = posts.stream().filter(p -> p.getLang_id().contains(id)).toList();
        posts.forEach(p -> p.setLang_id(p.getLang_id().stream()
                .filter(s -> s.equals(id)).toList())
        );
        postRepository.saveAll(posts);
    }
    public SkillCollection saveSkill(String name, String description, int level, String img){
        return skillRepository.save(new SkillCollection(name,description,level,img));
    }
    public SkillCollection updateSkill(String id, String name, String description, int level, String img){
        SkillCollection skill = skillRepository.findById(id).orElseThrow();
        skill.setName(name);
        skill.setDescription(description);
        skill.setLevel(level);
        skill.setImg(img);
        return skillRepository.save(skill);
    }
    public void deleteSkill(String id){
        skillRepository.deleteById(id);
        List<BlogCollection> posts = postRepository.findAll();
        posts = posts.stream().filter(p -> p.getSkill_id().contains(id)).toList();
        posts.forEach(p -> p.setSkill_id(p.getSkill_id().stream()
                .filter(s -> s.equals(id)).toList())
        );
        postRepository.saveAll(posts);
    }

    public void deleteBlogById(String id){
        postRepository.deleteById(id);
    }
    public void deleteLangById(String id){
        langRepository.deleteById(id);
    }
    public void deleteSkillById(String id){
        skillRepository.deleteById(id);
    }

    public List<SkillCollection> findSkillAll() {
        return skillRepository.findAll();
    }

//    public LangCollection CategoryInputBlogId(String post_id, String name){
//        Optional<LangCollection> c = langRepository.findByName(name);
//        c.orElseThrow(() -> new TypeNotPresentException("존재하지 않는 카테고리에 접근하려 했습니다!", new Throwable()));
//        LangCollection category;
//        category = c.orElseGet(() -> saveLang(name));
//        List<String> ids = category.getBlogs_id();
//        ids.add(post_id);
//        category.setBlogs_id(ids);
//
//        return categoryRepository.save(category);
//    }
//    public List<BlogCollection> sortById(List<BlogCollection> list, boolean ascending){
//        if(ascending){
//            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())).toList();
//        }
//        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList();
//    }

}
