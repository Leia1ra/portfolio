package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Repository.AboutMeRepository;
import spring.boot.portfolio.Repository.AwardRepository;
import spring.boot.portfolio.Repository.GrowthRepository;
import spring.boot.portfolio.Repository.IntroductionRepository;

import java.util.List;

@Service(value = "PersonalService") @RequiredArgsConstructor
public class PersonalService {
    private final AboutMeRepository aboutMeRepository;
    private final AwardRepository awardRepository;
    private final GrowthRepository growthRepository;
    private final IntroductionRepository introductionRepository;

    public List<AboutMeCollection> aboutMeFind(){
        return aboutMeRepository.findAll();
    }

    public AboutMeCollection aboutMeSave(AboutMeCollection ac) {
        return aboutMeRepository.save(ac);
    }

}
