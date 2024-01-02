package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.PostModel.PostCollection;
import spring.boot.portfolio.Repository.PostRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public boolean postSave(PostCollection data){
        PostCollection result = postRepository.save(data);
        return result != null;
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
    public List<PostCollection> sortByDate(List<PostCollection> list, boolean ascending){
        if(ascending){
            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getWrite_day().getTime() - o2.getWrite_day().getTime())
            ).toList();
        }
        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getWrite_day().getTime() - o1.getWrite_day().getTime())
        ).toList();
    }
//    public List<PostCollection> sortById(List<PostCollection> list, boolean ascending){
//        if(ascending){
//            return list.stream().sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId())).toList();
//        }
//        return list.stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList();
//    }
}
