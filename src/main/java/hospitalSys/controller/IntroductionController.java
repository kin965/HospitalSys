package hospitalSys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.service.HospitalSysService;

@Controller
public class IntroductionController {

	@Autowired
	private HospitalSysService hospitalSysService;

	@PostMapping("/saveIntroduction")
	public String saveIntroduction(Model model, @ModelAttribute IntroductoryDocumentInfoBean idi) {

		/*
		 * System.out.println(">>>>>>>>>>>>>>>>>>"+idi.getKanjaId());
		 * System.out.println("><<<<<<<<<<<<<<<<<"+idi.getHospitalName());
		 * System.out.println("SSSSSSSSSSSSSSSSSS<><><><>"+hospitalSysService.
		 * selectByKanjaId(idi.getKanjaId()));
		 */
		// 判断当前患者id是否存在 不存在的话 新建一个患者id 存在的话 直接向该id插入数据
		KanjaInfoBean kanjaInfo = hospitalSysService.selectKanjaInfoByKanjaId(idi.getKanjaId());
		if (kanjaInfo == null) {
			System.out.println("NULL");
			// 新建 id 存储数据

//			idi.getBirthday();
//			java.sql.Date sqlDate= java.sql.Date.valueOf(idi.getBirthday());

			idi.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			idi.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			System.out.println("LLLLLLLLLLLLLLLLL" + hospitalSysService.selectMaxIdbyD());
			idi.setReferralHospitalInfo(idi.getHospitalName() + " " + idi.getSection() + " " + idi.getUserName());
			idi.setDocument("紹介状");
			if (idi.getUketoriId() == null) {
				idi.setUketoriId(idi.getKanjaId());
			} else {
				idi.setUketoriId(idi.getUketoriId());
			}
			hospitalSysService.saveNewKanja(idi);

			hospitalSysService.saveIntroduction(idi);

		} else {
			System.out.println("NOT NULL");
			idi.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			idi.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			idi.setUketoriId(idi.getKanjaId());
			idi.setReferralHospitalInfo(idi.getHospitalName() + " " + idi.getSection() + " " + idi.getUserName());
			idi.setDocument("紹介状");
			hospitalSysService.saveIntroduction(idi);

			// 在当前id插入数据
		}

		return "redirect:/homePage";
	}

}
