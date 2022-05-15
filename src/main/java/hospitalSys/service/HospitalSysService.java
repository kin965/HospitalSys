package hospitalSys.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.DocumentOrderBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IllnessIdAndDocumentIDBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.bean.OrderInfoBean;
import hospitalSys.bean.TBean;
import hospitalSys.mapper.HospitalSysMapper;
import lombok.Getter;
import lombok.Setter;

@Service
public class HospitalSysService {

	@Autowired
	private HospitalSysMapper hospitalSysMapper;

	// メイン画面のデータ
	public List<HomePageBean> selectHomePage() {

		List<HomePageBean> list = hospitalSysMapper.selectHomePageBean();
		for (HomePageBean x : list) {
			if (x.getFlag().equals("1")) {
				x.setFlag("未確認");
			} else {
				x.setFlag("確認済み");
			}

		}

		// System.out.println("==========+++++++>>>>"+list.get(1));

		return list;
	}

	public List<DocumentOrderBean> selectByKanjaId(int kanjaId) {
		List<DocumentOrderBean> list = hospitalSysMapper.selectById(kanjaId);

		return list;
	}

	@Getter
	@Setter
	public static class PageDatas<T> {

		// 总条数
		private int totalElements;
		//
		private int totalpages;
		// 当前页面
		private int currentPage;
		// 当前页数
		private int currentSize;

		private List<T> datas;

	}

	public PageDatas<HomePageBean> selectAll(int page, int size) {

		// int totalElements = ****Mapper.count()
		// int totalPages = Math.ceil(totalElements / size);
		// List<?> **Mapper.find....

		PageDatas<HomePageBean> xxx = new PageDatas<>();
		// xxx.setData

		return xxx;

	}

	public List<HomePageBean> selectIdSe(int kanjaId) {

		List<HomePageBean> list = hospitalSysMapper.selectId(kanjaId);

		for (HomePageBean x : list) {
			if (x.getFlag().equals("1")) {
				x.setFlag("未確認");
			} else {
				x.setFlag("確認済み");
			}
			System.out.println("OOOOOOOOOKKKKK");
		}

		return list;
	}

	public void saveKanja(KanjaInfoBean kanjaInfoBean) {

		Timestamp time = new Timestamp(System.currentTimeMillis());
		kanjaInfoBean.setCreateTime(time);
		hospitalSysMapper.saveKanja(kanjaInfoBean);

	}

	public void Tss(TBean tb) {
		hospitalSysMapper.TS(tb);
	}

	public List<TBean> TTT() {
		return hospitalSysMapper.TAll();
	}

	public KanjaInfoBean selectKanjaInfoByKanjaId(int kanjaId) {

		return hospitalSysMapper.selectKanjaInfoByKanjaId(kanjaId);
	}

	public void saveNewKanja(IntroductoryDocumentInfoBean introductoryDocumentInfo) {

		Timestamp time = new Timestamp(System.currentTimeMillis());

		introductoryDocumentInfo.setCreateTime(time);

		hospitalSysMapper.saveNewKanjaInfo(introductoryDocumentInfo);

	}

//更改状态的方法
	public void statusConfirmationOfHomePage(int documentId) {

		hospitalSysMapper.statusConfirmation(documentId);

	}

//删除文书的方法
	public void deleteDocumentIdById(int documentId) {

		hospitalSysMapper.deleteDocument(documentId);
	}

	// 获取d表最大值的方法 用来更新id
	public int selectMaxIdbyD() {
		IllnessIdAndDocumentIDBean idb = hospitalSysMapper.selectMaxDId();

		return idb.getDocumentId();
	}

	public int selectMaxIdByI() {

		IllnessIdAndDocumentIDBean idb = hospitalSysMapper.selectMaxIId();

		return idb.getIllnessId();
	}

	// 保存紹介状
	public void saveIntroduction(IntroductoryDocumentInfoBean idi) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		idi.setCreateTime(time);

		hospitalSysMapper.saveIntroductionInfo(idi);

	}

	// 診療所見
	public void saveClinicFindingsBean(ClinicFindingsBean cfb) {

		Timestamp time = new Timestamp(System.currentTimeMillis());
		cfb.setCreateTime(time);
		hospitalSysMapper.saveNewClinicFindings(cfb);

	}

	// 汎用紹介状
	public void saveGeneralPurposeBean(GeneralPurposeBean gpb) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		gpb.setCreateTime(time);
		hospitalSysMapper.saveNewGeneralPurpose(gpb);

	}

	public OrderInfoBean selectInfoByKanjaId(int kanjaId) {

		return hospitalSysMapper.selectInfoById(kanjaId);

	}
}
