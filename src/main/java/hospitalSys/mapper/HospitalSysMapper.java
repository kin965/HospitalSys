package hospitalSys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.DocumentOrderBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IllnessIdAndDocumentIDBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.bean.OrderInfoBean;
import hospitalSys.bean.TBean;

@Mapper
public interface HospitalSysMapper {

	List<HomePageBean> selectHomePageBean();

	List<DocumentOrderBean> selectById(int kanjaId);

	List<HomePageBean> selectId(int kanjaId);


	List<TBean>TAll();

    void saveKanja(KanjaInfoBean kanjaInfoBean);

    void TS(TBean tBean);

    KanjaInfoBean selectKanjaInfoByKanjaId(int kanjaId);

    void saveNewKanjaInfo(IntroductoryDocumentInfoBean introductoryDocumentInfo);

    void statusConfirmation(int documentId);

    void deleteDocument(int documentId);

    //返回Did最大值的方法
    IllnessIdAndDocumentIDBean selectMaxDId();
    //返回Iid最大值的方法
    IllnessIdAndDocumentIDBean selectMaxIId();
//保存介绍状
    void saveIntroductionInfo(IntroductoryDocumentInfoBean introductoryDocumentInfo);
    //保存定型诊疗所见
    void saveNewClinicFindings(ClinicFindingsBean clinicFindingsBean);
    //保存定型汎用紹介状
    void saveNewGeneralPurpose(GeneralPurposeBean gseneralPurposeBean);

    //获取所有信息，之后根据不同不同文书类型 去显示不同内容
     OrderInfoBean selectInfoById(int kanjaId);

}
