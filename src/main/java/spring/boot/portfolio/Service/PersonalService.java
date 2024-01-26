package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Model.AboutMeModel.IntroductionCollection;
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
    public List<IntroductionCollection> introduceFind() {
        return introductionRepository.findAll();
    }

    @Transactional
    public void aboutMeSave(AboutMeCollection ac) throws Exception {
        aboutMeRepository.save(ac);

    }

    @Transactional
    public void aboutMeExceptionSelect(String id) throws Exception{
        aboutMeRepository.deleteAllByIdNot(id);
    }

    @Transactional
    public IntroductionCollection introduceSave(IntroductionCollection ic) throws Exception {
        return introductionRepository.save(ic);
        // if(true){throw new RuntimeException("ㅎㅇ");}
    }

    @Transactional
    public void introduceDelete(String id) throws Exception{
        introductionRepository.deleteById(id);
    }
}
