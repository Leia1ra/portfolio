package spring.boot.portfolio.Configuration.Example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "community") @RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public void communityWrite(Community community) {
        communityRepository.save(community);
    }

    public List<Community> communityList() {
        return communityRepository.findAll();
    }

    public Community communityView(Community community) {
        Optional<Community> optional = communityRepository.findById(community.getId());
        if(optional.isPresent()){
            return optional.get();
        } else {
            community.setIsNull(true);
            return community;
        }
    }
}
