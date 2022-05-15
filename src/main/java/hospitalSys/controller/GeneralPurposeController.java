package hospitalSys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.service.HospitalSysService;

@Controller
public class GeneralPurposeController {
	@Autowired
	private HospitalSysService hospitalSysService;

	// 保存 汎用紹介状
	@PostMapping("/saveGeneralPurpose")
	public String saveGeneralPurpose(Model model, @ModelAttribute GeneralPurposeBean gpb) {
		KanjaInfoBean kanjaInfo = hospitalSysService.selectKanjaInfoByKanjaId(gpb.getKanjaId());

		if (kanjaInfo == null) {

			IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();
			idi.setKanjaId(gpb.getKanjaId());

			if(gpb.getUketoriId()!=null) {idi.setUketoriId(gpb.getUketoriId());}
			else {idi.setUketoriId(gpb.getKanjaId());

			}


			idi.setName(gpb.getName());
			idi.setKana(gpb.getKana());
			idi.setBirthday(gpb.getBirthday());
			idi.setGender(gpb.getGender());
			idi.setPostalCode(gpb.getPostalCode());
			idi.setAddress(gpb.getAddress());
			idi.setHomeTel(gpb.getHomeTel());
			idi.setWorkTel(gpb.getWorkTel());
			hospitalSysService.saveNewKanja(idi);

			System.out.println("没有这个id的时候会来这里");
			System.out.println("IDIDIDIDIDIDIDIDI" + idi.getKanjaId());
			gpb.setUketoriId(gpb.getKanjaId());
			gpb.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			gpb.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			gpb.setDocument("定型・汎用紹介状");



			hospitalSysService.saveGeneralPurposeBean(gpb);

		} else {

			gpb.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			gpb.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			gpb.setUketoriId(gpb.getKanjaId());
			gpb.setDocument("定型・汎用紹介状");
			hospitalSysService.saveGeneralPurposeBean(gpb);
			System.out.println("有这个id的时候 会直接来这里");

		}

		return "redirect:/homePage";
	}

}
