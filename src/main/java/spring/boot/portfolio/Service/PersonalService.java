package spring.boot.portfolio.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.portfolio.Model.AboutMeModel.AboutMeCollection;
import spring.boot.portfolio.Model.AboutMeModel.AwardCollection;
import spring.boot.portfolio.Model.AboutMeModel.GrowthCollection;
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
        return introductionRepository.findAllByOrderByNoAsc();
    }
    public List<GrowthCollection> growthFind() {
        return growthRepository.findAllByOrderByStartDateDesc();
    }
    public List<AwardCollection> awardFind() {
        return awardRepository.findAllByOrderByDateDesc();
    }


    /* AboutMe */
    @Transactional
    public void aboutMeSave(AboutMeCollection ac) throws Exception {
        aboutMeRepository.save(ac);
    }
    @Transactional
    public void aboutMeExceptionSelect(String id) throws Exception{
        aboutMeRepository.deleteAllByIdNot(id);
    }


    /* Introduction */
    @Transactional
    public IntroductionCollection introduceSave(IntroductionCollection ic) throws Exception {
        return introductionRepository.save(ic);
    }

    @Transactional
    public void introduceDelete(String id) throws Exception {
        introductionRepository.deleteById(id);
        // if(true){throw new RuntimeException("ㅎㅇ");}
    }


    /* Growth */
    @Transactional
    public GrowthCollection growthSave(GrowthCollection gc) throws Exception {
        return growthRepository.save(gc);
    }
    @Transactional
    public void growthDelete(String id) throws Exception {
        growthRepository.deleteById(id);
        // if(true){throw new RuntimeException("ㅎㅇ");}
    }

    /* Award */
    @Transactional
    public AwardCollection awardSave(AwardCollection gc) throws Exception {
        return awardRepository.save(gc);
    }
    @Transactional
    public void awardDelete(String id) throws Exception {
        awardRepository.deleteById(id);
        // if(true){throw new RuntimeException("ㅎㅇ");}
    }
}
